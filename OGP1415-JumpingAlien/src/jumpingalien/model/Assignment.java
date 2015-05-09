package jumpingalien.model;

import java.util.HashMap;
import java.util.Map;

public class Assignment extends Statement {
	// TODO hier is variableType precies overbodig. Is dat normaal?
	public Assignment(String str, Type variableType, Expression exp) {
		this.setString(str);
		this.setExpression(exp);
	}
	
	private String string;
	private String getString() {
		return this.string;
	}
	private void setString(String str) {
		this.string = str;
	}
	
	private Expression expression;
	private Expression getExpression() {
		return expression;
	}
	private void setExpression(Expression expression1) {
		this.expression = expression1;
	}

	@Override
	public Map<String, Type> execute(Map<String, Type> var) {
		Map<String, Type> map = new HashMap<String, Type>();
		map.put(this.getString(), this.getExpression().evaluate());
		return map;
	}
}
