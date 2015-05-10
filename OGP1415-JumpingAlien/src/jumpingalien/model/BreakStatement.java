package jumpingalien.model;

import java.util.Map;

public class BreakStatement extends Statement {

	public BreakStatement() {
	}
	
	// Pas doen als for each af is mss
	// TODO nog de statement waarin het zit laten breaken
	@Override
	public Map<String, Type> execute(Map<String, Type> var) {
		this.setReady();
		this.getSuperStatement().setIndex(0);
		return var;
	}

}
