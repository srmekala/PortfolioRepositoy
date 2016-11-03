/**
 * 
 */
package portfolio;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author swathi
 *
 */
public class TestPortfolioSimulator {
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRunSimulationSingleRun() {
		int numSims = 1;
		PortfolioSimulator p = new PortfolioSimulator(PortfolioType.A, 9.43, 15.67, 100, 2, 2.2, numSims);
		p.runSimulation();
		System.out.println(p.getBestCase());
	}
	
	@Test
	public void testRunSimulationLargeNum() {
		int numSims = 100000;
		PortfolioSimulator p = new PortfolioSimulator(PortfolioType.A, 9.43, 15.67, 100, 2, 2.2, numSims);
		p.runSimulation();
	}
	
	@Test
	public void testRunSimulationZeroInflation() {
		double ir = 0;
		PortfolioSimulator p = new PortfolioSimulator(PortfolioType.A, 9.43, 15.67, 100, 2, ir, 1000);
		p.runSimulation();
	}
	
	@Test
	public void testRunSimulationNegativeInflation() {
		double ir = -1.73;
		PortfolioSimulator p = new PortfolioSimulator(PortfolioType.A, 9.43, 15.67, 100, 2, ir, 1000);
		p.runSimulation();
	}
	
	@Test
	public void testGetMedianSingleSimulation() {
		PortfolioSimulator p = new PortfolioSimulator(PortfolioType.A, 9.43, 15.67, 100, 2, 2.2, 3);
		double r[] = {322};
		p.setResult(r);
		Assert.assertEquals(322, p.getMedian(), 0);
	}
	
	@Test
	public void testGetMedianOddNumSimulation() {
		PortfolioSimulator p = new PortfolioSimulator(PortfolioType.A, 9.43, 15.67, 100, 2, 2.2, 3);
		double r[] = {103,22,432};
		p.setResult(r);
		Assert.assertEquals(103, p.getMedian(), 0);
	}
	
	@Test
	public void testGetMedianEvenNumSimulation() {
		PortfolioSimulator p = new PortfolioSimulator(PortfolioType.A, 9.43, 15.67, 100, 2, 2.2, 3);
		double r[] = {123,654,22,432,234,674,467,834,129,76};
		p.setResult(r);
		Assert.assertEquals(333, p.getMedian(), 0);
	}

	@Test
	public void testGetBestCaseSingleSimulation() {
		PortfolioSimulator p = new PortfolioSimulator(PortfolioType.A, 9.43, 15.67, 100, 2, 2.2, 3);
		double r[] = {322};
		p.setResult(r);
		Assert.assertEquals(322, p.getBestCase(), 0);
	}
	
	@Test
	public void testGetBestCaseOddNumSimulation() {
		PortfolioSimulator p = new PortfolioSimulator(PortfolioType.A, 9.43, 15.67, 100, 2, 2.2, 3);
		double r[] = {103,22,432};
		p.setResult(r);
		Assert.assertEquals(432, p.getBestCase(), 0);
	}
	
	@Test
	public void testGetBestCaseEvenNumSimulation() {
		PortfolioSimulator p = new PortfolioSimulator(PortfolioType.A, 9.43, 15.67, 100, 2, 2.2, 3);
		double r[] = {123,654,22,432,234,674,467,834,129,76};
		p.setResult(r);
		Assert.assertEquals(674, p.getBestCase(), 0);
	}

	@Test
	public void testGetWorstCaseSingleSimulation() {
		PortfolioSimulator p = new PortfolioSimulator(PortfolioType.A, 9.43, 15.67, 100, 2, 2.2, 3);
		double r[] = {322};
		p.setResult(r);
		Assert.assertEquals(322, p.getWorstCase(), 0);
	}
	
	@Test
	public void testGetWorstCaseOddNumSimulation() {
		PortfolioSimulator p = new PortfolioSimulator(PortfolioType.A, 9.43, 15.67, 100, 2, 2.2, 3);
		double r[] = {123,22,432};
		p.setResult(r);
		Assert.assertEquals(22, p.getWorstCase(), 0);
	}
	
	@Test
	public void testGetWorstCaseEvenNumSimulation() {
		PortfolioSimulator p = new PortfolioSimulator(PortfolioType.A, 9.43, 15.67, 100, 2, 2.2, 3);
		double r[] = {123,654,22,432,54,423};
		p.setResult(r);
		Assert.assertEquals(22, p.getWorstCase(), 0);
	}

}
