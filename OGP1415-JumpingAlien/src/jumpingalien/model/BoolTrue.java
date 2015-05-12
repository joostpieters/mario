package jumpingalien.model;

public class BoolTrue extends Expression<Boolean> {
	
	public BoolTrue() {
		
	}
	
	public Boolean evaluate(Program program) {
		return  true;
	}
}
