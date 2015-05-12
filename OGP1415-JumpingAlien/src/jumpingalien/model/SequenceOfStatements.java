package jumpingalien.model;

import java.util.List;

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
		for(Statement statement: list) {
			statement.setSuperStatement(this);
		}
	}
	
	private int index = 0;
	private int getIndex() {
		return this.index;
	}
	private void setIndex(int i) {
		this.index = i;
	}
	
	
	@Override
	public void execute(Program program) {
		if(this.getIndex() == this.getList().size() - 1) {
			this.getList().get(this.getIndex()).execute(program);
			if(this.getList().get(this.getIndex()).isReady()) {
				this.getList().get(this.getIndex()).setNotReady();
				this.setReady();
				this.setIndex(0);
			}
		}
		else {
			this.getList().get(this.getIndex()).execute(program);
			if(this.getList().get(this.getIndex()).isReady()) {
				this.getList().get(this.getIndex()).setNotReady();
				this.setIndex(this.getIndex() + 1);
			}
		}
	}
	@Override
	public void reset() {
		this.setNotReady();
		this.setIndex(0);
		for(Statement statement: list) {
			statement.reset();
		}
	}

}
