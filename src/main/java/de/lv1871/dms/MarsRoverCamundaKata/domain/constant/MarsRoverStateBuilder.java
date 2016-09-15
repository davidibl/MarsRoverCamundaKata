package de.lv1871.dms.MarsRoverCamundaKata.domain.constant;

public class MarsRoverStateBuilder {

	private Direction direction;
	private int xCoordinate;
	private int yCoordinate;

	public static MarsRoverStateBuilder create() {
		return new MarsRoverStateBuilder();
	}

	public MarsRoverStateBuilder withDirection(Direction direction) {
		this.direction = direction;
		return this;
	}

	public MarsRoverStateBuilder withXCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
		return this;
	}

	public MarsRoverStateBuilder withYCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
		return this;
	}

	public MarsRoverStateBuilder withDefaultState() {
		this.direction = Direction.NORD;
		this.xCoordinate = 0;
		this.yCoordinate = 0;
		return this;
	}

	public MarsRoverState build() {
		MarsRoverState state = new MarsRoverState();

		state.setDirection(direction);
		state.setxCoordinate(xCoordinate);
		state.setyCoordinate(yCoordinate);

		return state;
	}

}
