package de.lv1871.dms.MarsRoverCamundaKata.domain.constant;

import java.util.Arrays;

public enum MarsRoverBefehl {

    VORWAERTS("f"), RUECKWAERTS("b"), RECHTS("r"), LINKS("l");

    private final String commandString;

    private MarsRoverBefehl(String commandString) {
	this.commandString = commandString;
    }

    public String getCommandString() {
	return commandString;
    }

    public static MarsRoverBefehl createCommandByString(String commandString) {
	// @formatter:off
	return Arrays
		.asList(MarsRoverBefehl.values())
		.stream()
		.filter(command -> command.getCommandString().equals(commandString))
		.findFirst()
		.orElseThrow(() -> new IllegalArgumentException());
	// @formatter:on
    }

    public static MarsRoverBefehl createCommandByChar(int commandString) {
	return createCommandByString(String.valueOf(((char) commandString)));
    }

}
