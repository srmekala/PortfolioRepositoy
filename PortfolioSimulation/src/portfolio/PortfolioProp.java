package portfolio;
/**
 * @author swathi
 *
 */
public class PortfolioProp {
	private PortfolioType pt;
	private double meanReturn;
    private double riskStdDev;
    
    PortfolioProp(PortfolioType pt, double meanReturn, double riskStdDev){
    	this.pt = pt;
    	this.meanReturn = meanReturn;
    	this.riskStdDev = riskStdDev;
    }
    
	public PortfolioType getPt() {
		return pt;
	}
	public void setPt(PortfolioType pt) {
		this.pt = pt;
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
}
