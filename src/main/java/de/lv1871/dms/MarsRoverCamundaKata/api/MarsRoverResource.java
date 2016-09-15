package de.lv1871.dms.MarsRoverCamundaKata.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.MarsRoverState;
import de.lv1871.dms.MarsRoverCamundaKata.service.MarsRoverService;

@Controller
@CrossOrigin
public class MarsRoverResource {

	@Autowired
	private MarsRoverService marsRoverService;

	@RequestMapping(path = "/api/marsrover", method = RequestMethod.POST)
	public @ResponseBody String postCommandString(@RequestParam("commands") String commands,
			@RequestBody MarsRoverState state) {
		return marsRoverService.startMarsRover(commands, state);
	}

	@RequestMapping(path = "/api/marsrover/state", method = RequestMethod.GET)
	public @ResponseBody MarsRoverState getInitialState() {
		return marsRoverService.getIntialState();
	}

}
