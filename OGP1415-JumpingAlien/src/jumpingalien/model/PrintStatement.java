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
	public Map<String, Type> execute(Map<String, Type> var) {
		System.out.println(this.getExpression1().evaluate());
		this.setReady();
		return var;
	}

	@Override
	public void reset() {
		this.setNotReady();
	}
	
}
