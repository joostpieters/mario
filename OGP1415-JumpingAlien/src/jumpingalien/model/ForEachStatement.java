

package jumpingalien.model;


import program.statement.Statement;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;

public class ForEachStatement extends LoopStatement {

	public ForEachStatement(String variableName,
			jumpingalien.part3.programs.IProgramFactory.Kind variableKind,
			Expression<Boolean> where,
			Expression<Boolean> sort,
			jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection,
			Statement body) {
		this.setWhere(where);
		this.setSort(sort);
		this.setKind(variableKind);
		this.setSortDirection(sortDirection);
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
	//	where.setGameObject(this.getGameObject());
	}
	
	private Expression<Boolean> sort;
	private Expression<Boolean> getSort() {
		return sort;
	}
	private void setSort(Expression<Boolean> sort) {
		this.sort = sort;
	//	sort.setGameObject(this.getGameObject());
	}
	
	private SortDirection sortDirection;
	private SortDirection getSortDirection() {
		return sortDirection;
	}
	private void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}

	private Statement body;
	private Statement getBody() {
		return this.body;
	}
	private void setBody(Statement stat) {
		this.body = stat;
		stat.setSuperStatement(this);
	}
	
	// TODO hier overal iets zetten
	@Override
	public void execute(Program program) {
		switch(this.getKind()) {
		case MAZUB:
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
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
}
