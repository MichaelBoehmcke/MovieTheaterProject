import junit.framework.TestCase;

public class SeatTesting extends TestCase{
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
 public void testSeatConstructor() {
   Seat newSeat = new Seat(10);
   assertEquals(newSeat.getID(), 10);
 }
 
 public void testSeatAvailability() {
  Seat newSeat = new Seat(0);
  newSeat.setAvailable(false);
  assertFalse(newSeat.getAvailable());
 }

 public void testSetSeatID() {
  Seat newSeat = new Seat(0);
  newSeat.setID(1);
  assertEquals(newSeat.getID(), 1);
 }
}