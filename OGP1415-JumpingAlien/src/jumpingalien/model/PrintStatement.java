package jumpingalien.model;

import java.util.Map;

public class PrintStatement<S> extends Statement {

	public PrintStatement(Expression<S> expr) {
		this.setExpression1(expr);
		}
		
	private Expression<S> expression1;
	
	protected Expression<S> getExpression1() {
		return expression1;
	}
	
	private void setExpression1(Expression<S> expr) {
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
	
}
