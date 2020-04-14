
import org.junit.Test;
import static org.junit.Assert.*;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


public class DatabaseTestClass {
	@BeforeClass
	public static void oneTimeSetup() {
		
	}
	
	@AfterClass
	public static void oneTimeTearDown() {
		
	}
	
	@Before
	public void setup() {
		//runs before each test
	}
	
	@After
	public void tearDown() {
		//runs after each test
	}

	@Test
	public void testFindUsernameTrue() {
			Database db = new Database();
			Administrator admin = new Administrator();
			db.addAccount(admin);
			boolean accountFound = db.findUsername("AdminDefault");
			assertEquals("Account should be found",true, accountFound);
	}
	@Test
	public void testFindUsernameFalse() {
		Database db = new Database();
		Administrator admin = new Administrator();
		db.addAccount(admin);
		boolean accountFound = db.findUsername("Nobody");
		assertEquals("Account should not be found",false, accountFound);
	}
	@Test
	public void testVerifyLoginFalse() {
		Database db = new Database();
		Administrator admin = new Administrator();
		db.addAccount(admin);
		boolean accountLogIn = db.verifyLogin("AdminDefault","Admin1");
		assertEquals("Account should not log in",false, accountLogIn);
	}
	@Test
	public void testVerifyLoginFalse2() {
		Database db = new Database();
		Administrator admin = new Administrator();
		db.addAccount(admin);
		boolean accountLogIn = db.verifyLogin("dminDefault","Admin");
		assertEquals("Account should not log in",false, accountLogIn);
	}
	
	@Test
	public void testVerifyLoginTrue() {
		Database db = new Database();
		Administrator admin = new Administrator();
		db.addAccount(admin);
		boolean accountLogIn = db.verifyLogin("AdminDefault","admin1");
		assertEquals("Account should log in",true, accountLogIn);
	}
	@Test
	public void testAccountLogInTrue() {
		Database db = new Database();
		Administrator admin = new Administrator();
		db.addAccount(admin);
		Account tempAccount = db.returnLoggedIn("AdminDefault","admin1");
		assertEquals("temp account should be the one that was just added",tempAccount, admin);
	}
	@Test
	public void testAccountLogInFalse() {
		Database db = new Database();
		Administrator admin = new Administrator();
		db.addAccount(admin);
		Account tempAccount = db.returnLoggedIn("AdminDefault","admin");
		assertNull("should return null",tempAccount);
	}
	@Test
	public void testAdminToString() {
		Administrator admin = new Administrator();
		String temp = admin.toString();
		String expected ="Role: Admin\n"
				+ "Username: AdminDefault\nPassword: admin1\nID: -1\n";
		assertEquals(temp,expected);
	}
	@Test
	public void testAccountToString() {
		Database db = new Database();
		Administrator admin = new Administrator();
		db.addAccount(admin);
		String temp = db.accounts.get(0).toString();
		String expected ="Role: Admin\n"
				+ "Username: AdminDefault\nPassword: admin1\nID: 0\n";
		assertEquals(temp,expected);
	}
	@Test
	public void testGetAccountInfo() {
		//Administrator admin = new Administrator();
		Database db = new Database("load");
		//System.out.println(db.accounts.get(0).toString());
		Member joe = (Member) db.accounts.get(0);
		String expected = "Role: Member\n" + 
				"Username: joebhoi\n" + 
				"Password: Cocksby90\n" + 
				"ID: -1\n" + 
				"Age: 20\n" + 
				"Rewards: 0\n" + 
				"Discount: x1.0\n" + 
				"Card Type: Debit\n" + 
				"Name: Joseph Bhoi\n" + 
				"Exp Date: 10/23\n" + 
				"Card Number: 1234567890784987\n" + 
				"CVV: 667";
		assertEquals(expected, joe.toString());
	}
	@Test
	public void testChangeUserAndPass() {
		Database db = new Database("load");
		Member joe = (Member) db.accounts.get(0);
		String newPassword ="GoSenators!";
		joe.changePassword("GoSenators!");
		assertEquals(newPassword,joe.getPassword());
		assertEquals(joe.getID(),-1);
		assertEquals(joe.getRole(), "Member");
		String newUser ="SenatorsFan112";
		joe.changeUsername("SenatorsFan112");
		assertEquals(joe.getUsername(),newUser);
	}
	@Test
	public void testMemberInfoWithoutPayment() {
		Database db = new Database("load");
		Member joe = (Member) db.accounts.get(0);
		joe.paymentInfo =null;
		String expected = "Role: Member\n" + 
				"Username: joebhoi\n" + 
				"Password: Cocksby90\n" + 
				"ID: -1\n" + 
				"Age: 20\n" + 
				"Rewards: 0\n" + 
				"Discount: x1.0";
		assertEquals(expected, joe.toString());
	}
	@Test
	public void testMemberInfo() {
		Database db = new Database("load");
		Member joe = (Member) db.accounts.get(0);
		assertEquals(joe.getRewards(), 0);
	}
	@Test
	public void testMemberDiscount() {
		Member temp = new Member();
		temp.setDiscount(1.0);
		assertEquals(temp.getDiscount(), 1.0, 0);
	}
	@Test
	public void testMemberGetPayment() {
		Database db = new Database("load");
		Member joe = (Member) db.accounts.get(0);
		Payment p = joe.getPayment();
		assertEquals(p,joe.getPayment());
		Member temp = new Member(72,"Billy","Joel");
		temp = new Member(5,"Little","Tommy");
		
		assertNull(temp.getPayment());
		temp.setRewards(1);
		temp.addReward(1);
		assertEquals(temp.getRewards(),2);
	}
	@Test
	public void testViewHistory() {
		String showTime="7:30pm";
		Show Frozen2;
		String name ="Frozen II";
		String type ="Movie";
		String genre="Animation, Advenure, Comedy";
		String ESRB="PG";
		int runtime=103;
		String description ="Anna, Elsa, Kristoff, Olaf and Sven leave Arendelle to travel to an ancient, autumn-bound forest of an enchanted land. They set out to find the origin of Elsa's powers in order to save their kingdom."; 

		Frozen2 = new Show(name, type, description, genre, ESRB, runtime,showTime);
		Member temp = new Member(72,"Billy","Joel");
		temp.purchaseTicket(Frozen2);
		String expected ="ID: 0 Frozen II\n";
		assertEquals(temp.viewHistory(),expected);
	}
	@Test
	public void testSetPaymentInfo() {
		Member temp = new Member(72,"Billy","Joel");
		Payment p = null;
		temp.setPayment(p);
		temp.setUpPayment();
	}
	@Test
	public void testReturnLoggedIn3() {
		Database db = new Database("");
		Account acc =db.returnLoggedIn("Joebhoi", "Cocksby90");
	}
	@Test
	public void testLoadAndSave() {
		Database db = new Database("Load");
		db.saveAccounts();
		db.saveVenues();
		db.saveShows();
	}
	@Test
	public void testShowFinder() {
		String showTime="7:30pm";
		Show Frozen2;
		String name ="Frozen II";
		String type ="Movie";
		String genre="Animation, Advenure, Comedy";
		String ESRB="PG";
		int runtime=103;
		String description ="Anna, Elsa, Kristoff, Olaf and Sven leave Arendelle to travel to an ancient, autumn-bound forest of an enchanted land. They set out to find the origin of Elsa's powers in order to save their kingdom."; 

		Frozen2 = new Show(name, type, description, genre, ESRB, runtime,showTime);
		Database db = new Database("");
		db.showFinder(Frozen2);
		db = new Database();
		db.showFinder(Frozen2);	
	}
	@Test
	public void testPrint() {
		Database db = new Database("");
		db.printVenues();
		db.printAccounts();
		db.printShows();
	}
	@Test
	public void testDeleteAccount() {
		Database db = new Database("");
		db.deleteAccount(db.accounts.get(0));
		boolean result =db.findUsername("joebhoi");
		boolean expected = false;
		assertEquals(expected, result);
	}


}
