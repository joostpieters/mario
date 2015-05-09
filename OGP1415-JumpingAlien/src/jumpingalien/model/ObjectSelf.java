package jumpingalien.model;

public class ObjectSelf extends Expression<Object> {

	public ObjectSelf() {
	}

	//TODO ik geloof dat dit de boedoeling is, want die krijgt niets mee in ProgramFactory
	@Override
	protected Object evaluate() {
		return this;
	}
	
}
