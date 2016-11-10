/*
 * Copyright (C) 2016 Swathi Mekala. All rights reserved.
 * This file is part of Portfolio project
 */
package portfolio;

/**
 * PortfolioProp class provides abstraction for properties of a portfolio
 * such as portfolio-type, mean-return and risk or standard-deviation.
 * 
 * @author Swathi Mekala
 * @version 1.1
 * @since 1.0
 */
public class PortfolioProp {
	/** Type of portfolio */
	private PortfolioType pt;
	/** Mean return of the portfolio */
	private double meanReturn;
	/** Standard deviation of returns of the portfolio */
	private double riskStdDev;
    
	/**
	 * Class constructor for PortfolioProp
	 * @param pt port-folio type
	 * @param meanReturn mean return percentage
	 * @param riskStdDev standard deviation percentage
	 */
    PortfolioProp(PortfolioType pt, double meanReturn, double riskStdDev){
    	this.pt = pt;
    	this.meanReturn = meanReturn;
    	this.riskStdDev = riskStdDev;
    }
    
    /**
     * @return the portfolio type as PortfolioType enum
     */
	public PortfolioType getPt() {
		return pt;
	}

	/**
     * @param pt portfolio type
     */	
	public void setPt(PortfolioType pt) {
		this.pt = pt;
	}
	
    /**
     * @return mean return percentage
     */
	public double getMeanReturn() {
		return meanReturn;
	}
	
    /**
     * @param meanReturn mean return percentage
     */
	public void setMeanReturn(double meanReturn) {
		this.meanReturn = meanReturn;
	}
	
    /**
     * @return standard deviation
     */
	public double getRiskStdDev() {
		return riskStdDev;
	}
	
    /**
     * @param riskStdDev standard deviation
     */
	public void setRiskStdDev(double riskStdDev) {
		this.riskStdDev = riskStdDev;
	}
}
