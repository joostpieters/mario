package jumpingalien.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class that describes a school of slimes
 * @author Ward Romanus, Pieter Van den Berghe
 *
 */
public class School {
	
	/**
	 * the School containing a number of Slimes
	 */
	public School() {
		
	}
	
	/**
	 * a list containing all the Slimes in this school
	 */
	private List<Slime> members = new CopyOnWriteArrayList<Slime>();
	
	/**
	 * returns the members of this School
	 * @return members
	 */
	@Basic 
	public List<Slime> getMembers() {
		return new CopyOnWriteArrayList<Slime>(this.members);
	}
	
	/**
	 * adds the given Slime to the members of this school
	 * @param slime
	 * 			the Slime to add
	 * @effect slime is added to the members
	 * 			| this.members.add(slime)
	 */
	public void newSlime(Slime slime) {
		assert slime != null;
		assert ( ! this.hasSlime(slime));
		this.members.add(slime);
	}
	
	/**
	 * returns the size of this School
	 * @return the size (amount of members)
	 * 			| this.getMembers().size()
	 */
	@Basic 
	public int getSize() {
		return this.getMembers().size();
	}
	
	/**
	 * adds a slime to this School
	 * @param slime
	 * 			the Slime to add
	 * @pre the slime should not be null
	 * 			| slime != null
	 * @pre the slime may not be already part of the School
	 * 			| ! members.contains(slime)
	 * @effect the hitpoints of the Slime that joins the school are adapted
	 * 			| slime.setHitpoints(slime.getHitpoints()  - slime.getSchool().getSize() + 1 + this.getSize())
	 * @effect every Slime that was already part of the School wins 1 hitpoint
	 * 			| for each Slime oldMember: school.getMembers():
	 * 			| 	oldmember.setHitpoints(oldMember.getHitpoints() + Slime.getSchoolDamage())
	 * @effect every new member of the School loses 1 hitpoint
	 * 			| for each Slime newMember: this.getMembers():
	 * 			| 	newMember.setHitpoints(newMember.getHitpoints() - Slime.getSchoolDamge()))
	 * @effect the Slime slime gets removed from it's old school, it's school is set to this and slime is added
	 * 			| slime.getSchool().removeSlime(slime)
	 * 			| slime.setSchool(this)
	 * 			| this.newSlime(slime)
	 */
	public void addSlime(Slime slime) {
		assert slime != null;
		assert ( ! this.hasSlime(slime));
		slime.setHitpoints(slime.getHitpoints() - slime.getSchool().getSize() + 1 + this.getSize());
		for (Slime oldMember: slime.getSchool().getMembers()) {
			if (oldMember != slime) {
				oldMember.setHitpoints(oldMember.getHitpoints() + Slime.getSchoolDamage());
			}
		}
		for (Slime newMember: this.getMembers()) {
			newMember.setHitpoints(newMember.getHitpoints() - Slime.getSchoolDamage());
		}
		slime.getSchool().removeSlime(slime);
		slime.setSchool(this);
		this.newSlime(slime);
	}
	
	/**
	 * removes the given Slime slime from this school
	 * @param slime
	 * 			the Slime to remove
	 * @pre this School must have a Slime slime
	 * 			| assert hasSlime(slime)
	 * @pre the given Slime may not be equal to null
	 * 			| slime != null
	 * @effect the given Slime slime gets removed from this School
	 * 			| this.members.remove(slime)
	 */
	public void removeSlime(@Raw Slime slime) {
		assert this.hasSlime(slime);
		assert slime != null;
		this.members.remove(slime);
		
	}
	
	/**
	 * checks if ths Slime slime is part of this School
	 * @param slime
	 * 			the Slime to check
	 * @return this.members.contains(slime)
	 */
	private boolean hasSlime(Slime slime) {
		return this.members.contains(slime);
	}
	
	
}
