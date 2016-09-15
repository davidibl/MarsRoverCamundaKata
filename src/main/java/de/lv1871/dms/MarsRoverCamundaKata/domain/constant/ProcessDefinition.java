package de.lv1871.dms.MarsRoverCamundaKata.domain.constant;

public enum ProcessDefinition {

	PRMARSROVER("PrMarsrover");

	private final String processKey;

	private ProcessDefinition(String processKey) {
		this.processKey = processKey;
	}

	public String getProcessKey() {
		return processKey;
	}

}
