package jumpingalien.model;

public class ObjectSelf extends Expression<Object> {

	public ObjectSelf() {
	}

	//TODO dit is fout, het dit moet het gameObject zijn dat het programma oproept
	protected Object evaluate(Program program) {
		return program.getGameObject();
	}
	
}
