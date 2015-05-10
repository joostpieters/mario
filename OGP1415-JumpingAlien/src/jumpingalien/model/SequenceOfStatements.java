package jumpingalien.model;

import java.util.List;
import java.util.Map;

public class SequenceOfStatements extends Statement {

	public SequenceOfStatements(List<Statement> statements) {
		this.setList(statements);
	}
	
	private List<Statement> list;
	private List<Statement> getList() {
		return this.list;
	}
	private void setList(List<Statement> list) {
		this.list = list;
	}

	@Override
	public Map<String, Type> execute(Map<String, Type> var) {
		for (Statement statement: this.getList()) {
			statement.execute(var);
		}
		return var;
	}

}
