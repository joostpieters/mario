package jumpingalien.model;

import java.util.List;
import java.util.Map;

import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.SourceLocation;


//@SuppressWarnings("rawtypes")
public class ProgramFactory implements IProgramFactory<Expression, Statement, Type, Program> {

	@Override
	public Expression createReadVariable(String variableName,
			Type variableType, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Double> createDoubleConstant(double value,
			SourceLocation sourceLocation) {
		return new Constant(value);
	}

	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createNull(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSelf(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createDirectionConstant(
			jumpingalien.part3.programs.IProgramFactory.Direction value,
			SourceLocation sourceLocation) {
		return new DirectionExpression(value);
	}

	@Override
	public Expression createAddition(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Addition(left, right);
	}

	@Override
	public Expression createSubtraction(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Subtraction(left, right);

	}

	@Override
	public Expression createMultiplication(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Multiplication(left, right);

	}

	@Override
	public Expression createDivision(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Division(left, right);

	}

	@Override
	public Expression createSqrt(Expression expr, SourceLocation sourceLocation) {
		return new DoubleExpression(DoubleOperation.SQRT, expr);
	}

	@Override
	public Expression createRandom(Expression maxValue,
			SourceLocation sourceLocation) {
		return new DoubleExpression(DoubleOperation.RANDOM, maxValue);
	}

	@Override
	public Expression createAnd(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new AndBool(left, right);
	}

	@Override
	public Expression createOr(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new OrBool(left, right);
	}

	@Override
	public Expression createNot(Expression expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createLessThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return LessThan(left, right);
	}

	@Override
	public Expression createLessThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		return LessEquals(left, right);
	}

	@Override
	public Expression createGreaterThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return GreaterThan(left, right);
	}

	@Override
	public Expression createGreaterThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		return GreaterEquals(left, right);
	}

	@Override
	public Expression createEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return Equals(left, right);
	}

	@Override
	public Expression createNotEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return NotEquals(left, right);
	}

	@Override
	public Expression createGetX(Expression expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetY(Expression expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetWidth(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetHeight(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetHitPoints(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetTile(Expression x, Expression y,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createSearchObject(Expression direction,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsMazub(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsShark(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsSlime(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsPlant(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsDead(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsTerrain(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsPassable(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsWater(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsMagma(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsAir(Expression expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsMoving(Expression expr, Expression direction,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsDucking(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsJumping(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createAssignment(String variableName, Type variableType,
			Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createWhile(Expression condition, Statement body,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createForEach(
			String variableName,
			jumpingalien.part3.programs.IProgramFactory.Kind variableKind,
			Expression where,
			Expression sort,
			jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody,
			Statement elseBody, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStartRun(Expression direction,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStopRun(Expression direction,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStartJump(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStopJump(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStartDuck(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStopDuck(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createWait(Expression duration,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSkip(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSequence(List<Statement> statements,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getDoubleType() {
		return Type.DOUBLE;
	}

	@Override
	public Type getBoolType() {
		return Type.BOOL;
	}

	@Override
	public Type getGameObjectType() {
		return Type.OBJECT;
	}

	@Override
	public Type getDirectionType() {
		return Type.DIRECTION;
	}

	@Override
	public Program createProgram(Statement mainStatement,
			Map<String, Type> globalVariables) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
