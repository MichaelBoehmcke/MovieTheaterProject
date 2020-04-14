import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetRewards() {
		Member mem = new Member();
		assertEquals(0, mem.getRewards());
	}

	@Test
	void testSetRewards() {
		Member reward = new Member();
		assertEquals(reward, reward.getRewards());
	}

	@Test
	void testAddReward() {
		Member add = new Member();
		add.addReward(1);
		assertEquals("1", add.getRewards());
	}

}
