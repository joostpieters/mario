package jumpingalien.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import be.kuleuven.cs.som.annotate.Raw;

public class School {
	public School() {
		
	}
	private List<Slime> members = new CopyOnWriteArrayList<Slime>();
	
	public List<Slime> getMembers() {
		return this.members;
	}
	public void newSlime(Slime slime) {
		this.members.add(slime);
	}
	
	public int getLength() {
		return this.getMembers().size();
	}
	
	public void addSlime(Slime slime) {
		assert ( ! this.getMembers().contains(slime));
		slime.setHitpoints(slime.getHitpoints() + slime.getSchool().getLength() - this.getLength());
		for (Slime oldMember: slime.getSchool().getMembers()) {
			oldMember.setHitpoints(oldMember.getHitpoints() - 1);
		}
		for (Slime newMember: this.getMembers()) {
			newMember.setHitpoints(newMember.getHitpoints() + 1);
		}
		// TODO dit is nogal rommelig
		slime.getSchool().members.remove(slime);
		slime.setSchool(this);
		this.members.add(slime);
	}
	public void removeSlime(@Raw Slime slime) {
		assert this.hasSlime(slime);
		assert slime != null;
		this.members.remove(slime);
		
	}
	private boolean hasSlime(Slime slime) {
		return this.members.contains(slime);
	}
}
