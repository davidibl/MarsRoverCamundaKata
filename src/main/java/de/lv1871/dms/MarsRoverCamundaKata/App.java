package de.lv1871.dms.MarsRoverCamundaKata;

import static springfox.documentation.builders.PathSelectors.regex;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan({ "de.lv1871.dms.MarsRoverCamundaKata" })
@CrossOrigin
@EnableSwagger2
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().url("jdbc:h2:file:./db/camunda.db").build();
	}

	@Bean
	public Docket prozessApi() {
	// @formatter:off
	return new Docket(DocumentationType.SWAGGER_2)
		.groupName("prozessinstanz-api")
		.apiInfo(prozessApiInfo())
		.select()
		.paths(prozessPaths())
		.build();
	// @formatter:on
	}

	private Predicate<String> prozessPaths() {
		return regex("/api/prozessinstanz.*");
	}

	private ApiInfo prozessApiInfo() {
	// @formatter:off
	return new ApiInfoBuilder()
		.title("Prozessinstanz API")
		.description("Eine Rest Resource um Variablen oder Informationen direkt zu einer Prozessinstanz abzurufen.")
		.version("2.0")
		.build();
	// @formatter:on
	}

	@Bean
	public Docket marsroverApi() {
	// @formatter:off
	return new Docket(DocumentationType.SWAGGER_2)
		.groupName("marsrover-api")
		.apiInfo(marsroverApiInfo())
		.select()
		.paths(marsroverPaths())
		.build();
	// @formatter:on
	}

	private Predicate<String> marsroverPaths() {
		return regex("/api/marsrover.*");
	}

	private ApiInfo marsroverApiInfo() {
	// @formatter:off
	return new ApiInfoBuilder()
		.title("Marsrover API")
		.description("Eine Rest Resource um die Bewegung des Marsrovers anzustoßen.")
		.version("2.0")
		.build();
	// @formatter:on
	}
}
