package de.lv1871.dms.MarsRoverCamundaKata.process;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.execute;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.job;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;

import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.Direction;
import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.MarsRoverState;
import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.MarsRoverStateBuilder;
import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.ProcessDefinition;
import de.lv1871.dms.MarsRoverCamundaKata.process.service.BefehleExtrahierenService;
import de.lv1871.dms.MarsRoverCamundaKata.process.service.CommandTypeDecider;
import de.lv1871.dms.MarsRoverCamundaKata.process.service.ErstenBefehlWaehlenService;
import de.lv1871.dms.MarsRoverCamundaKata.process.service.LinksDrehenService;
import de.lv1871.dms.MarsRoverCamundaKata.process.service.RechtsDrehenService;
import de.lv1871.dms.MarsRoverCamundaKata.process.service.RueckwaertsBewegenService;
import de.lv1871.dms.MarsRoverCamundaKata.process.service.StandortVersendenService;
import de.lv1871.dms.MarsRoverCamundaKata.process.service.VorwaertsBewegenService;
import de.lv1871.dms.MarsRoverCamundaKata.process.variable.MarsRoverProcessVariableAccessor;
import de.lv1871.dms.MarsRoverCamundaKata.process.variable.VariableName;

@Deployment(resources = "marsrover.bpmn")
public class MarsRoverProzessTest extends AbstractProcessEngineRuleTest {

	private static final String BEFEHL_VOR_VOR_RECHTS_VOR_VOR = "ffrff";
	private static final String BEFEHL_RECHTS_RECHTS_VOR = "rrf";

	private static final MarsRoverState NULL_NULL_NORD_STATE = MarsRoverStateBuilder.create()
			.withDirection(Direction.NORD).withXCoordinate(0).withYCoordinate(0).build();

	private static final MarsRoverState NULL_NEUN_SUED_STATE = MarsRoverStateBuilder.create()
			.withDirection(Direction.SUED).withXCoordinate(0).withYCoordinate(9).build();

	private static final MarsRoverState ZWEI_ZWEI_OST_STATE = MarsRoverStateBuilder.create()
			.withDirection(Direction.OST).withXCoordinate(2).withYCoordinate(2).build();

	@Test
	public void verifyMoveToZweiZweiOst() {

		registerAllBeanImplementations();

		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(
				ProcessDefinition.PRMARSROVER.getProcessKey(),
				getInitialVariablesSetup(BEFEHL_VOR_VOR_RECHTS_VOR_VOR, NULL_NULL_NORD_STATE).toMap());

		assertThat(processInstance).isStarted().isActive().isNotEnded();

		runToEnd(processInstance);

		assertThat(processInstance).isEnded();

		MarsRoverState finalState = getFinalState(processInstance);

		assertEquals(ZWEI_ZWEI_OST_STATE, finalState);
	}

	@Test
	public void verifyWennYNullDannNeun() {
		registerAllBeanImplementations();

		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(
				ProcessDefinition.PRMARSROVER.getProcessKey(),
				getInitialVariablesSetup(BEFEHL_RECHTS_RECHTS_VOR, NULL_NULL_NORD_STATE).toMap());

		assertThat(processInstance).isStarted().isActive().isNotEnded();

		runToEnd(processInstance);

		assertThat(processInstance).isEnded();

		MarsRoverState finalState = getFinalState(processInstance);

		assertEquals(NULL_NEUN_SUED_STATE, finalState);
	}

	@Test
	public void linksDrehenAusNordRichtungGibtWesten() {
		LinksDrehenService service = new LinksDrehenService();
		MarsRoverProcessVariableAccessor variables = new MarsRoverProcessVariableAccessor();
		variables.setMarsRoverState(NULL_NULL_NORD_STATE);

		service.execute(variables);

		assertEquals(Direction.WEST, variables.getCurrentDirection());
	}

	private void registerAllBeanImplementations() {

		//@formatter:off
		List<Class<? extends Object>> types = Arrays.asList(
			BefehleExtrahierenService.class,
			CommandTypeDecider.class,
			ErstenBefehlWaehlenService.class,
			LinksDrehenService.class,
			RechtsDrehenService.class,
			RueckwaertsBewegenService.class,
			StandortVersendenService.class,
			VorwaertsBewegenService.class);
		//@formatter:on
		registerBeanImplementationsByTypes(types);
	}

	private void registerBeanImplementationsByTypes(List<Class<?>> types) {

		types.forEach(type -> {
			Object newInstance;
			try {
				newInstance = type.newInstance();
				Mocks.register(StringUtils.uncapitalize(type.getSimpleName()), newInstance);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	private MarsRoverState getFinalState(ProcessInstance processInstance) {
		HistoryService historyService = processEngine.getHistoryService();
		return (MarsRoverState) historyService.createHistoricVariableInstanceQuery()
				.processInstanceId(processInstance.getProcessInstanceId())
				.variableName(VariableName.MARSROVERSTATE.get()).singleResult().getValue();
	}

	private MarsRoverProcessVariableAccessor getInitialVariablesSetup(String commandsString, MarsRoverState state) {
		MarsRoverProcessVariableAccessor variables = new MarsRoverProcessVariableAccessor();
		variables.setCommandsString(commandsString);
		variables.setMarsRoverState(state);

		return variables;
	}

	private void runToEnd(ProcessInstance processInstance) {
		Job job2;
		while ((job2 = job(processInstance)) != null) {
			execute(job2);
		}

	}

}
