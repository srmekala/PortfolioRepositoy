/*
 * Copyright (C) 2016 Swathi Mekala. All rights reserved.
 * This file is part of Portfolio project
 */
package portfolio;

/**
 * PortfolioType enum for all types of portfolio
 * 
 * @author Swathi Mekala
 * @version 1.1
 * @since 1.0
 */
public enum PortfolioType {
	/** Enum value for Aggressive portfolio type */
    A {
        @Override
        public String toString() {
            return "Aggressive";
        }
    },
    /** Enum value for VeryConservative portfolio type */
    I {
        @Override
        public String toString() {
            return "Very Conservative";
        }
    }
}
