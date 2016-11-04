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
		// making sure the method runs for just a single run. Border case Scenario testing 
		int numSims = 1;
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, numSims);
		p.runSimulation();
	}
	
	@Test
	public void testRunSimulationLargeNum() {
		// making sure the method runs for a large num without exceptions
		int numSims = 100000;
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, numSims);
		p.runSimulation();
	}
	
	@Test
	public void testRunSimulationZeroInflationWithoutMeanReturns() {
		double ir = 0;
		int capital = 100;
		// for testing zero inflation pass number of simulations as 1 with std_dev = 0 and meanReturn = 0 and numYears = 1
		// This helps testing for testing adjustForRate private method when rate is zero
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 0, 0);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, capital, 1, ir, 1);
		p.runSimulation();
		Assert.assertEquals((double)capital, p.getResult()[0], (double)0);
	}
	
	@Test
	public void testRunSimulationNegativeInflationWithoutMeanReturns() {
		double ir = -1.73;
		int capital = 100;
		// for testing zero inflation pass number of simulations as 1 with std_dev = 0 and meanReturn = 0 and numYears = 1
		// This helps testing for testing adjustForRate private method when rate is -ve 
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 0, 0);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, capital, 1, ir, 1);
		p.runSimulation();
		Assert.assertTrue(capital>p.getResult()[0]);
		Assert.assertEquals(p.getResult()[0], 98.27, (double)0);
	}
	
	@Test
	public void testRunSimulationPositiveInflationWithoutMeanReturns() {
		double ir = 1.73;
		int capital = 100;
		// for testing zero inflation pass number of simulations as 1 with std_dev = 0 and meanReturn = 0 and numYears = 1
		// This helps testing for testing adjustForRate private method when rate is +ve
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 0, 0);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, capital, 1, ir, 1);
		p.runSimulation();
		Assert.assertTrue(capital<p.getResult()[0]);
		Assert.assertEquals(p.getResult()[0], 101.73, (double)0);
	}
	
	@Test
	public void testRunSimulationZeroInflationWithMeanReturns() {
		double ir = 0;
		int capital = 100;
		double meanReturn = 10.00;
		// for testing zero inflation pass number of simulations as 1 with std_dev = 0 and numYears = 1
		// This helps testing for testing computeFinalReturn private method when inflation rate is zero and returns distributions is not zero
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, meanReturn, 0);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, capital, 1, ir, 1);
		p.runSimulation();
		Assert.assertEquals(110, p.getResult()[0], (double)0.001);
	}
	
	@Test
	public void testRunSimulationNegativeInflationWithMeanReturns() {
		double ir = -1.73;
		int capital = 100;
		double meanReturn = 10;
		// for testing zero inflation pass number of simulations as 1 with std_dev = 0 and numYears = 1
		// This helps testing for testing computeFinalReturn private method when inflation rate is -ve and returns distributions is not zer
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, meanReturn, 0);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, capital, 1, ir, 1);
		p.runSimulation();
		Assert.assertTrue(capital<p.getResult()[0]);
		Assert.assertEquals(p.getResult()[0], 108.097, (double)0.01);
	}
	
	@Test
	public void testRunSimulationPositiveInflationWithMeanReturns() {
		double ir = 1.73;
		int capital = 100;
		double meanReturn = 10;
		// for testing zero inflation pass number of simulations as 1 with std_dev = 0 and numYears = 1
		// This helps testing for testing computeFinalReturn private method when inflation rate is +ve and returns distributions is not zer
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, meanReturn, 0);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, capital, 1, ir, 1);
		p.runSimulation();
		Assert.assertTrue(capital<p.getResult()[0]);
		Assert.assertEquals(p.getResult()[0], 111.903, (double)0.01);
	}
	
	@Test
	public void testGetMedianSingleSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 1);
		double r[] = {322};
		p.setResult(r);
		Assert.assertEquals(322, p.getMedian(), 0);
	}
	
	@Test
	public void testGetMedianOddNumSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 3);
		double r[] = {103,22,432};
		p.setResult(r);
		Assert.assertEquals(103, p.getMedian(), 0);
	}
	
	@Test
	public void testGetMedianEvenNumSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 10);
		double r[] = {123,654,22,432,234,674,467,834,129,76};
		p.setResult(r);
		Assert.assertEquals(333, p.getMedian(), 0);
	}

	@Test
	public void testGetBestCaseSingleSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 1);
		double r[] = {322};
		p.setResult(r);
		Assert.assertEquals(322, p.getBestCase(), 0);
	}
	
	@Test
	public void testGetBestCaseOddNumSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 3);
		double r[] = {103,22,432};
		p.setResult(r);
		Assert.assertEquals(432, p.getBestCase(), 0);
	}
	
	@Test
	public void testGetBestCaseEvenNumSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 10);
		double r[] = {123,654,22,432,234,674,467,834,129,76};
		p.setResult(r);
		Assert.assertEquals(674, p.getBestCase(), 0);
	}

	@Test
	public void testGetWorstCaseSingleSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 1);
		double r[] = {322};
		p.setResult(r);
		Assert.assertEquals(322, p.getWorstCase(), 0);
	}
	
	@Test
	public void testGetWorstCaseOddNumSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 3);
		double r[] = {123,22,432};
		p.setResult(r);
		Assert.assertEquals(22, p.getWorstCase(), 0);
	}
	
	@Test
	public void testGetWorstCaseEvenNumSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 10);
		double r[] = {123,654,22,432,54,423};
		p.setResult(r);
		Assert.assertEquals(22, p.getWorstCase(), 0);
	}

}
