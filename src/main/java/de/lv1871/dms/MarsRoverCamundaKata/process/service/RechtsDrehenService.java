package de.lv1871.dms.MarsRoverCamundaKata.process.service;

import org.springframework.stereotype.Service;

import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.Direction;
import de.lv1871.dms.MarsRoverCamundaKata.process.variable.MarsRoverProcessVariableAccessor;

@Service
public class RechtsDrehenService extends ProcessServiceBase {

	@Override
	public void execute(MarsRoverProcessVariableAccessor variables) {

		switch (variables.getCurrentDirection()) {
			case NORD:
				variables.setCurrentDirection(Direction.OST);
				break;
			case OST:
				variables.setCurrentDirection(Direction.SUED);
				break;
			case SUED:
				variables.setCurrentDirection(Direction.WEST);
				break;
			case WEST:
				variables.setCurrentDirection(Direction.NORD);
				break;
		}

	}

}
