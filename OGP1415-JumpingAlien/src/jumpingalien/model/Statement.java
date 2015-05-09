package jumpingalien.model;

import java.util.Map;

public abstract class Statement {
	
	public Statement(){
		
	}
	
	private World world;
	
	protected World getWorld() {
		return world;
	}
	
	protected void setWorld(World world) {
		this.world = world;
	}
	
	private boolean ready = false;
	public boolean isReady() {
		return this.ready;
	}
	public void setReady() {
		this.ready = true;
	}
	public void setNotReady() {
		this.ready = false;
	}
	
	public abstract Map<String, Type> execute(Map<String, Type> var);
	//Nog een heleboel belangrijke abstracte functies
	
}

