package de.lv1871.dms.MarsRoverCamundaKata.process.variable;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public class ProcessVariableAccessorBase {

	private DelegateExecution delegateExecution;
	private Map<String, Object> variables;

	public ProcessVariableAccessorBase(DelegateExecution delegateExecution) {
		this.delegateExecution = delegateExecution;
	}

	public ProcessVariableAccessorBase() {
		variables = new HashMap<>();
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue(String key) {
		if (delegateExecution != null) {
			return (T) delegateExecution.getVariable(key);
		} else {
			return (T) variables.get(key);
		}
	}

	public void setValue(String key, Object value) {
		if (delegateExecution != null) {
			delegateExecution.setVariable(key, value);
		} else {
			variables.put(key, value);
		}
	}

	public Map<String, Object> toMap() {
		if (delegateExecution != null) {
			return delegateExecution.getVariables();
		}

		return variables;
	}

}
