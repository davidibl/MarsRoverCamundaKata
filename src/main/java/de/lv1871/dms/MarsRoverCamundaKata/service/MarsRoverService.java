package de.lv1871.dms.MarsRoverCamundaKata.service;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.MarsRoverState;
import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.MarsRoverStateBuilder;
import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.ProcessDefinition;
import de.lv1871.dms.MarsRoverCamundaKata.process.variable.MarsRoverProcessVariableAccessor;

@Service
public class MarsRoverService {

	@Autowired
	private RuntimeService runtimeService;

	public String startMarsRover(String commands, MarsRoverState state) {
		MarsRoverProcessVariableAccessor variables = new MarsRoverProcessVariableAccessor();
		variables.setCommandsString(commands);
		variables.setMarsRoverState(state);

		return runtimeService
				.startProcessInstanceByKey(ProcessDefinition.PRMARSROVER.getProcessKey(), variables.toMap())
				.getProcessInstanceId();
	}

	public MarsRoverState getIntialState() {
		return MarsRoverStateBuilder.create().withDefaultState().build();
	}

}
