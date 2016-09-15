package de.lv1871.dms.MarsRoverCamundaKata.process.variable;

public enum VariableName {

    // @formatter:off
    COMMMANDSSTRING("commandsString"),
    BEFEHLE("befehle"),
    AKTUELLERBEFEHL("aktuellerBefehl"),
    MARSROVERSTATE("marsRoverState");
    // @formatter:on

    private String variableNameString;

    private VariableName(String variableNameString) {
	this.variableNameString = variableNameString;
    }

    public String get() {
	return variableNameString;
    }

}
