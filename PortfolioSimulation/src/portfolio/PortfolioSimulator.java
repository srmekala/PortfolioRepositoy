package portfolio;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class PortfolioSimulator {
    private PortfolioType portfolioType;
    private int capital;
    private int numYears;
    private double inflationRate;
    private int numSimulations;
    private double meanReturn;
    private double riskStdDev;
    private double[] result;

    public PortfolioSimulator(PortfolioType pt, double mr, double r, int capt, int yrs, double ir, int numSims) {
        portfolioType = pt;
        meanReturn = mr;
        riskStdDev = r;
        capital = capt;
        numYears = yrs;
        inflationRate = ir;
        numSimulations = numSims;
    }

    public PortfolioType getPortfolioType() {
        return portfolioType;
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

	public void setPortfolioType(PortfolioType portfolioType) {
        this.portfolioType = portfolioType;
    }

    public double getMeanReturn() {
        return meanReturn;
    }

    public void setMeanReturn(double meanReturn) {
        this.meanReturn = meanReturn;
    }

    public double getRiskStdDev() {
        return riskStdDev;
    }

    public void setRiskStdDev(double riskStdDev) {
        this.riskStdDev = riskStdDev;
    }

    private double[] generateReturnsDistribution() {
        double returnsDistribution[] = new double[numYears];
        Random r = new Random();
        for (int i = 1; i <= numYears; ++i) {
            double sample = r.nextGaussian()*getRiskStdDev()+getMeanReturn();
            returnsDistribution[i-1] = sample;
        }
        return returnsDistribution;
    }
    
    private double adjustForRate(double capVal, double rate) {
        return capVal*(1+(rate/100));
    }
    
    private double computeFinalReturn(double returnsSet[]) {
        double finalReturn = getCapital();
        for(int i=0; i<returnsSet.length; i++) {
            finalReturn = adjustForRate(finalReturn,returnsSet[i]); // Capital gain/return
            finalReturn = adjustForRate(finalReturn, getInflationRate()); // Adjust to inflation
        }
        return finalReturn;
    }
    
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
        double percentile = 0.9;
        return getPercentileValue(percentile);
    }
    
    public double getWorstCase() {
        Arrays.sort(this.result);
        double percentile = 0.1;
        return getPercentileValue(percentile);
    }
    
    public void printResults(String format){
    	DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);        
        System.out.format(format, this.getPortfolioType(), df.format(getMedian()),
                        df.format(getBestCase()), df.format(getWorstCase()));        
    }
}
