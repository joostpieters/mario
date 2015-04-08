package jumpingalien.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class School {
	public School() {
		
	}
	private List<Slime> members = new CopyOnWriteArrayList<Slime>();
	
	private List<Slime> getMembers() {
		return this.members;
	}
	
	public int getLenght() {
		return this.getMembers().size();
	}
	
	public void addSlime(Slime slime) {
		assert !this.getMembers().contains(slime);
		slime.setHitpoints(slime.getHitpoints() + slime.getSchool().getLenght() - this.getLenght());
		for (Slime oldMember: slime.getSchool().getMembers()) {
			oldMember.setHitpoints(oldMember.getHitpoints() - 1);
		}
		for (Slime newMember: this.getMembers()) {
			newMember.setHitpoints(newMember.getHitpoints() + 1);
		}
		slime.setSchool(this);
		this.members.add(slime);
	}
	
	
	
}
