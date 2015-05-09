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
	
	public abstract Map<String, Type> execute(Map<String, Type> var);
	//Nog een heleboel belangrijke abstracte functies
	
}

