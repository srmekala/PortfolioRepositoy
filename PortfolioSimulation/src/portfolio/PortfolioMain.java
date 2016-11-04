package portfolio;

 
public class PortfolioMain {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int capital = 100000;
        int numYears = 20;
        double inflationRate = 3.5;
        int numSimulations = 10000;
        PortfolioProp aggrPp = new PortfolioProp(PortfolioType.A, 9.4324, 15.675);
        PortfolioProp cnsrvPp = new PortfolioProp(PortfolioType.I, 6.189, 6.3438);
        PortfolioSimulator a = new PortfolioSimulator(aggrPp, capital, numYears, inflationRate, numSimulations);
        PortfolioSimulator vc = new PortfolioSimulator(cnsrvPp, capital, numYears, inflationRate, numSimulations);
        
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
        System.out.println("--------------------------------------------------------------------------");
        System.out.format(format, "Portfolio Type", "Median 20th Year", "10% Best Case", "10% Worst Case");
        System.out.format(format, "--------------", "----------------", "-------------", "--------------");
        a.printResults(format);
        vc.printResults(format);
        System.out.println("--------------------------------------------------------------------------");
    }
}
