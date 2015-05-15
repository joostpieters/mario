package program.statement;

import jumpingalien.model.Type;
import program.Program;
import program.expression.Expression;


public class Assignment extends Statement {
	// TODO hier is variableType precies overbodig. Is dat normaal?
	// Daarmee kunnen we wel checken of de expression/value van de juiste vorm is
	public Assignment(String str, Type variableType, Expression<?> value) {
		this.setString(str);
		this.setExpression(value);
	}
	
	private String string;
	private String getString() {
		return this.string;
	}
	private void setString(String str) {
		this.string = str;
	}
	
	private Expression<?> expression;
	private Expression<?> getExpression() {
		return expression;
	}
	private void setExpression(Expression<?> expression1) {
		this.expression = expression1;
	}

	@Override
	public void execute(Program program) {
		program.getEnvironment().put(this.getString(), this.getExpression().evaluate(program));
		this.setReady();
	}
	
	@Override
	public void reset() {
		this.setNotReady();
	}
}
