package jumpingalien.model;

/**
 * 
 * A class to check for an illegal school
 * 
 * @version 1.0
 * @author Pieter Van den Berghe, Ward Romanus 
 *
 */
@SuppressWarnings("serial")
public class IllegalSchoolException extends Exception {
	/**
	 * The school
	 */	
	private final School school;

	/**
	 * initialize this new illegal school exception with a given school
	 * @param	school
	 * @post	the value of the new illegal school exception is equal to 
	 * 			the given school
	 * 			| this.school = school
	 */
	public IllegalSchoolException(School school){
		this.school = school;
	}
	
	/**
	 * returns the school
	 * @return school
	 */
	public School getSchool() {
		return this.school;
	}

}
