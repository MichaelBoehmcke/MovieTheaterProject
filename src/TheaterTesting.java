import junit.framework.TestCase;

public class TheaterTesting extends TestCase{
//@BeforeClass
 public void oneTimeSetup() {
 }

//@AfterClass
 public void oneTimeTearDown() {
 }

//@Before
 public void setup() {
  //runs before each test
 }

//@After
 public void tearDown() {
  //runs after each test
 }

//@test
 public void testTheaterConstructor() {
   Theater newTheater = new Theater(1, 5, 5);
   assertEquals(newTheater.getNumber(), 1);
 }
}