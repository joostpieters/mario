package jumpingalien.model;

import java.util.Map;

public class BreakStatement extends Statement {

	public BreakStatement() {
	}
	
	
	// TODO nog de statement waarin het zit laten breaken ->klopt dit?
	@Override
	public Map<String, Type> execute(Map<String, Type> var) {
		this.getSuperStatement().setReady();
		return var;
	}

}
