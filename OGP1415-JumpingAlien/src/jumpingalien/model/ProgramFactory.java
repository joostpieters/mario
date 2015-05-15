package jumpingalien.model;

import java.util.List;
import java.util.Map;

import program.Program;
import program.expression.Addition;
import program.expression.AndBool;
import program.expression.BoolFalse;
import program.expression.BoolTrue;
import program.expression.Constant;
import program.expression.DirectionExpression;
import program.expression.Division;
import program.expression.Equals;
import program.expression.Expression;
import program.expression.GetHeightObject;
import program.expression.GetHpObject;
import program.expression.GetTileExpression;
import program.expression.GetWidthObject;
import program.expression.GetX;
import program.expression.GetY;
import program.expression.GreaterEquals;
import program.expression.GreaterThan;
import program.expression.IsAir;
import program.expression.IsDead;
import program.expression.IsDucking;
import program.expression.IsJumping;
import program.expression.IsMagma;
import program.expression.IsMazub;
import program.expression.IsMoving;
import program.expression.IsPassable;
import program.expression.IsPlant;
import program.expression.IsShark;
import program.expression.IsSlime;
import program.expression.IsTerrain;
import program.expression.IsWater;
import program.expression.LessEquals;
import program.expression.LessThan;
import program.expression.Multiplication;
import program.expression.NotBool;
import program.expression.NotEquals;
import program.expression.ObjectNull;
import program.expression.ObjectSelf;
import program.expression.OrBool;
import program.expression.RandomDouble;
import program.expression.ReadVariable;
import program.expression.SearchObject;
import program.expression.SqrtDouble;
import program.expression.Subtraction;
import program.statement.Assignment;
import program.statement.BreakStatement;
import program.statement.ForEachStatement;
import program.statement.IfStatement;
import program.statement.PrintStatement;
import program.statement.SequenceOfStatements;
import program.statement.SkipStatement;
import program.statement.StartDuck;
import program.statement.StartJump;
import program.statement.StartRun;
import program.statement.Statement;
import program.statement.StopDuck;
import program.statement.StopJump;
import program.statement.StopRun;
import program.statement.WaitStatement;
import program.statement.WhileStatement;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ProgramFactory implements IProgramFactory<Expression<?>, Statement, Type, Program> {

	@Override
	public Expression<?> createReadVariable(String variableName,
			Type variableType, SourceLocation sourceLocation) {
		return new ReadVariable(variableName, variableType);
	}

	@Override
	public Expression<Double> createDoubleConstant(double value,
			SourceLocation sourceLocation) {
		return new Constant(value);
	}

	@Override
	public Expression<Boolean> createTrue(SourceLocation sourceLocation) {
		return new BoolTrue();
	}

	@Override
	public Expression<Boolean> createFalse(SourceLocation sourceLocation) {
		return new BoolFalse();
	}

	@Override
	public Expression<Object> createNull(SourceLocation sourceLocation) {
		return new ObjectNull();
	}

	@Override
	public Expression<Object> createSelf(SourceLocation sourceLocation) {
		return new ObjectSelf();
	}

	@Override
	public Expression<jumpingalien.part3.programs.IProgramFactory.Direction> createDirectionConstant(
			jumpingalien.part3.programs.IProgramFactory.Direction value,
			SourceLocation sourceLocation) {
		return new DirectionExpression(value);
	}

	@Override
	public Expression<Double> createAddition(Expression<?> left, Expression<?> right,
			SourceLocation sourceLocation) {
		
		return new Addition((Expression<Double>) left, (Expression<Double>) right);
	}

	@Override
	public Expression<Double> createSubtraction(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Subtraction(left, right);

	}

	@Override
	public Expression<Double> createMultiplication(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Multiplication(left, right);

	}

	@Override
	public Expression<Double> createDivision(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Division(left, right);

	}

	@Override
	public Expression<Double> createSqrt(Expression expr, SourceLocation sourceLocation) {
		return new SqrtDouble(expr);
	}

	@Override
	public Expression<Double> createRandom(Expression maxValue,
			SourceLocation sourceLocation) {
		return new RandomDouble(maxValue);
	}

	@Override
	public Expression<Boolean> createAnd(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new AndBool(left, right);
	}

	@Override
	public Expression<Boolean> createOr(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new OrBool(left, right);
	}

	@Override
	public Expression<Boolean> createNot(Expression expr, SourceLocation sourceLocation) {
		return new NotBool(expr);
	}

	@Override
	public Expression<Boolean> createLessThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new LessThan(left, right);
	}

	@Override
	public Expression<Boolean> createLessThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		return new LessEquals(left, right);
	}

	@Override
	public Expression<Boolean> createGreaterThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new GreaterThan(left, right);
	}

	@Override
	public Expression<Boolean> createGreaterThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		return new GreaterEquals(left, right);
	}

	@Override
	public Expression<Boolean> createEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Equals(left, right);
	}

	@Override
	public Expression<Boolean> createNotEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new NotEquals(left, right);
	}

	@Override
	public Expression<Double> createGetX(Expression expr, SourceLocation sourceLocation) {
		return new GetX(expr);
	}

	@Override
	public Expression<Double> createGetY(Expression expr, SourceLocation sourceLocation) {
		return new GetY(expr);
	}

	@Override
	public Expression<Double> createGetWidth(Expression expr,
			SourceLocation sourceLocation) {
		return new GetWidthObject(expr);
	}

	@Override
	public Expression createGetHeight(Expression expr,
			SourceLocation sourceLocation) {
		return new GetHeightObject(expr);
	}

	@Override
	public Expression<Double> createGetHitPoints(Expression expr,
			SourceLocation sourceLocation) {
		return new GetHpObject(expr);
	}

	@Override
	public Expression<Tile> createGetTile(Expression x, Expression y,
			SourceLocation sourceLocation) {
		return new GetTileExpression(x, y);
	}

	@Override
	public Expression<SuperObject> createSearchObject(Expression direction,
			SourceLocation sourceLocation) {
		return new SearchObject(direction);
	}

	@Override
	public Expression<Boolean> createIsMazub(Expression expr,
			SourceLocation sourceLocation) {
		return new IsMazub(expr);
	}

	@Override
	public Expression<Boolean> createIsShark(Expression expr,
			SourceLocation sourceLocation) {
		return new IsShark(expr);
	}

	@Override
	public Expression<Boolean> createIsSlime(Expression expr,
			SourceLocation sourceLocation) {
		return new IsSlime(expr);
	}

	@Override
	public Expression<Boolean> createIsPlant(Expression expr,
			SourceLocation sourceLocation) {
		return new IsPlant(expr);
	}

	@Override
	public Expression<Boolean> createIsDead(Expression expr,
			SourceLocation sourceLocation) {
		return new IsDead(expr);
	}

	@Override
	public Expression<Boolean> createIsTerrain(Expression expr,
			SourceLocation sourceLocation) {
		return new IsTerrain(expr);
	}

	@Override
	public Expression<Boolean> createIsPassable(Expression expr,
			SourceLocation sourceLocation) {
		return new IsPassable(expr);
	}

	@Override
	public Expression<Boolean> createIsWater(Expression expr,
			SourceLocation sourceLocation) {
		return new IsWater(expr);
	}

	@Override
	public Expression<Boolean> createIsMagma(Expression expr,
			SourceLocation sourceLocation) {
		return new IsMagma(expr);
	}

	@Override
	public Expression<Boolean> createIsAir(Expression expr, SourceLocation sourceLocation) {
		return new IsAir(expr);
	}

	@Override
	public Expression<Boolean> createIsMoving(Expression expr, Expression direction,
			SourceLocation sourceLocation) {
		return new IsMoving(expr, direction);
	}

	@Override
	public Expression<Boolean> createIsDucking(Expression expr,
			SourceLocation sourceLocation) {
		return new IsDucking(expr);
	}

	@Override
	public Expression<Boolean> createIsJumping(Expression expr,
			SourceLocation sourceLocation) {
		return new IsJumping(expr);
	}

	@Override
	public Statement createAssignment(String variableName, Type variableType,
			Expression<?> value, SourceLocation sourceLocation) {
		return new Assignment(variableName, variableType, value);
	}

	@Override
	public Statement createWhile(Expression condition, Statement body,
			SourceLocation sourceLocation) {
		return new WhileStatement(condition, body);
	}

	@Override
	public Statement createForEach(
			String variableName,
			jumpingalien.part3.programs.IProgramFactory.Kind variableKind,
			Expression where,
			Expression sort,
			jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {
		return new ForEachStatement(variableName, variableKind, where, sort, sortDirection, body);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new BreakStatement();
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody,
			Statement elseBody, SourceLocation sourceLocation) {
		return new IfStatement(condition, ifBody, elseBody);
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		return new PrintStatement(value);
	}

	@Override
	public Statement createStartRun(Expression direction,
			SourceLocation sourceLocation) {
		return new StartRun(direction);
	}

	@Override
	public Statement createStopRun(Expression direction,
			SourceLocation sourceLocation) {
		return new StopRun(direction);
	}

	@Override
	public Statement createStartJump(SourceLocation sourceLocation) {
		return new StartJump();
	}

	@Override
	public Statement createStopJump(SourceLocation sourceLocation) {
		return new StopJump();
	}

	@Override
	public Statement createStartDuck(SourceLocation sourceLocation) {
		return new StartDuck();
	}

	@Override
	public Statement createStopDuck(SourceLocation sourceLocation) {
		return new StopDuck();
	}

	@Override
	public Statement createWait(Expression duration,
			SourceLocation sourceLocation) {
		return new WaitStatement(duration);
	}

	@Override
	public Statement createSkip(SourceLocation sourceLocation) {
		return new SkipStatement();
	}

	@Override
	public Statement createSequence(List<Statement> statements,
			SourceLocation sourceLocation) {
		return new SequenceOfStatements(statements);
	}

	@Override
	public Type getDoubleType() {
		return Type.DOUBLE;
	}

	@Override
	public Type getBoolType() {
		return Type.Boolean;
	}

	@Override
	public Type getGameObjectType() {
		return Type.Object;
	}

	@Override
	public Type getDirectionType() {
		return Type.Direction;
	}

	@Override
	public Program createProgram(Statement mainStatement,
			Map<String, Type> globalVariables) {
		return new Program(mainStatement, globalVariables);
	}


	
}
