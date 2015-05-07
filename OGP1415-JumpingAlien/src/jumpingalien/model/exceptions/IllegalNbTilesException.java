package jumpingalien.model.exceptions;

@SuppressWarnings("serial")
public class IllegalNbTilesException extends Exception {
	
	/**
	 * the given number of tiles
	 */
	private final int nbTiles;

	/**
	 * initialize this new illegal number of tiles exception 
	 * with a given number of tiles
	 * @param nbTiles
	 * 			the number of tiles
	 * @post	the value of the new illegal max number of tiles
	 * 			 exception is equal to the given number of tiles
	 * 			| new.getNbTiles = nbTiles
	 */
	public IllegalNbTilesException(int nbTiles) {
		this.nbTiles = nbTiles;
	}
	
	/**
	 * returns the amount of tiles
	 * @return number of tiles
	 * 			| nbTiles
	 */
	public double getNbTiles() {
		return nbTiles;
	}
}
