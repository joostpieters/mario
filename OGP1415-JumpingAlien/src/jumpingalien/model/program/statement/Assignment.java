package jumpingalien.model.program.statement;

import jumpingalien.model.Type;
import jumpingalien.model.program.Program;
import jumpingalien.model.program.expression.Expression;


public class Assignment extends Statement {
	public Assignment(String str, Type variableType, Expression<?> value) {
		this.setString(str);
		this.setExpression(value);
		if (value.getType() != variableType) {
			throw new IllegalArgumentException();
		}
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
	@Override
	public boolean isWellFormed() {
		return true;
	}
}
