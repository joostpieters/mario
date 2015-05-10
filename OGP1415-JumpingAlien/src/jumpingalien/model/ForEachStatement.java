package jumpingalien.model;

import java.util.Map;

import jumpingalien.part3.programs.IProgramFactory.Kind;

public class ForEachStatement extends Statement {

	public ForEachStatement(String variableName,
			jumpingalien.part3.programs.IProgramFactory.Kind variableKind,
			Expression<Boolean> where,
			Expression<Boolean> sort,
			jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection,
			Statement body) {
		
		this.setBody(body);
	}
	
	private String variableName;
	private String getVariableName() {
		return variableName;
	}
	private void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	private Kind kind;
	private Kind getKind() {
		return kind;
	}
	private void setKind(Kind kind) {
		this.kind = kind;
	}
	
	private Expression<Boolean> where;
	private Expression<Boolean> getWhere() {
		return where;
	}
	private void setWhere(Expression<Boolean> where) {
		this.where = where;
	}
	
	private Expression<Boolean> sort;
	private Expression<Boolean> getSort() {
		return sort;
	}
	private void setSort(Expression<Boolean> sort) {
		this.sort = sort;
	}
	
	
	
	
	
	private Statement body;
	private Statement getBody() {
		return this.body;
	}
	private void setBody(Statement stat) {
		this.body = stat;
		stat.setSuperStatement(this);
	}
	
//	private boolean inBody;
//	private boolean getInBody() {
//		return inBody;
//	}
//	private void setInBody(boolean b) {
//		this.inBody = b;
//	}
	
	// TODO hier overal iets zetten
	@Override
	public Map<String, Type> execute(Map<String, Type> var) {
		switch(this.getKind()) {
		case MAZUB: this.getGameObject().getWorld().getAlien() -> {}
			break;
		case BUZAM:
			break;
		case PLANT:
			break;
		case SHARK:
			break;
		case SLIME:
			break;
		case TERRAIN:
			break;
		case ANY:
			break;
		default: //TODO hier iets zetten
			break;
		}
		return var;
	}
	
}
