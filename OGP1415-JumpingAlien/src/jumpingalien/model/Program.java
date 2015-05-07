package jumpingalien.model;

import java.util.ArrayList;


public class Program {
	public Program() {
		
	}
	
	private ArrayList<Statement> statements = new ArrayList<Statement>();
	
	private ArrayList<Statement> getStatements() {
		return this.getStatements();
	}
	
	private int index = 0;
	
	private int getIndex() {
		return index;
	}
	
	private void setIndex(int i) {
		this.index = i;
	}
	
	private void addStatement(Statement s) {
		this.getStatements().add(s);
	}
	
	// hier mss nen iterator 
	private Statement nextStatement() {
		if (hasNextStatement()) {
			return this.getStatements().get(getIndex());
		}
		else {
			return null;
		}
	}
	
	private boolean hasNextStatement() {
		if(this.getIndex() >= this.getStatements().size()) {
			return false;
		}
		return true;
	}
	
	

	public boolean isWellFormed() {
		return true;
	}


}
