package interviews.onlinestore;

import static org.junit.Assert.*;

import org.junit.Test;

public class OnlineStoreTest {

	@Test
	public void test() {
		SoccerCleats cleats = new SoccerCleats("Size 8", 54.99);
		assertEquals("Size 8", cleats.getDescription());
		assertEquals(54.99, cleats.getPrice(), 0);
		assertEquals(Sport.SOCCER, cleats.getSport());
	}

}
