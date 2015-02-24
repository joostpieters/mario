/**
 * A class that describes the movement of the rectangular player
 * character Mazub in a game world. 
 * 
 * @invar The lowest left corner (x_pos,y_pos) has to stay
 * 			in the game world.
 * 		| isValidPosition(getXPos(), getYPos())
 * @invar The speed has to be limited to a maximum speed
 * 		| isValidSpeed(getSpeed())
 * @author Pieter Van den Berghe, Ward Romanus
 */
public class Mazub {
	
	/**
	 * @param x_pos
	 * The x position in the field for the new Mazub
	 * @param y_pos
	 * The y position in the field for the new Mazub
	 * @param x_dim
	 * Number of pixels from the x dimension of the new Mazub
	 * @param y_dim
	 * Number of pixels from the y dimension of the new Mazub
	 * @post the mazub is created at (x_pos,y_pos)
	 * 			| new.getXPos() == x_pos
	 * 			| new.getYPos() == y_pos
	 * 			| new.getXDim() == x_dim
	 * 			| new.getYDim() == y_dim
	 * @throws	IllegalPositionException
	 * 			The given position is not valid for Mazub
	 * 			| !isValidPosition(x_pos,y_pos)			 
	 * 
	 * DEFENSIEF uitwerken
	 * 
	 * 
	 */
	public Mazub(int x_pos, int y_pos, int[] dimension)
			throws IllegalPositionException, IllegalDimensionException {
				if(!isValidPosition(x_pos,y_pos))
					throw new IllegalPositionException(x_pos,y_pos);
				if (!isValidDimension(dimension))
					throw new IllegalDimensionException(dimension);
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.dimension = dimension;
	}
	
	
	public Mazub(int x_pos, int y_pos, int[] dimension,
			int initStartSpeed,int maxSpeed)
			throws IllegalPositionException {
				if(!isValidPosition(x_pos,y_pos))
					throw new IllegalPositionException(x_pos,y_pos);
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.dimension = dimension;
		this.initStartSpeed = initStartSpeed;
		this.maxSpeed = maxSpeed;
	}
	
	/**	
	 * commentaar toevoegen
	 */
	public int x_pos = 0;
	public int y_pos = 0;
	public int x_dim = 6; //not sure or this should be declared here
	public int y_dim = 11;//not sure or this should be declared here
	public int[] dimension;
	public int initStartSpeed; 
	public int maxSpeed; 
	public double speed;
	public double newSpeed;
	public double deltaT;
	public double new_x_pos;
	public double new_y_pos;
	public double x_difference;
	public double y_difference;
	public boolean duck = true;
	public double acc; // acceleration
	public double YSpeed;
	public double YAcc;
	public static int MIN_X_VALUE = 0;
	public static int MAX_X_VALUE = 1023;
	public static int MIN_Y_VALUE = 0;
	public static int MAX_Y_VALUE = 767;
	public int START_SPEED = getInitStartSpeed(); 
	public static int MAX_SPEED = 3;
	public static int MAX_SPEED_DUCK = 1;
	public static int JUMP_SPEED = 8;
	public static int FALL_ACC = -10;
		
	
	public int getXPos() {
		return x_pos;
	}
	public int getYPos() {
		return y_pos;
	}
	public int[] getDimension() {
		return dimension;
	}
	public int getInitStartSpeed() {
		return initStartSpeed;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	
	
	/**
	 * 	
	 * @param x_pos 
	 * 			the horizontal position of Mazub
	 * @param y_pos
	 * 			the vertical position of Mazub
	 * @return 	True if the horizontal position x_pos and 
	 *			and the vertical position y_pos stay in the 
	 *			game world.
	 *			| return (x_pos >= MIN_X_VALUE && x_pos <= MAX_X_VALUE
	 *				&& y_pos >= MIN_Y_VALUE && y_pos <= MAX_Y_VALUE) 
	 */
	public static boolean isValidPosition(int x_pos, int y_pos) {
		return (x_pos >= MIN_X_VALUE && x_pos <= MAX_X_VALUE
				 && y_pos >= MIN_Y_VALUE && y_pos <= MAX_Y_VALUE);
	}
	
	/**
	 * 	
	 * @param dimension
	 * 			the dimension of Mazub
	 * @return 	True if both dimensions are positive
	 *			| return (dimension[0] > 0) && (dimension[1] > 0)
	 */
	public static boolean isValidDimension(int[] dimension) {
		return (dimension[0] > 0) && (dimension[1] > 0);
	}
	
	
	/**
	 * 
	 * @param x_pos 
	 * 			the horizontal position of Mazub
	 * @param y_pos
	 * 			the vertical position of Mazub
	 * @pre the position of Mazub must be valid
	 * 			| isValidPosition(getXPos(),getYPos())
	 * @post Mazub moved with a certain speed and acceleration
	 * 
	 * NOMINAAL uitwerken
	 * 
	 * KLAAR PIEMELS
	 */
	public void startMove() {
		assert isValidPosition(getXPos(),getYPos());
		speed = START_SPEED; 
		if (this.isDucked() == false) {
			acc = (float) 0.9;
			maxSpeed = getMaxSpeed();
		}
		else {
			acc = 0;
			maxSpeed = MAX_SPEED_DUCK;
		}
		
	}
	
	/**
	 * @param x_pos 
	 * 			the horizontal position of Mazub
	 * @param y_pos
	 * 			the vertical position of Mazub
	 * @pre the position of Mazub must be valid
	 * 			| isValidPosition(getXPos(),getYPos())
	 * @post Mazub does not move
	 * 
	 * 
	 * NOMINAAL uitwerken
	 * 
	 * KLAAR
	 */
	public void endMove(int x_pos, int y_pos) {
		speed = 0;
		acc = 0;
	}
	
	/**
	 * 
	 * 
	 * 
	 * DEFENSIEF uitwerken
	 */
	public void advanceTime() {
		newSpeed = speed + acc*deltaT;
		if (newSpeed < maxSpeed) {
			speed = (float) newSpeed;
		}
		else {			
			acc = 0;
			speed = maxSpeed;
			}
		new_x_pos = (double) this.getXPos() + speed*100*deltaT + x_difference;
		
		YSpeed = YSpeed + YAcc*deltaT;
		if (y_pos == 0) {
			endFall();
		}
		else {
			new_y_pos = (double) this.getYPos() + YSpeed*100*deltaT + y_difference;
		}
				
		x_pos = (int) Math.floor(new_x_pos);
		y_pos = (int) Math.floor(new_y_pos);
		x_difference = new_x_pos - x_pos;
		y_difference = new_y_pos - y_pos;
	}
	
	/**
	 * 
	 * 
	 * 
	 * TOTAAL uitwerken
	 */
	public void startJump() {
		YSpeed = JUMP_SPEED;
	}
	
	/**
	 * TOTAAL uitwerken
	 */
	public void endJump() {
		if (YSpeed > 0) {
			YSpeed = 0;
		}
		if (y_pos != 0) {
			fall();
		}
	}
	
	/**
	 * TOTAAL uitwerken
	 */
	public void fall() {
		YAcc = FALL_ACC;
	}
	public void endFall() {
		YAcc = 0;
	}
	
	public boolean isDucked(){
		return (duck);
	}
	
	/**
	 * DEFENSIEF uitwerken
	 */
	
	public void startDuck() {
		
	}
	
	/**
	 * DEFENSIEF uitwerken
	 */
	public void endDuck() {	
		
	}
	
	/**
	 * NOMINAAL uitwerken
	 * GEEN formele documentatie nodig
	 */
	public void getCurrentSprite() {
		
	}

	public void setInitStartSpeed(int initStartSpeed) {
		this.initStartSpeed = initStartSpeed;
	}
}
