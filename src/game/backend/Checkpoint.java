package game.backend;

/*
	The i and j might seem inverted but thats because of the grid notation
	Smaller i means higher in the board
 */

public enum Checkpoint {

	//	UP
	U(-1,0, 1),

	//	UP - UP
	UU(-2,0, 2),

	//	DOWN
	D(1,0, 4),

	//	DOWN - DOWN
	DD(2,0, 8),

	//	RIGHT
	R(0,1, 16),

	//	RIGHT - RIGHT
	RR(0,2, 32),

	//	LEFT
	L(0,-1, 64),

	//	LEFT - LEFT
	LL(0,-2, 128);
	
	private int i;
	private int j;
	private int value;
	
	Checkpoint(int i, int j, int value) {
		this.i = i;
		this.j = j;
		this.value = value;
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	public int getValue() {
		return value;
	}

}
