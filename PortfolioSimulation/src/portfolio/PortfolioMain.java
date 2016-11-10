/*
 * Copyright (C) 2016 Swathi Mekala. All rights reserved.
 * This file is part of Portfolio project
 */
package portfolio;

/**
 * Main class that runs Portfolio simulation using required parameters
 * 
 * @author Swathi Mekala
 * @version 1.1
 * @since 1.0
 */
public class PortfolioMain {
    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int capital = 100000; // Initial capital
        int numYears = 20; // Number of years portfolio should be simulated for
        double inflationRate = 3.5; // Inflation rate
        int numSimulations = 10000; // Number of simulations to be run
        // Mean return for 'Aggressive' portfolio type
        double meanReturnAggr = 9.4324;
        // Standard deviation for 'Aggressive' portfolio type
        double stdDeviationAggr = 15.675;
        // PortfolioProperty instance for 'Aggressive' type to be passed on to
        // PortfolioSimulator
        PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, meanReturnAggr,
        	 									stdDeviationAggr);
        // Portfolio for Aggressive type
        PortfolioSimulator a = new PortfolioSimulator(aggrPp, capital, numYears,
        										inflationRate, numSimulations);
        // Mean return for 'Very Conservative' portfolio type
        double meanReturnCons = 6.189;
        // Standard deviation for 'Very Conservative' portfolio type
        double stdDeviationCons = 6.3438;
        // PortfolioProperty instance for 'Very Conservative' type to be passed on
        // to PortfolioSimulator
        PortfolioProp cnsrvPp = new PortfolioProp(PortfolioType.I, meanReturnCons,
        										stdDeviationCons);
        // Portfolio for Very Conservative type
        PortfolioSimulator vc = new PortfolioSimulator(cnsrvPp, capital,
        							numYears, inflationRate, numSimulations);

        // Run simulation on both
        a.runSimulation();
        vc.runSimulation();
        
        // Print results
        //
        // Example:
        // ---------------------------------------------------------------------------
        // |Portfolio Type      |Median 20th Year    |10% Best Case  |10% Worst Case |
        // |--------------      |----------------    |-------------  |-------------- |
        // |Aggressive          |1078510.17          |2199883.76     |102869.38      |
        // |Very Conservative   |644007.2            |870926.52      |471338.79      |
        // ---------------------------------------------------------------------------
        String format = "|%1$-20s|%2$-20s|%3$-15s|%4$-15s|\n";
        System.out.println(
        		"--------------------------------------------------------------------------");
        System.out.format(format, "Portfolio Type", "Median 20th Year",
        				"10% Best Case", "10% Worst Case");
        System.out.format(format, "--------------", "----------------",
        				"-------------", "--------------");
        a.printResults(format);
        vc.printResults(format);
        System.out.println(
        		"--------------------------------------------------------------------------");
    }
}
