/*
 * Copyright (C) 2016 Swathi Mekala. All rights reserved.
 * This file is part of Portfolio project
 */
package portfolio;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

/**
 * PortfolioSimulator class accepts various portfolio simulation parameters
 * and computes and projects end returns for a given number of years 
 * 
 * @author Swathi Mekala
 * @version 1.1
 * @since 1.0
 */
public class PortfolioSimulator {
	/** {@link PortfolioProp} class for portfolio properties */
    private PortfolioProp pp;
    /** Capital amount to start with at Year 0 */
    private int capital;
    /** Number of years the return should be computed in each simulation */
    private int numYears;
    /** Constant inflation rate used for computing returns */
    private double inflationRate;
    /** Number of simulations to be run */ 
    private int numSimulations;
    /** Array holding results of all simulations */
    private double[] result;
    /** Best case returns percentile */
    static double bestcasePercentile = 90;
    /** Best case returns percentile */
    static double worstcasePercentile = 10;

    /**
     * PortfolioSimulator class constructor
     * 
     * @param pt {@link PortfolioProp} object
     * @param capt initial capital
     * @param yrs number of years 
     * @param ir inflation rate
     * @param numSims number of simulations
     */
	public PortfolioSimulator(PortfolioProp pt, int capt, int yrs, double ir, int numSims) {
        pp = pt;
        capital = capt;
        numYears = yrs;
        inflationRate = ir;
        numSimulations = numSims;
    }

	/**
	 * Getter method for {@link PortfolioProp} member
	 * @return portfolio properties
	 */
    public PortfolioProp getPp() {
		return pp;
	}

    /**
     * Setter method for {@link PortfolioProp} member
     * @param pp portfolio properties
     */
	public void setPp(PortfolioProp pp) {
		this.pp = pp;
	}

	/**
	 * Getter method for capital
	 * @return initial capital amount
	 */
	public int getCapital() {
		return capital;
	}

	/**
	 * Setter method for capital
	 * @param capital 
	 */
	public void setCapital(int capital) {
		this.capital = capital;
	}

	/**
	 * Getter method for numYears
	 * @return number of years
	 */
	public int getNumYears() {
		return numYears;
	}

	/**
	 * Setter method for numYears
	 * @param numYears number of years each simulation should be run
	 */
	public void setNumYears(int numYears) {
		this.numYears = numYears;
	}

	/**
	 * Getter method for inflation rate
	 * @return inflation rate
	 */
	public double getInflationRate() {
		return inflationRate;
	}

	/**
	 * Setter method for inflation rate
	 * @param inflationRate rate of inflation constant through the years
	 */
	public void setInflationRate(double inflationRate) {
		this.inflationRate = inflationRate;
	}

	/**
	 * Getter method for numSimulations
	 * @return number of simulations
	 */
	public int getNumSimulations() {
		return numSimulations;
	}

	/**
	 * Setter method for numSimulations
	 * @param numSimulations number of simulations
	 */
	public void setNumSimulations(int numSimulations) {
		this.numSimulations = numSimulations;
	}

	/**
	 * Getter method for result
	 * @return result double array of results of all years
	 */
	public double[] getResult() {
		return result;
	}

	/**
	 * Setter method for result. Mainly used for unit testing.
	 * @param result array of double values
	 */
	public void setResult(double[] result) {
		this.result = result;
	}

	/**
	 * Getted method for bestcasePercentile constant
	 * @return best case percentile
	 */
	public static double getBestcasePercentile() {
		return bestcasePercentile;
	}

	/**
	 * Setter method for bestcasePercentile
	 * @param bestcasePercentile best case percentile
	 */
	public static void setBestcasePercentile(double bestcasePercentile) {
		PortfolioSimulator.bestcasePercentile = bestcasePercentile;
	}

	/**
	 * Getted method for worstcasePercentile constant
	 * @return worst case percentile
	 */
	public static double getWorstcasePercentile() {
		return worstcasePercentile;
	}

	/**
	 * Setter method for worstcasePercentile
	 * @param worstcasePercentile worst case percentile
	 */
	public static void setWorstcasePercentile(double worstcasePercentile) {
		PortfolioSimulator.worstcasePercentile = worstcasePercentile;
	}

	/**
     * Generates gaussian distribution of returns for numYears given the standard
     * deviation and mean
     * @return double[] Array of gaussian distributions 
     */
    private double[] generateReturnsDistribution() {
        double returnsDistribution[] = new double[numYears];
        Random r = new Random();
        for (int i = 0; i < numYears; ++i) {
            double sample = ( r.nextGaussian() * this.getPp().getRiskStdDev() ) + 
            				this.getPp().getMeanReturn();
            returnsDistribution[i] = sample;
        }
        return returnsDistribution;
    }
    
    /**
     * Adjusts given capital value for inflation rate. e.g. at rate of 3.5%, 
     * a value of 100 is adjusted to 103.5 
     * @param capVal Initial capital value
     * @param rate rate of (inflation) in percentage
     * @return double final capital after applying the rate 
     */
    private double adjustForRate(double capVal, double rate) {
    	return (capVal * (1 + (rate / 100)));
    }
    
    /**
     * Computes the final return for the capital amount at the end of the given
     * number of years based on the gaussian distribution of return percentages
     * and the constant inflation rate 
     * @param returnsSet contains gaussian distribution for numYears
     * @return	double Final capital value after adjusting to return distribution
     * 			and inflation rate for numYears 
     */
    private double computeFinalReturn(double returnsSet[]) {
        double finalReturn = getCapital();
        for(int i=0; i<returnsSet.length; i++) {
        	// Adjust for return percentage
        	finalReturn = adjustForRate(finalReturn,returnsSet[i]);
        	// Adjust for inflation rate
            finalReturn = adjustForRate(finalReturn, getInflationRate());
        }
        return finalReturn;
    }
    
    /**
     * Runs simulations based on the constructor parameters passed.
     */
    public void runSimulation() {
        double simulationResult[] = new double[numSimulations];
        for(int i=0; i<numSimulations; i++) {
            double returnsSet[] = generateReturnsDistribution();
            simulationResult[i] = computeFinalReturn(returnsSet);
        }
        this.result = simulationResult;
    }
    
    /**
     * Computes median of results
     * @return median of capital returns of all years 
     */
    public double getMedian() {
        Arrays.sort(this.result);
        int len = this.result.length;
        int mid = len/2;
        if(len % 2 == 1) {
            return this.result[mid];
        } else {
            return (this.result[mid] + this.result[(mid - 1)]) / 2;
        }
    }

    /**
     * Method for getting the percentile capital return value
     * @param percentile Capital return value at the percentile of the 
     * 		  result distribution
     * @return percentile value from the result distribution
     */
    private double getPercentileValue(double percentile) {
    	int pos = (int) Math.round((percentile / 100) * this.result.length);        
        if (pos == 0) {
            return this.result[pos];
        }
        return this.result[pos - 1];	
    }
    
    /**
     * Computes the best case of returns from the distribution based on the 
     * best case percentile
     * @return best case return value from the result distribution
     */
    public double getBestCase() {
        Arrays.sort(this.result);
        return getPercentileValue(PortfolioSimulator.getBestcasePercentile());
    }
    
    /**
     * Computes the worst case of returns from the distribution based on the 
     * worst case percentile
     * @return worst case return value from the result distribution
     */
    public double getWorstCase() {
        Arrays.sort(this.result);
        return getPercentileValue(PortfolioSimulator.getWorstcasePercentile());
    }
 
    /**
     * Prints median, best case and worst case result based on the format
     * @param format Format for the results to be displayed according to
     */
    public void printResults(String format){
    	DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);        
        System.out.format(format, this.getPp().getPt(), df.format(getMedian()),
                        df.format(getBestCase()), df.format(getWorstCase()));        
    }
}
