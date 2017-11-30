package scraping;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NVectorTest {
	
	// pre conditions.
	private NVector nVector;

	//(-1.34, 2.45) is 2d vector
	private double[] oneDcord = {-1.34, 2.45};
	
	/**
	 * Do before
	 * This method is executed before each test. It is used to prepare the test environment (e.g., read input data, initialize the class).
	 */
	@Before
	public void setUp() {
		
		// post conditions.
		nVector = new NVector(oneDcord);
	}
	
	/**
	 * Test-1 should check coordinates is not null
	 */
	@Test
	public void shouldCheckCoordinates() {
		Assert.assertNotNull(nVector.coords());
	}
	
	
	@Test
	public void shouldCheckLength() {
		Assert.assertEquals("2.792507833471556", String.valueOf(nVector.length()));
	}
	
	/**
	 * Test-2 check test equals null
	 */
	@Test
	public void shouldCheckDimensions() {
		Assert.assertFalse(nVector.equals(null));
	}
	
	/**
	 * Test-3 check test equals reflexive
	 */
	@Test
	public void shouldCheckReflexive() {
		Assert.assertTrue(oneDcord.equals(oneDcord));
	}
	
	/**
	 * Test-4 check test non vector
	 */
	@Test
	public void shouldCheckNonVector() {
		Object o = new Object();
		Assert.assertFalse(nVector.equals(o));
	}
	
	/**
	 * Test-5 check test for equals Unequal
	 */
	@Test
	public void shouldCheckEqualsUnequal() {
		double[] oneDcord2 = {-1.34, 2.45,1.23};
		Assert.assertFalse(nVector.equals(oneDcord2));
	}
	
	/**
	 * Test-6 check test hascode
	 */
	@Test 
	public void shouldCheckhascode() {
		Assert.assertNotNull(nVector.hashCode());
	}
	
	/**
	 * Test-7 check test for toString method output
	 */
	@Test 
	public void shouldCheckToStringMethod() {
		Assert.assertNotNull(nVector.toString());
		Assert.assertEquals("2d vector (-1.34, 2.45)",nVector.toString());
	}
	
	
}
