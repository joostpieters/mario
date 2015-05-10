package jumpingalien.model;

import java.util.Map;

public class SkipStatement extends Statement {

	public SkipStatement() {
	}
	
	@Override
	public Map<String, Type> execute(Map<String, Type> var) {
		this.setReady();
		return var;
	}
	
}
