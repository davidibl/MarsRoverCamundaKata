package de.lv1871.dms.MarsRoverCamundaKata.process.variable;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.Direction;
import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.MarsRoverBefehl;
import de.lv1871.dms.MarsRoverCamundaKata.domain.constant.MarsRoverState;

public class MarsRoverProcessVariableAccessor extends ProcessVariableAccessorBase {

	public MarsRoverProcessVariableAccessor(DelegateExecution delegateExecution) {
		super(delegateExecution);
	}

	public MarsRoverProcessVariableAccessor() {
		super();
	}

	public void setCommandsString(String commands) {
		setValue(VariableName.COMMMANDSSTRING.get(), commands);
	}

	public String getCommandsString() {
		return getValue(VariableName.COMMMANDSSTRING.get());
	}

	public void setBefehle(List<MarsRoverBefehl> befehle) {
		setValue(VariableName.BEFEHLE.get(), befehle);
	}

	public List<MarsRoverBefehl> getBefehle() {
		return getValue(VariableName.BEFEHLE.get());
	}

	public void setAktuellerBefehl(MarsRoverBefehl aktuellerBefehl) {
		setValue(VariableName.AKTUELLERBEFEHL.get(), aktuellerBefehl);
	}

	public MarsRoverBefehl getAktuellerBefehl() {
		return getValue(VariableName.AKTUELLERBEFEHL.get());
	}

	public MarsRoverState getMarsRoverState() {
		return getValue(VariableName.MARSROVERSTATE.get());
	}

	public void setMarsRoverState(MarsRoverState marsRoverState) {
		setValue(VariableName.MARSROVERSTATE.get(), marsRoverState);
	}

	public Direction getCurrentDirection() {
		return getMarsRoverState().getDirection();
	}

	public void setCurrentDirection(Direction direction) {
		getMarsRoverState().setDirection(direction);
	}

	public void setXCoordinate(int xCoordinate) {
		getMarsRoverState().setxCoordinate(xCoordinate);
	}

	public void setYCoordinate(int yCoordinate) {
		getMarsRoverState().setyCoordinate(yCoordinate);
	}

	public int getYCoordinate() {
		return getMarsRoverState().getyCoordinate();
	}

	public int getXCoordinate() {
		return getMarsRoverState().getxCoordinate();
	}

}
