package de.lv1871.dms.MarsRoverCamundaKata.process.service;

import org.springframework.stereotype.Service;

import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.MarsRoverBefehl;

@Service
public class CommandTypeDecider {

	public boolean isBewegung(MarsRoverBefehl aktuellerBefehl) {
		return (aktuellerBefehl == MarsRoverBefehl.VORWAERTS || aktuellerBefehl == MarsRoverBefehl.RUECKWAERTS);
	}

	public boolean isRechts(MarsRoverBefehl aktuellerBefehl) {
		return (aktuellerBefehl == MarsRoverBefehl.RECHTS);
	}

	public boolean isVorwaerts(MarsRoverBefehl aktuellerBefehl) {
		return (aktuellerBefehl == MarsRoverBefehl.VORWAERTS);
	}

}
