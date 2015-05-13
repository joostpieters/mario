package jumpingalien.model;

public class ReadVariable extends Expression {

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
		program.addToEnvironment(this.getName(), this.getType());
		return program.getEnvironment().get(this.getName());
	}

}
