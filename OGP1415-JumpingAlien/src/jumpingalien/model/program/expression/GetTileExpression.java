package jumpingalien.model.program.expression;

import jumpingalien.model.Tile;
import jumpingalien.model.Type;
import jumpingalien.model.World;
import jumpingalien.model.program.Program;

public class GetTileExpression extends BinaryExpression<Tile, Double> {

	public GetTileExpression(Expression<Double> expression1,
			Expression<Double> expression2) {
		super(expression1, expression2);
		if (expression1.getType() != Type.DOUBLE || expression2.getType() != Type.DOUBLE) {
			throw new IllegalArgumentException();
		}
		this.setType(Type.OBJECT);
	}

	@Override
	public Tile evaluate(Program program) {
		World world = program.getGameObject().getWorld();
		int xPos = this.getExpression1().evaluate(program).intValue();
		int yPos = this.getExpression2().evaluate(program).intValue();
		int xCor = world.getTileOfPixels(xPos, yPos)[0];
		int yCor = world.getTileOfPixels(xPos, yPos)[1];
		return new Tile(xCor, yCor, world);
	}
}
