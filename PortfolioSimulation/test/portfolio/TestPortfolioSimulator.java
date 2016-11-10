/*
 * Copyright (C) 2016 Swathi Mekala. All rights reserved.
 * This file is part of Portfolio project
 */
package portfolio;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test class for {@link PortfolioSimulator} class and its methods.
 * 
 * @author Swathi Mekala
 * @version 1.1
 * @since 1.0
 */
public class TestPortfolioSimulator {

	/**
	 * Test for single simulation run. Border case scenario testing of
	 * {@link PortFolioSimulator#runSimulation} 
	 */
	@Test
	public void testRunSimulationSingleRun() {
		int numSims = 1;
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, numSims);
		p.runSimulation();
	}
	
	/**
	 * Test for large number of simulations. Making sure no exception is raised. 
	 */
	@Test
	public void testRunSimulationLargeNum() {
		int numSims = 100000;
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, numSims);
		p.runSimulation();
	}
	
	/**
	 * Test for zero mean return, zero std deviation and zero inflation rate. This 
	 * helps testing the {@link PortfolioSimulator#adjustForRate(double,double)} 
	 * private method when rate is zero
	 */
	@Test
	public void testRunSimulationZeroInflationWithoutMeanReturns() {
		double ir = 0;
		int capital = 100;
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 0, 0);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, capital, 1, ir, 1);
		p.runSimulation();
		Assert.assertEquals((double)capital, p.getResult()[0], (double)0);
	}
	
	/**
	 * Test for negative inflation rate and zero return and std deviation. This 
	 * helps testing the {@link PortfolioSimulator#adjustForRate(double,double)}
	 * private method when rate is negative
	 */
	@Test
	public void testRunSimulationNegativeInflationWithoutMeanReturns() {
		double ir = -1.73;
		int capital = 100;
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 0, 0);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, capital, 1, ir, 1);
		p.runSimulation();
		Assert.assertTrue(capital > p.getResult()[0]);
		Assert.assertEquals(p.getResult()[0], 98.27, (double)0);
	}
	
	/**
	 * Test for positive inflation rate and zero return and std deviation. This 
	 * helps testing the {@link PortfolioSimulator#adjustForRate(double,double)}
	 * private method when rate is positive
	 */
	@Test
	public void testRunSimulationPositiveInflationWithoutMeanReturns() {
		double ir = 1.73;
		int capital = 100;
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 0, 0);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, capital, 1, ir, 1);
		p.runSimulation();
		Assert.assertTrue(capital < p.getResult()[0]);
		Assert.assertEquals(p.getResult()[0], 101.73, (double)0);
	}
	
	/**
	 * Test for positive mean return, zero std deviation and zero inflation rate. 
	 * This helps testing of {@link PortfolioSimulator#computeFinalReturn(double[])} 
	 * private method for boundary cases
	 */
	@Test
	public void testRunSimulationZeroInflationWithMeanReturns() {
		double ir = 0;
		int capital = 100;
		double meanReturn = 10.00;
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, meanReturn, 0);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, capital, 1, ir, 1);
		p.runSimulation();
		Assert.assertEquals(110, p.getResult()[0], (double)0.001);
	}
	
	/**
	 * Test for positive mean return, zero std deviation and negative inflation rate.
	 * This helps testing of {@link PortfolioSimulator#computeFinalReturn(double[])} 
	 * private method for boundary cases
	 */
	@Test
	public void testRunSimulationNegativeInflationWithMeanReturns() {
		double ir = -1.73;
		int capital = 100;
		double meanReturn = 10;
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, meanReturn, 0);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, capital, 1, ir, 1);
		p.runSimulation();
		Assert.assertTrue(capital < p.getResult()[0]);
		Assert.assertEquals(p.getResult()[0], 108.097, (double)0.01);
	}
	
	/**
	 * Test for positive mean return, zero std deviation and positive inflation rate. 
	 * This helps testing of {@link PortfolioSimulator#computeFinalReturn(double[])} 
	 * private method for boundary cases
	 */
	@Test
	public void testRunSimulationPositiveInflationWithMeanReturns() {
		double ir = 1.73;
		int capital = 100;
		double meanReturn = 10;
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, meanReturn, 0);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, capital, 1, ir, 1);
		p.runSimulation();
		Assert.assertTrue(capital < p.getResult()[0]);
		Assert.assertEquals(p.getResult()[0], 111.903, (double)0.01);
	}

	/**
	 * Test for correctness of {@link PortfolioSimulator#getMedian()} method in a 
	 * basic single simulation run
	 */
	@Test
	public void testGetMedianSingleSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 1);
		double r[] = { 322 };
		p.setResult(r);
		Assert.assertEquals(322, p.getMedian(), 0);
	}
	
	/**
	 * Test for correctness of {@link PortfolioSimulator#getMedian()} method for 
	 * odd number simulation runs
	 */
	@Test
	public void testGetMedianOddNumSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 3);
		double r[] = {103, 22, 432};
		p.setResult(r);
		Assert.assertEquals(103, p.getMedian(), 0);
	}
	
	/**
	 * Test for correctness of {@link PortfolioSimulator#getMedian()} method for 
	 * even number simulation runs
	 */
	@Test
	public void testGetMedianEvenNumSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 10);
        // Take a random sample of results
		double r[] = {123, 654, 22, 432, 234, 674, 467, 834, 129, 76};
		double expectedMedian = 333;
		p.setResult(r);
		Assert.assertEquals(expectedMedian, p.getMedian(), 0);
	}

	/**
	 * Test for correctness of {@link PortfolioSimulator#getBestCasePercentile()} 
	 * method for a single simulation runs
	 */
	@Test
	public void testGetBestCaseSingleSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 1);
		double r[] = {322};
		p.setResult(r);
		Assert.assertEquals(322, p.getBestCase(), 0);
	}
	
	/**
	 * Test for correctness of {@link PortfolioSimulator#getBestCasePercentile()} 
	 * method for odd number of simulation runs
	 */
	@Test
	public void testGetBestCaseOddNumSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 3);
		double r[] = {103, 22, 432};
		p.setResult(r);
		Assert.assertEquals(432, p.getBestCase(), 0);
	}
	
	/**
	 * Test for correctness of {@link PortfolioSimulator#getBestCasePercentile()} 
	 * method for even number of simulation runs
	 */
	@Test
	public void testGetBestCaseEvenNumSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 10);
		double r[] = {123, 654, 22, 432, 234, 674, 467, 834, 129, 76};
		p.setResult(r);
		Assert.assertEquals(674, p.getBestCase(), 0);
	}

	/**
	 * Test for correctness of {@link PortfolioSimulator#getWorstCasePercentile()} 
	 * method for a single simulation run
	 */
	@Test
	public void testGetWorstCaseSingleSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 1);
		double r[] = { 322 };
		p.setResult(r);
		Assert.assertEquals(322, p.getWorstCase(), 0);
	}
	
	/**
	 * Test for correctness of {@link PortfolioSimulator#getWorstCasePercentile()} 
	 * method for odd number of simulation runs
	 */
	@Test
	public void testGetWorstCaseOddNumSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 3);
		double r[] = {123, 22, 432};
		p.setResult(r);
		Assert.assertEquals(22, p.getWorstCase(), 0);
	}
	
	/**
	 * Test for correctness of {@link PortfolioSimulator#getWorstCasePercentile()} 
	 * method for even number of simulation runs
	 */
	@Test
	public void testGetWorstCaseEvenNumSimulation() {
		PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioSimulator p = new PortfolioSimulator(aggrPp, 100, 2, 2.2, 10);
		double r[] = {123, 654, 22, 432, 54, 423};
		p.setResult(r);
		Assert.assertEquals(22, p.getWorstCase(), 0);
	}

}
