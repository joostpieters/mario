package jumpingalien.model;

import jumpingalien.part3.programs.IProgramFactory.Direction;

public class IsMoving extends UnaryExpression<Boolean, GameObject> {
	
	public IsMoving(Expression<GameObject> expr, Expression<Direction> direc) {
		super(expr);
		this.setDirection(direc);
	}
	
	private Expression<Direction> direction;
	
	private Expression<Direction> getDirection() {
		return direction;
	}
	
	private void setDirection(Expression<Direction> direc) {
		this.direction = direc;
	}
	
	@Override
	protected Boolean evaluate() {
		if (this.getDirection().evaluate() == Direction.RIGHT) {
			return ((this.getExpression1().evaluate().getXSpeed() > 0) && 
					(this.getExpression1().evaluate().getOrientation() == Orientation.RIGHT));
		}
		else if (this.getDirection().evaluate() == Direction.LEFT) {
			return ((this.getExpression1().evaluate().getXSpeed() > 0) && 
					(this.getExpression1().evaluate().getOrientation() == Orientation.LEFT));
		}
		else if (this.getDirection().evaluate() == Direction.UP) {
			return (this.getExpression1().evaluate().getYSpeed() > 0);
		}
		else if (this.getDirection().evaluate() == Direction.DOWN) {
			return (this.getExpression1().evaluate().getYSpeed() < 0);
		}
		return false;		
	}

}
