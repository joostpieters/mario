package jumpingalien.model;

public class DirectionExpression extends Expression {
	public DirectionExpression(jumpingalien.part3.programs.IProgramFactory.Direction value) {
		this.setValue(value);
	}
	
	private jumpingalien.part3.programs.IProgramFactory.Direction value;
	
	private jumpingalien.part3.programs.IProgramFactory.Direction getValue() {
		return value;
	}
	private void setValue(jumpingalien.part3.programs.IProgramFactory.Direction value) {
		this.value = value;
	}
	
	public jumpingalien.part3.programs.IProgramFactory.Direction evaluates() {
		return this.getValue();
	}
}
