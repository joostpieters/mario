package jumpingalien.model;
/**
 * 
 * A class to check for an illegal Visible Window
 * 
 * 
 * @version 1.0
 * @author Pieter Van den Berghe, Ward Romanus
 * 
 *
 */
@SuppressWarnings("serial")
public class IllegalVisibleWindowException extends Exception {
	/**
	 * the (vertical) visible height of the screen;
	 */
	private final int visibleHeight;
	/**
	 * the (horizontal) visible width of the screen;
	 */
	private final int visibleWidth;
	/**
	 * initialize this new illegal visibleHeight exception with a given
	 * visible width 
	 * @param visibleHeight
	 * @post the value of the new illegal visible height exception is equal
	 * 			to the given visibleHeight
	 * 			| new.getVisibleHeight = visibleHeight
	 */
	public IllegalVisibleWindowException(int visibleHeight, int visibleWidth) {
		this.visibleHeight = visibleHeight;
		this.visibleWidth = visibleWidth;
	}
	
	/**
	 * the visible height of the screen
	 * @return visibleHeight
	 */
	public int getVisibleHeight() {
		return visibleHeight;
	}
	/**
	 * the visible width of the screen
	 * @return visibleWidth
	 */
	public int getVisibleWidth() {
		return visibleWidth;
	}
}
