package de.lv1871.dms.MarsRoverCamundaKata.process.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.MarsRoverBefehl;
import de.lv1871.dms.MarsRoverCamundaKata.process.variable.MarsRoverProcessVariableAccessor;

@Service
public class BefehleExtrahierenService extends ProcessServiceBase {

    @Override
    public void execute(MarsRoverProcessVariableAccessor variables) {

	// @formatter:off
	variables.setBefehle(
		variables
			.getCommandsString()
			.chars()
			.mapToObj(c -> (char) c)
			.map(MarsRoverBefehl::createCommandByChar)
			.collect(Collectors.toList()));
	// @formatter:on

    }

}
