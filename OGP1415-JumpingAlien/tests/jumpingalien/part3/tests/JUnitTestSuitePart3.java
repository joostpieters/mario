package jumpingalien.part3.tests;
import jumpingalien.part2.tests.MazubTest;
import jumpingalien.part2.tests.PlantTest;
import jumpingalien.part2.tests.SchoolTest;
import jumpingalien.part2.tests.SharkTest;
import jumpingalien.part2.tests.SlimeTest;
import jumpingalien.part2.tests.WorldTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
   jumpingalien.part2.tests.PartialFacadeTest.class,
   MazubTest.class,
   PartialFacadeTest.class,
   PlantTest.class,
   SchoolTest.class,
   SharkTest.class,
   SlimeTest.class,
   WorldTest.class,
   BuzamTest.class,
   ExpressionTest.class,
   StatementTest.class,
   UnaryExpressionTest.class,
   BinaryExpressionTest.class,
   ProgramTest.class,
   TypeCheckingTest.class,
   TileTest.class
}) 
public class JUnitTestSuitePart3 {

}
