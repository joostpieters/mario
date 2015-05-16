package jumpingalien.model.program.expression;

import java.util.ArrayList;
import java.util.Collection;

import jumpingalien.model.SuperObject;
import jumpingalien.model.program.Program;
import jumpingalien.part3.programs.IProgramFactory.Direction;

public class SearchObject extends UnaryExpression<SuperObject, Direction> {

	public SearchObject(Expression<Direction> expression1) {
		super(expression1);
	}
	
	
	private double getDistance(double pos1, double pos2) {
		return Math.abs(pos1 - pos2);
	}
	
	@Override
	public SuperObject evaluate(Program program) {
		Collection<SuperObject> list = new ArrayList<SuperObject>();
		list.addAll(program.getGameObject().getWorld().listAllGameObjects());
		list.addAll(program.getGameObject().getWorld().listAllImpassableTiles());
		list.remove(program.getGameObject());
		double xPos = program.getGameObject().getXPos();
		double yPos = program.getGameObject().getYPos();
		double xDim = program.getGameObject().getXDim();
		double yDim = program.getGameObject().getYDim();
		
		double distance = Double.POSITIVE_INFINITY;
		SuperObject object = null;

		for (SuperObject superObject: list) {
			switch(this.getExpression1().evaluate(program)) {
				case RIGHT: 
					if(xPos <= superObject.getXPos() && !((yPos + yDim -1 < superObject.getYPos()) 
							|| (superObject.getYPos()  + superObject.getYDim() - 1 < yPos))) {
						if (this.getDistance(xPos, superObject.getXPos()) < distance) {
							object = superObject;
						}
					}
					break;	
				case LEFT:
					if(xPos + xDim >= superObject.getXPos() + superObject.getXDim() 
							&& !((yPos + yDim -1 < superObject.getYPos()) 
							|| (superObject.getYPos()  + superObject.getYDim() - 1 < yPos))) {
						if (this.getDistance(xPos + xDim, superObject.getXPos() + superObject.getXDim()) < distance) {
							object = superObject;
						}
					}
					break;			
				case UP:
					if(yPos <= superObject.getYPos() && !((xPos + xDim -1 < superObject.getXPos()) 
							|| (superObject.getXPos()  + superObject.getXDim() - 1 < xPos))) {
						if (this.getDistance(yPos, superObject.getYPos()) < distance) {
							object = superObject;
						}
					}
					break;
				case DOWN:
					if(yPos + yDim >= superObject.getYPos() + superObject.getYDim() 
							&& !((xPos + xDim -1 < superObject.getXPos()) 
							|| (superObject.getXPos()  + superObject.getXDim() - 1 < xPos))) {
						if (this.getDistance(yPos + yDim, superObject.getYPos() + superObject.getYDim()) < distance) {
							object = superObject;
						}
					}
					break;
				default:
					break;
			}
		}
		return object;
	}

}
