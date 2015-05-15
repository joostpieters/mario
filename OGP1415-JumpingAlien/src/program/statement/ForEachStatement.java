package program.statement;

import java.util.ArrayList;
import java.util.Collection;

import program.Program;
import program.expression.Expression;
import jumpingalien.model.SuperObject;
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
	}
	
	private Expression<Boolean> sort;
	private Expression<Boolean> getSort() {
		return sort;
	}
	private void setSort(Expression<Boolean> sort) {
		this.sort = sort;
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
	
	private Collection<SuperObject> listObjectByKind(Program program) {
		Collection<SuperObject> list = new ArrayList<SuperObject>();
		switch(this.getKind()) {
		case MAZUB:
			list.add(program.getGameObject().getWorld().getAlien());
			break;
		case BUZAM:
			if(program.getGameObject().getWorld().getBuzam() != null) {
				list.add(program.getGameObject().getWorld().getBuzam());
			}
			break;
		case PLANT:
			list.addAll(program.getGameObject().getWorld().getPlants());
			break;
		case SHARK:
			list.addAll(program.getGameObject().getWorld().getSharks());
			break;
		case SLIME:
			list.addAll(program.getGameObject().getWorld().getSlimes());
			break;
		case TERRAIN:
			list.addAll(program.getGameObject().getWorld().listAllTiles());
			break;
		case ANY:
			list.add(program.getGameObject().getWorld().getAlien());
			if(program.getGameObject().getWorld().getBuzam() != null) {
				list.add(program.getGameObject().getWorld().getBuzam());
			}
			list.addAll(program.getGameObject().getWorld().getPlants());
			list.addAll(program.getGameObject().getWorld().getSlimes());
			list.addAll(program.getGameObject().getWorld().getSharks());
			list.addAll(program.getGameObject().getWorld().listAllTiles());
			break;
		default: 
			throw new IllegalArgumentException();
		}
		return list;
	}
	
	// TODO hier overal iets zetten en nog eens goed nadenken over deze kutklasse :)
	@Override
	public void execute(Program program) {
		System.out.println("ohnee dat is heeel vervelend (zie for each)");
		
	}
	@Override
	public void reset() {
		this.setNotReady();
		this.setInBody(false);
		
	}
	
}
