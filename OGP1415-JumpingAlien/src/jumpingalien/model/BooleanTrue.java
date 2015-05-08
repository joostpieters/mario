package jumpingalien.model;

public class BooleanTrue<T> extends Expression {

	public BooleanTrue() {
		
	}

	@Override
	protected Boolean evaluate() {
		return true;
	}

}
