package jumpingalien.model.program.statement;

import jumpingalien.model.program.Program;
import jumpingalien.model.program.expression.Expression;


public class IfStatement extends Statement {

	public IfStatement(Expression<Boolean> condition, Statement ifBody, Statement elsebody) {
		this.setCondition(condition);
		this.setIfBody(ifBody);
		if (elsebody != null) {
			this.setElseBody(elsebody);

		}
	}
	
	private Expression<Boolean> condition;
	protected Expression<Boolean> getCondition() {
		return condition;
	}
	private void setCondition(Expression<Boolean> expr) {
		this.condition = expr;
	}
	
	private Statement ifBody;
	public Statement getIfBody() {
		return this.ifBody;
	}
	private void setIfBody(Statement stat) {
		this.ifBody = stat;
		ifBody.setSuperStatement(this);
	}
	
	private Statement elseBody;
	public Statement getElseBody() {
		return this.elseBody;
	}
	private void setElseBody(Statement stat) {
		this.elseBody = stat;
		elseBody.setSuperStatement(this);
	}
	
	private int index;
	private int getIndex() {
		return index;
	}
	private void setIndex(int i) {
		this.index = i;
	}
	
	@Override
	public void execute(Program program) {
		if (this.getIndex() == 0) {
			if(this.getCondition().evaluate(program)) {
				this.setIndex(1);
			}
			else if (this.getElseBody() != null){
				this.setIndex(2);
			}
			else {
				this.setIndex(0);
				this.setReady();
			}
		}
		else if (this.getIndex() == 1) {
			this.getIfBody().execute(program);
			if(this.getIfBody().isReady()) {
				this.getIfBody().setNotReady();
				this.setIndex(0);
				this.setReady();
			}
		}
		else  {
			this.getElseBody().execute(program);
			if(this.getElseBody().isReady()) {
				this.getElseBody().setNotReady();
				this.setIndex(0);
				this.setReady();
			}
		}
	}
	@Override
	public void reset() {
		this.setNotReady();
		this.setIndex(0);
		this.getIfBody().reset();
		this.getElseBody().reset();
	}
	@Override
	public boolean isWellFormed() {
		if (this.getElseBody() != null) {
			return this.getIfBody().isWellFormed() && this.getElseBody().isWellFormed();
		}
		else {
			return this.getIfBody().isWellFormed();
		}
				
	}
	

}
