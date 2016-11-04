package portfolio;

/**
 * @author swathi
 */

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class PortfolioSimulator {
    private PortfolioProp pp;
    private int capital;
    private int numYears;
    private double inflationRate;
    private int numSimulations;
    private double[] result;
    static double bestcasePercentile = 90;
    static double worstcasePercentile = 10;

	public PortfolioSimulator(PortfolioProp pt, int capt, int yrs, double ir, int numSims) {
        pp = pt;
        capital = capt;
        numYears = yrs;
        inflationRate = ir;
        numSimulations = numSims;
    }

    public PortfolioProp getPp() {
		return pp;
	}

	public void setPp(PortfolioProp pp) {
		this.pp = pp;
	}

	public int getCapital() {
		return capital;
	}

	public void setCapital(int capital) {
		this.capital = capital;
	}

	public int getNumYears() {
		return numYears;
	}

	public void setNumYears(int numYears) {
		this.numYears = numYears;
	}

	public double getInflationRate() {
		return inflationRate;
	}

	public void setInflationRate(double inflationRate) {
		this.inflationRate = inflationRate;
	}

	public int getNumSimulations() {
		return numSimulations;
	}

	public void setNumSimulations(int numSimulations) {
		this.numSimulations = numSimulations;
	}

	public double[] getResult() {
		return result;
	}

	public void setResult(double[] result) {
		this.result = result;
	}

	public static double getBestcasePercentile() {
		return bestcasePercentile;
	}

	public static void setBestcasePercentile(double bestcasePercentile) {
		PortfolioSimulator.bestcasePercentile = bestcasePercentile;
	}

	public static double getWorstcasePercentile() {
		return worstcasePercentile;
	}

	public static void setWorstcasePercentile(double worstcasePercentile) {
		PortfolioSimulator.worstcasePercentile = worstcasePercentile;
	}

	/**
     * 
     * @return double[] Array of gaussian distributions for numYears given std dev and mean
     */
    private double[] generateReturnsDistribution() {
        double returnsDistribution[] = new double[numYears];
        Random r = new Random();
        for (int i = 0; i < numYears; ++i) {
            double sample = r.nextGaussian()*this.getPp().getRiskStdDev()+this.getPp().getMeanReturn();
            returnsDistribution[i] = sample;
        }
        return returnsDistribution;
    }
    
    /**
     * @param capVal Initial capital value
     * @param rate
     * @return double final capital after applying the rate 
     */
    private double adjustForRate(double capVal, double rate) {
    	return capVal*(1+(rate/100));
    }
    
    /**
     * 
     * @param returnsSet contains gaussian distribution for numYears
     * @return	double	Final capital value after applying gaussion distribution and inflation rate for numYears 
     */
    private double computeFinalReturn(double returnsSet[]) {
        double finalReturn = getCapital();
        for(int i=0; i<returnsSet.length; i++) {
        	finalReturn = adjustForRate(finalReturn,returnsSet[i]); // Capital gain/return
            finalReturn = adjustForRate(finalReturn, getInflationRate()); // Adjust to inflation
        }
        return finalReturn;
    }
    /**
     * Run simulation numSimulations of times. 
     * And for each simulation calculate the returns distribution for numYears.
     * And then apply the final distribution and inflation rate to get the final return at the end of each year  
     */
    public void runSimulation() {
        double simulationResult[] = new double[numSimulations];
        for(int i=0; i<numSimulations; i++) {
            double returnsSet[] = generateReturnsDistribution();
            simulationResult[i] = computeFinalReturn(returnsSet);
        }
        this.result = simulationResult;
    }
    
    public double getMedian() {
        Arrays.sort(this.result);
        int len = this.result.length;
        int mid = len/2;
        if(len%2 == 1) {
            return this.result[mid];
        } else {
            return (this.result[mid]+this.result[(mid-1)])/2;
        }
    }

    private double getPercentileValue(double percentile) {
    	int pos = (int) Math.round(percentile * this.result.length); // 90th percentile value        
        if (pos == 0) {
            return this.result[pos];
        }
        return this.result[pos-1];	
    }
    
    public double getBestCase() {
        Arrays.sort(this.result);
        return getPercentileValue(PortfolioSimulator.getBestcasePercentile()/100.00);
    }
    
    public double getWorstCase() {
        Arrays.sort(this.result);
        return getPercentileValue(PortfolioSimulator.getWorstcasePercentile()/100.00);
    }
    
    public void printResults(String format){
    	DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);        
        System.out.format(format, this.getPp().getPt(), df.format(getMedian()),
                        df.format(getBestCase()), df.format(getWorstCase()));        
    }
}
