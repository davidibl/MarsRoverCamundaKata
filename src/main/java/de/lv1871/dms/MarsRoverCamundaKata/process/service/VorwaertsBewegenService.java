package de.lv1871.dms.MarsRoverCamundaKata.process.service;

import org.springframework.stereotype.Service;

import de.lv1871.dms.MarsRoverCamundaKata.process.variable.MarsRoverProcessVariableAccessor;

@Service
public class VorwaertsBewegenService extends ProcessServiceBase {

	@Override
	public void execute(MarsRoverProcessVariableAccessor variables) {

		switch (variables.getCurrentDirection()) {
			case NORD:
				if (variables.getYCoordinate() == 9) {
					variables.setYCoordinate(-1);
				}
				variables.setYCoordinate(variables.getYCoordinate() + 1);
				break;
			case OST:
				variables.setXCoordinate(variables.getXCoordinate() + 1);
				break;
			case SUED:
				if (variables.getYCoordinate() == 0) {
					variables.setYCoordinate(10);
				}
				variables.setYCoordinate(variables.getYCoordinate() - 1);
				break;
			case WEST:
				variables.setXCoordinate(variables.getXCoordinate() - 1);
				break;
		}

	}

}
