package de.lv1871.dms.MarsRoverCamundaKata.process.service;

import org.springframework.stereotype.Service;

import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.Direction;
import de.lv1871.dms.MarsRoverCamundaKata.process.variable.MarsRoverProcessVariableAccessor;

@Service
public class LinksDrehenService extends ProcessServiceBase {

	@Override
	public void execute(MarsRoverProcessVariableAccessor variables) {

		switch (variables.getCurrentDirection()) {
			case NORD:
				variables.setCurrentDirection(Direction.WEST);
				break;
			case OST:
				variables.setCurrentDirection(Direction.NORD);
				break;
			case SUED:
				variables.setCurrentDirection(Direction.OST);
				break;
			case WEST:
				variables.setCurrentDirection(Direction.SUED);
				break;
		}

	}

}
