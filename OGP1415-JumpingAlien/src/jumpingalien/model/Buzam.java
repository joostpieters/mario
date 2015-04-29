package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.util.Sprite;

/**
 * A class that describes the movement of Mazub's evil twin Buzam
 * @author Ward Romanus, Pieter Van den Berghe
 *
 */
public class Buzam extends GameObject 
					implements InterfaceMazubBuzam {

	protected Buzam(int xPos, int yPos, Sprite[] sprites)
			throws IllegalPositionException, IllegalSpriteException {
		super(xPos, yPos, sprites);
	}
	
	
}
