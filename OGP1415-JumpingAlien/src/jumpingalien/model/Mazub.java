package jumpingalien.model;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import jumpingalien.util.Sprite;

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
	 * Initialize this new Mazub with given x and y positions and given sprites.
	 * 
	 * @param x_pos
	 * The x position in the field for the new Mazub
	 * @param y_pos
	 * The y position in the field for the new Mazub
	 * @param sprites
	 * The sprites for the new Mazub
	 * @effect the mazub is created at (x_pos,y_pos)
	 * 			| new.getXPos() == x_pos
	 * 			| new.getYPos() == y_pos
	 * 			| new.getSprite() == sprites
	 * 			| new.getInitStartSpeed() == START_SPEED
	 * 			| new.getMaxSpeed() == MAX_SPEED
	 * @throws	IllegalPositionException
	 * 			The given position is not valid for Mazub
	 * 			| !isValidPosition(x_pos,y_pos)
	 * 
	 * 
	 */
	public Mazub(int x_pos, int y_pos, Sprite[] sprites)
			throws IllegalPositionException {
				if(!isValidPosition())
					throw new IllegalPositionException(x_pos,y_pos);	
		this.setXPos(x_pos);
		this.setYPos(y_pos);
		this.setSprite(sprites);
		this.setInitStartSpeed(START_SPEED);
		this.setMaxSpeed(MAX_SPEED);
	}	
	
	

	/**
	 * Initialize this new Mazub with given x and y positions, sprites, horizontal start speed and maximum horizontal speed.
	 * 
	 * @param x_pos
	 * The x position in the field for the new Mazub
	 * @param y_pos
	 * The y position in the field for the new Mazub
	 * @param sprites
	 * The sprites for the new Mazub
	 * @param initStartSpeed
	 * The initial horizontal start speed
	 * @param maxSpeed
	 * The maximum horizontal speed 
	 * @effect the mazub is created at (x_pos,y_pos)
	 * 			| new.getXPos() == x_pos
	 * 			| new.getYPos() == y_pos
	 * 			| new.getSprite() == sprites
	 * 			| new.getInitStartSpeed() == initStartSpeed
	 * 			| new.getMaxSpeed() == maxSpeed
	 * @throws	IllegalPositionException
	 * 			The given position is not valid for Mazub
	 * 			| !isValidPosition(x_pos,y_pos)
	 * 	 
	 * 
	 * The initial velocity will never be changed below 1 m/s so
	 * we don't need an IllegalInitStartSpeedException or a
	 * IllegalMaxSpeedException. 
	 * 
	 */	
	public Mazub(int x_pos, int y_pos, Sprite[] sprites,
			int initStartSpeed,int maxSpeed)
		throws IllegalPositionException {
			if (!isValidPosition())
				throw new IllegalPositionException(x_pos,y_pos);
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.sprites = sprites;
		this.initStartSpeed = initStartSpeed;
		this.maxSpeed = maxSpeed;
	}
	
	
	/**
	 *  the horizontal position of mazub
	 */
	public int x_pos = 0;
	/**
	 * the vertical position of mazub
	 */
	public int y_pos = 0;
	
	/**
	 * The sprites for Mazub
	 */
	private Sprite[] sprites;
	
	/**
	 * the orientation of Mazub
	 */
	public String orientation  = "right";
	/**
	 * the width/horizontal size of mazub
	 */
	public int x_dim;
	/**
	 * the height/vertical size of mazub
	 */
	public int y_dim;

	/**
	 * the dimensions of mazub, the set consists of the x_dim
	 * and the y_dim.
	 */
	public int[] dimension
	;
	/**
	 * the starting speed when startMove is initiated
	 */
	public int initStartSpeed;
	/**
	 * the maximum horizontal speed the mazub can reach
	 */
	public int maxSpeed; 
	/**
	 * the speed of mazub
	 */
	public double xSpeed;
	/**
	 * the horizontal speed at time = time + detltaT 
	 * (for example when mazub is accelerating) 
	 */
	public double newSpeed;
	/**
	 * the vertical speed at time = time + detltaT 
	 * (for example when mazub is accelerating) 
	 */
	public double newYSpeed;
	/**
	 * a small time interval, for example the new_speed after 
	 * deltaT seconds is given by speed + deltaT*acc 
	 * (acc =  acceleration)
	 */	
	public double dt;
	/**
	 * the x position (horizontal position) after deltaT seconds
	 */
	public double new_x_pos;
	/**
	 * the y position (vertical position) after deltaT seconds
	 */
	public double new_y_pos;
	/**
	 * the difference between the new x_position (new_x_pos) and
	 * the previous x_pos (x_pos)
	 */
	public double x_difference;
	/**
	 * the difference between the new y_position (new_y_pos) and
	 * the previous y_pos (y_pos)
	 */
	public double y_difference;
	/**
	 * a boolean saying if the mazub is ducked
	 */
	public boolean duck = false;
	/**
	 * the horizontal acceleration of mazub
	 */
	public double xAcc;
	/**
	 * the speed of mazub in the vertical direction
	 */
	public double ySpeed;
	/**
	 * the acceleration of mazub in the vertical direction
	 */
	public double yAcc;
	/**
	 * the next 4 int's are the ultimate static values for the borders
	 * of the gamescreen and thus for (x_pos,y_pos)
	 */
	/**
	 * the minimal value for x_pos
	 */
	public static int MIN_X_VALUE = 0;
	/**
	 * the maximal value for x_pos
	 */
	public static int MAX_X_VALUE = 1023;
	/**
	 * the minimal value for y_pos
	 */
	public static int MIN_Y_VALUE = 0;
	/**
	 * the maximal value for y_pos
	 */
	public static int MAX_Y_VALUE = 767;
	/**
	 * the static int giving the starting speed of mazub
	 * when startMove() is initiated
	 */
	public static int START_SPEED = 1;
	/**
	 * the maximal horizontal speed mazub can reach
	 */
	public static int MAX_SPEED = 3;
	/**
	 * the maximal horizontal speed mazub can reach
	 * when mazub is ducked
	 */
	public static int MAX_SPEED_DUCK = 1;
	/**
	 * the vertical speed at which mazub moves when 
	 * startJump() is initiated
	 */
	public static int JUMP_SPEED = 20;
	/**
	 * the vertical acceleration at which mazub falls
	 */
	public static double FALL_ACC = -10;
	/**
	 * the time passed after endMove was invoced for the
	 * last time
	 */
	public double time_since_endMove;
	/**
	 * the time passed after startMove was invoced for the
	 * last time
	 */
	public double time_since_startMove;
	/**
	 * the time that mazub should not move before sprite 0 is displayed
	 */
	public static double NOT_MOVING_TIME = 1;
	/**
	 * the time every image is displayed when mazub is running
	 */
	public static double TIME_DIFFERENT_IMAGE = 0.075;
	/**
	 * the amount of running images with a certain orientation
	 */
	public int m;
	/**
	 * the variable to go over the array of possible images
	 */
	public int i = 0;
	
	/**
	 * The boolean to reflect if Mazub is falling or not
	 */
	public boolean falling = false;
	
	/**
	 * Returns True if Mazub is falling
	 * @return falling
	 */
	public boolean isFalling() {
		return falling;
	}
	
	/**
	 * Marks the boolean falling as true
	 * @post falling == true
	 */
	public void setFalling() {
		this.falling = true;
	}
	
	/**
	 * Marks the boolean falling as false
	 * @post falling == false
	 */
	public void endFalling() {
		this.falling = false;
	}
		
	/**
	 * Returns the x position
	 * @return x_pos
	 */
	@Basic
	public int getXPos() {
		return x_pos;
	}
	
	/**
	 * Returns the difference between the real horizontal position of Mazub and the rounded down value 
	 * @return x_difference
	 */
	public double getXDifference() {
		return x_difference;
	}
	
	/**
	 * Returns the horizontal position of Mazub after dt seconds
	 * @return new_x_pos
	 */
	public double getNewXPos() {
		return new_x_pos;
	}
	
	
	
	/**
	 * Returns the y position
	 * 
	 * @return the y position
	 */
	@Basic
	public int getYPos() {
		return y_pos;
	}
	public double getYDifference() {
		return y_difference;
	}
	public double getNewYPos() {
		return new_y_pos;
	}
	public void setNewYPos(int f) {
		this.new_y_pos = f;
	}
	
	/**
	 * 
	 * @return an array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given alien's bottom left pixel in the world.	
	 */
	public int[] getLocation() {
		return new int[]{this.getXPos(),this.getYPos()};
	}	
	
	/**
	 * Returns the orientation of Mazub
	 * @return orientation
	 */
	@Basic
	public String getOrientation() {
		return orientation;
	}
	/**
	 * 
	 * @return the horizontal dimension of mazub (width)
	 */
	@Basic
	public int getXDim() {
		return this.getCurrentSprite().getWidth();
	}
	/**
	 * 
	 * @return the vertical dimension of mazub (height)
	 */
	@Basic
	public int getYDim() {
		return this.getCurrentSprite().getHeight();
	}
	public Sprite[] getSprite(){
		return this.sprites;
	}
	
	/**
	 * Returns the dimensions
	 * 
	 * @return the dimensions
	 */
	public int[] getDimension() {
		return dimension;
	}
	/**
	 * Returns the horizontal speed
	 * 
	 * @return the horizontal speed
	 * 			| speed
	 */
	@Basic
	public double getXSpeed() {
		return xSpeed;
	}
	
	/**
	 * 
	 * @return the vertical speed of mazub
	 * 			| YSpeed
	 */
	@Basic
	public double getYSpeed() {
		return ySpeed;
	}
	
	public double[] getVelocity(){
		return new double[] {xSpeed,ySpeed};
	}

	/**
	 * Returns the horizontal acceleration
	 * 
	 * @return the horizontal acceleration 
	 * 			| Acc
	 */
	@Basic
	public double getXAcc() {
		return xAcc;
	}
	/**
	 * 
	 * @return the vertical acceleration of mazub
	 * 			| YAcc
	 */
	@Basic
	public double getYAcc() {
		return yAcc;
	}
	
	/**
	 * 
	 * @return an array consisting of the horizontal and 
	 * vertical acceleration.
	 * 			| {Acc,YAcc}
	 */
	public double[] getAcceleration(){
		return new double[] {this.getXAcc(),this.getYAcc()};
	}
	
	/**
	 * 
	 * MOGEN DE GEWONE VARIABELEN IN DE ARRAY OF MOETEN
	 * WE DAARVOOR GETTERS AANMAKEN????
	 * 
	 * 
	 * @return an array consisting of the width and height of mazub
	 */
	public int[] getSize() {
		return new int[] {this.getXDim(),this.getYDim()};
	}

	/**
	 * Returns the initial start speed
	 * 
	 * @return the initial start speed
	 */
	@Immutable
	public int getInitStartSpeed() {
		return initStartSpeed;
	}
	
	/**
	 * Returns the maximum speed
	 * 
	 * @return the maximum speed
	 */
	@Immutable
	public int getMaxSpeed() {
		return maxSpeed;
	}	
	
	/**
	 * @return the time_since_endMove
	 */
	public double getTime_since_endMove() {
		return time_since_endMove;
	}

	/**
	 * @param time_since_endMove the time_since_endMove to set
	 */
	public void setTime_since_endMove(double time_since_endMove) {
		this.time_since_endMove = time_since_endMove;
	}

	/**
	 * @return the time_since_startMove
	 */
	public double getTime_since_startMove() {
		return time_since_startMove;
	}

	/**
	 * @param time_since_startMove the time_since_startMove to set
	 */
	public void setTime_since_startMove(double time_since_startMove) {
		this.time_since_startMove = time_since_startMove;
	}

	/**
	 * @param ySpeed the ySpeed to set
	 */
	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	public void setyAcc(double a) {
		this.yAcc = a;
	}

	/**
	 * 	Checks whether the given positions are valid positions for 
	 *  any Mazub.
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
	public boolean isValidPosition() {
		return (this.getXPos() >= MIN_X_VALUE && this.getXPos() <= MAX_X_VALUE
				 && this.getYPos() >= MIN_Y_VALUE && this.getYPos() <= MAX_Y_VALUE);
	}

	
//	/**
//	 * 	Checks whether the given dimensions are valid dimensions for 
//	 *  any Mazub.
//	 * 
//	 * @param dimension
//	 * 			the dimension of Mazub
//	 * @return 	True if both dimensions are positive
//	 *			| return (dimension[0] > 0) && (dimension[1] > 0)
//	 */
//	public static boolean isValidDimension(int[] dimension) {
//		return (dimension[0] > 0) && (dimension[1] > 0);
//	}	
	
	/**
	 * sets the speed of mazub to a new value speed
	 * @param speed
	 * 			the new speed of mazub
	 */
	
	public void setInitStartSpeed(double initstartspeed) {
		this.initStartSpeed = (int) initstartspeed;
	}
	
	public void setXSpeed(double s){
		this.xSpeed = s;
	}
	
	public void setMaxSpeed(int maxspeed) {
		this.maxSpeed = maxspeed;
	}
	
	public void setxAcc(double xAcc) {
		this.xAcc = xAcc;
	}
	
	public void setXPos(double x) {
		this.x_pos = (int) Math.floor(x);
	}
	
	public void setYPos(double y) {
		this.y_pos = (int) Math.floor(y);
	}
	
	public void setXDifference(double x_difference) {
		this.x_difference = x_difference;
	}
	
	public void setYDifference(double y_difference) {
		this.y_difference = y_difference;
	}
	
	public void setNewXPos(int n) {
		this.new_x_pos = n;
	}
	
	private void setSprite(Sprite[] sprites) {
		this.sprites = sprites;	
	}
	
	private void setDuck(boolean duck) {
		this.duck = duck;
	}

	/**
	 * Method to make the Mazub start moving
	 * 
	 * @param x_pos 
	 * 			the horizontal position of Mazub
	 * @param y_pos
	 * 			the vertical position of Mazub
	 * @pre the position of Mazub must be valid
	 * 			| isValidPosition(getXPos(),getYPos())
	 * @post Mazub starts moving with a certain speed and acceleration
	 * 			| if (! isDucked())
	 * 			| 	then acc = 0.9
	 * 			|		 maxSpeed = getMaxSpeed()
	 * 			| else
	 * 			| 	acc = 0
	 * 			|	maxSpeed = MAX_SPEED_DUCK
	 * @post time_since_startMove is set to zero
	 * 			| time_since_startMove = 0
	 * 
	 * NOMINAAL uitwerken
	 * 
	 * KLAAR
	 */
	public void startMove() {
		assert isValidPosition();
		this.setXSpeed(START_SPEED); 
		if (this.isDucked() == false) {
			xAcc = (float) 0.9;
			maxSpeed = getMaxSpeed();
		}
		else {
			xAcc = 0;
			maxSpeed = MAX_SPEED_DUCK;
		}
		time_since_startMove = 0;
	}
	
	/**
	 * Mazub starts moving to the right
	 * @post 	| startMove()
	 * 			| orientation = right
	 */
	public void startMoveRight(){
		startMove();
		orientation = "right";
	}
	/**
	 * Mazub starts moving to the left
	 * @post 	| startMove()
	 * 			| orientation = left
	 */
	public void startMoveLeft(){
		startMove();
		orientation = "left";
	}
	
	/**
	 * Method to end the move of a Mazub
	 * @post Mazub does not move
	 * 			| speed = 0;
	 * 			| acc = 0;
	 * @post time_until_endMove is set to zero
	 * 			| time_until_endMove = 0
	 * 
	 * 
	 * NOMINAAL uitwerken
	 * 
	 *Dat klopt hier niet
	 */
	public void endMove() {
		assert (xSpeed>=0);
		this.setXSpeed(0);
		this.setxAcc(0);
		setTime_since_endMove(0);
	}
	
	/**
	 * ends the mazub's movement to the left
	 */
	public void endMoveLeft() {
		endMove();
	}
	
	/**
	 * ends the mazub's movement to the right
	 */
	public void endMoveRight() {
		endMove();
	}
	
	/**
	 *  
	 * DEFENSIEF uitwerken
	 */
	public void startJump() {
		if (this.getYPos() == 0) {
			setySpeed(JUMP_SPEED);	
		}
	}
	
	/**
	 * DEFENSIEF uitwerken
	 */
	public void endJump() {
		if (this.getYSpeed() > 0) {
			this.setySpeed(0);
		}
	}
	
	/**
	 * Makes mazub fall if he is not on the standing on the ground
	 * 
	 * @post 	mazub accelerates to the ground with an acceleration
	 * 			of 10 m/s� if was above the ground and he would not fall 
	 * 			below the ground. If he would end below the ground, he ends
	 * 			on the ground and his fall ends.
	 * 		| 	if ((y_pos >0) && (new_y_pos >= 0))
	 * 		|		then YAcc = FALL_ACC
	 * 		| 	else if (y_pos > 0) 
	 * 		|		then y_pos = 0 
	 * 		|			endFall()
	 */
	public void fall() {
		//this.setFalling();
		if (this.getYPos() > 0){
			this.setyAcc(FALL_ACC);
			this.setFalling();
		}
//		new_y_pos = (double) this.getYPos() + ySpeed*100*dt
//				+ 0.5 * FALL_ACC * 100 * Math.pow(dt,2) + y_difference;
//		if ((y_pos >0) && (new_y_pos >= 0)) {
//			yAcc = FALL_ACC;
//		}
//		else if (y_pos > 0) {
//			y_pos = 0;
//			endFall();
//		}
	} 
	/**
	 * Mazub fall ends
	 * 
	 * @post	the acceleration of Mazub is set to 0
	 * 		|	YAcc = 0
	 * 
	 * 
	 */
	public void endFall() {
		this.setyAcc(0);
		this.setySpeed(0);
		this.endFalling();
		
	}
	
	public void advance_x(double dt) {
		if (this.getXSpeed() >= this.getMaxSpeed()){
			this.setXSpeed(this.getMaxSpeed());
			this.setxAcc(0);
		}
		
		if (this.getOrientation() == "right") {
			new_x_pos = (double) this.getXPos() + this.getXSpeed()*100*dt
					+ 0.5 * this.getXAcc() * 100 * Math.pow(dt,2) + this.getXDifference();		
			
		}
		else if (this.getOrientation() == "left") {
			new_x_pos = (double) this.getXPos() - this.getXSpeed()*100*dt
					- 0.5 * this.getXAcc() * 100 * Math.pow(dt,2) + this.getXDifference();
		}
		if (this.getNewXPos() < MIN_X_VALUE){
			this.setNewXPos(MIN_X_VALUE);
			this.setXPos(MIN_X_VALUE);
		}
		if (this.getNewXPos() > MAX_X_VALUE){
			this.setNewXPos(MAX_X_VALUE);
			this.setXPos(MAX_X_VALUE);
		}
		
		this.setXSpeed(this.getXSpeed() + dt * this.getXAcc());		
		
		setXPos(new_x_pos);
		setXDifference(new_x_pos - x_pos);
		
		if (this.getXSpeed() == 0) {
			time_since_endMove += dt;
		}
		else if (this.getXSpeed() > 0) {
			this.setTime_since_startMove(this.getTime_since_startMove() + dt);
			if (this.getTime_since_startMove() > TIME_DIFFERENT_IMAGE) {
				this.setTime_since_startMove(this.getTime_since_startMove() - TIME_DIFFERENT_IMAGE);
				if (i < m-1) {
					i += 1;
				}
				else {
					i = 0;
				}
			}
		}
	}
	
	public void advance_y(double dt){	
		if ((this.getYPos() > 0) && (!this.isFalling())){
			fall();
		}

		new_y_pos = (double) this.getYPos()
				+ this.getYSpeed()*100*dt + 0.5 * 100 *  this.getYAcc() * Math.pow(dt,2) + this.getYDifference();
		this.setySpeed(this.getYSpeed() + dt * this.getYAcc());
		if (this.getNewYPos() <= 0) {
			endFall();
			this.setNewYPos(0);
		}
		
		if (this.getNewYPos() > MAX_Y_VALUE) {
			this.setNewYPos(MAX_Y_VALUE);
			this.setySpeed(0);
		}
	
		setYPos(new_y_pos);	
		setYDifference(new_y_pos - y_pos);
	}
	
	/**
	 *  
	 * DEFENSIEF uitwerken
	 */
	public void advanceTime(double dt) throws IllegalDtException {
		if (!isValidDt(dt))
			throw new IllegalDtException(dt);
		advance_x(dt);
		advance_y(dt);	
	}	
	
	/**
	 * Checks if dt has a proper value between 0 and 0.2.
	 * @param dt
	 * @return True if dt is a value between 0 and 0.2 otherwise false.
	 * 		| if (dt > 0.2 || dt < 0)
	 * 		| then false
	 * 		| else true
	 */
	private boolean isValidDt(double dt) {
		if(dt > 0.2 || dt < 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the boolean duck, if mazub is ducked, true is returned,
	 * otherwise false
	 * 
	 * @return (duck)
	 *     __
        .'  `.
        |a_a  |
        \<_)__/    ,
         /   `-...-'\
        |    -~',   /
     ~`~\   '.-`  .' ~~^-~
     ~~` `-.~-..~` ~~`  ~`
     '~~ ~~`-  ^-~~`~ ^
	 */
	public boolean isDucked(){
		return (this.duck);
	}
	
	/**
	 * Starts the ducking of Mazub by setting the boolean duck on true 
	 * and the maxSpeed back to MAX_SPEED_DUCK
	 * @effect
	 * 			| duck == true
	 * 			| Xspeed == MAX_SPEED_DUCK
	 * DEFENSIEF uitwerken
	 */
	public void startDuck() {
		duck = true ;
		maxSpeed = MAX_SPEED_DUCK;
	}
		
	
	/**
	 * Ends the ducking of Mazub by setting the boolean duck on false 
	 * and the maxSpeed back to MAX_SPEED
	 * @effect
	 * 			| duck == false
	 * 			| Xspeed == MAX_SPEED
	 * DEFENSIEF uitwerken
	 */
	public void endDuck()  {	
		this.setDuck(false);
		this.setMaxSpeed(MAX_SPEED);
	}
	
	
	public boolean isValidSprite(Sprite[] sprites) {
		return ((sprites.length >= 8) && (sprites.length % 2 == 0));
	}
	
	/**
	 * GEEN formele documentatie nodig
	 */
	public Sprite getCurrentSprite() {
		assert isValidSprite(this.getSprite());
		m = ((this.getSprite()).length-8)/2;
		if ((this.getXSpeed()==0) && (! isDucked()) && (getTime_since_endMove() > NOT_MOVING_TIME)){
			return sprites[0];
		}
		else if ((getXSpeed()==0) && (getTime_since_endMove() > NOT_MOVING_TIME)){
			return sprites[1];
		}
		else if ((getXSpeed()==0) && (!isDucked()) && (getOrientation() == "right" )){
			return sprites[2];
		}		
		else if ((getXSpeed()==0) && (!isDucked())){
			return sprites[3];
		}
		else if ((getXSpeed() > 0) && (getOrientation() == "right" ) && (getYSpeed() > 0) && 
				(!isDucked())){
			return sprites[4];
		}
		else if ((getXSpeed() > 0) && (getYSpeed() > 0) && (!isDucked())){
			return sprites[5];
		}
		else if ((getOrientation() == "right") && (isDucked())){
			return sprites[6];
		}
		else if (isDucked()){
			return sprites[7];
		}
		else if (getOrientation() == "right"){
			return sprites[8 + i];
		}
		else {
			return sprites[8 + m + i]; 
		}
		
	}

	public void setInitStartSpeed(int initStartSpeed) {
		this.initStartSpeed = initStartSpeed;
	}
	
		
}

		
	
