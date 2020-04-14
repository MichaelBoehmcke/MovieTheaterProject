import junit.framework.TestCase;

public class VenueTesting extends TestCase{
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
 public void testVenueConstructor() {
   Venue newVenue = new Venue("TestVenue", "123 st", 10, 5, 5);
   assertEquals(newVenue.getName(), "TestVenue");
 }
 
 public void testToString() {
   Venue newVenue = new Venue("TestVenue", "123 st", 10, 5, 5);
   assertEquals(newVenue.toString(), "Venue: TestVenue\nAddress: 123 st\nNumber of Theaters: 10");
 }
}