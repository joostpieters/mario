package jumpingalien.model.program.statement;

import jumpingalien.model.program.Program;
import jumpingalien.model.program.expression.Expression;


public class PrintStatement extends Statement {

	public PrintStatement(Expression<?> expr) {
		this.setExpression1(expr);
		}
		
	private Expression<?> expression1;
	
	protected Expression<?> getExpression1() {
		return expression1;
	}
	
	private void setExpression1(Expression<?> expr) {
		this.expression1 = expr;
	}
	
	@Override
	public void execute(Program program) {
		System.out.println(this.getExpression1().evaluate(program));
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
