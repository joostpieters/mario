package program.expression;

import program.Program;
import jumpingalien.model.Type;

public class ReadVariable extends Expression<Object> {

	public ReadVariable(String name, Type type) {
		this.setName(name);
		this.setType(type);
	}
	
	private String name;	
	private String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}

	private Type type;
	private Type getType() { 
		return type;
	}
	private void setType(Type type) {
		this.type = type;
	}
	@Override
	public Object evaluate(Program program) {
//		if (this.getType() == Type.DOUBLE) {
//			program.addToEnvironment(this.getName(), 0);
//		}
//		else if (this.getType() == Type.Boolean) {
//			program.addToEnvironment(this.getName(), false);
//		}
//		else if (this.getType() == Type.Object) {
//			program.addToEnvironment(this.getName(), null);
//		}
//		else if (this.getType() == Type.Direction) {
//			program.addToEnvironment(this.getName(), false);
//		}
		return program.getEnvironment().get(this.getName());
	}

}
