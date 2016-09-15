package de.lv1871.dms.MarsRoverCamundaKata.process.service;

import org.springframework.stereotype.Service;

import de.lv1871.dms.MarsRoverCamundaKata.process.variable.MarsRoverProcessVariableAccessor;

@Service
public class ErstenBefehlWaehlenService extends ProcessServiceBase {

	@Override
	public void execute(MarsRoverProcessVariableAccessor variables) {

		variables.setAktuellerBefehl(variables.getBefehle().stream().findFirst().get());
		variables.getBefehle().remove(0);

	}

}
