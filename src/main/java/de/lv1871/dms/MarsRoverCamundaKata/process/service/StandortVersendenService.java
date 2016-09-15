package de.lv1871.dms.MarsRoverCamundaKata.process.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.lv1871.dms.MarsRoverCamundaKata.process.variable.MarsRoverProcessVariableAccessor;
import de.lv1871.dms.MarsRoverCamundaKata.web.MarsRoverStateHandler;

@Service
public class StandortVersendenService extends ProcessServiceBase {

	@Autowired
	private MarsRoverStateHandler marsRoverStateHandler;

	@Override
	public void execute(MarsRoverProcessVariableAccessor variables) {
		// TODO Auto-generated method stub
		try {
			System.out.println(new ObjectMapper().writeValueAsString(variables.getMarsRoverState()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (marsRoverStateHandler != null) {
				marsRoverStateHandler.sendMarsRoverState(variables.getMarsRoverState());
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
