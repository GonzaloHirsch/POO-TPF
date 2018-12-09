package game.backend.element;

import game.backend.move.Direction;
import game.backend.move.MoveMaker;

public class Bomb extends Element {
	
	@Override
	public boolean isMovable() {
		return true;
	}

	/*
	@Override
	public Direction[] explode() {
		return new MoveMaker.;
	}
	*/
	@Override
	public String getKey() {
		return "BOMB";
	}
	
	@Override
	public long getScore() {
		return 200;
	}
}
