package de.lv1871.dms.MarsRoverCamundaKata.process.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import de.lv1871.dms.MarsRoverCamundaKata.process.variable.MarsRoverProcessVariableAccessor;

public abstract class ProcessServiceBase implements JavaDelegate {

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		execute(new MarsRoverProcessVariableAccessor(delegateExecution));
	}

	public abstract void execute(MarsRoverProcessVariableAccessor variables);

}
