package portfolio;

public enum PortfolioType {
    A {
        @Override
        public String toString() {
            return "Aggressive";
        }
    },
    I {
        @Override
        public String toString() {
            return "Very Conservative";
        }
    }
}
