package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.util.Sprite;

/**
 * A class that describes the movement of Mazub's evil twin Buzam
 * @author Ward Romanus, Pieter Van den Berghe
 *
 */
public class Buzam extends Mazub 
					implements InterfaceMazubBuzam {

	public Buzam(int xPos, int yPos, Sprite[] sprites)
			throws IllegalPositionException, IllegalSpriteException {
		super(xPos, yPos, sprites);
		this.setInitStartSpeed(Mazub.getStartSpeed());
		this.setMaxSpeed(Mazub.getMaxMovingSpeed());
		this.setHitpoints(Mazub.getInitHitpoints());
	}
	
	@Override
	@Raw
	public void advanceTime(double dt) throws IllegalDtException {
		if ( ! isValidDt(dt))
			throw new IllegalDtException(dt);	
	}
	
	
}
