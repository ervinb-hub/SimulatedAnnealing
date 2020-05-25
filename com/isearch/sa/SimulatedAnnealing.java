package com.isearch.sa;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulatedAnnealing {
	
	private final static Logger logger = Logger.getLogger(Logger.class.getName());
	
	private Double Temperature;
	private Node[][] Energy;
	private int iterations;
	
	private final double EPS = 0.001;
	private final double alpha = 0.995;

	
	public SimulatedAnnealing(Node[][] s) {
		this.Energy = s;
		this.Temperature = new Double("10000000000"); 
		this.iterations = 1;
	}
	
	
	public double probability(double Eo, double Enew, double T) {
		double result = 0;
		
        if (Enew < Eo) {
            return 1.0;
        }
        
        if (Temperature == 0) {
        	return 0.0;
        }

        result = Math.exp(-(Enew - Eo) / T);
        return result;
    }
	
	
	public Node sAnnealing(Node current) {
		logger.log(Level.FINE, "Starting from point({0},{1})", 
				new Object[] {current.getX(), current.getY(), current.getValue()});
		
		while (Temperature > EPS && current.getTransitions() > 0) {
			
			int size = Energy.length - 1; 
			BigDecimal fCurrent = Energy[current.getX()][current.getY()].getValue();
			
			Node next = new Direction(current, size).next();
			logger.log(Level.FINE, 
				"Temperature={0}, Moving from: point({1},{2}), to point({3},{4})", 
					new Object[] {new Double(Temperature).toString(), 
					current.getX(), current.getY(), next.getX(), next.getY()});			
			
			BigDecimal fNext = Energy[next.getX()][next.getY()].getValue();
			next.setValue(fNext);
			
			BigDecimal dE = fNext.subtract(fCurrent);
			double UProb = Math.random();
			double Pnew = probability(fCurrent.doubleValue(), fNext.doubleValue(), Temperature);
			
			if (dE.doubleValue() < 0) {
				current = next;
				logger.log(Level.FINE, "fCurrent={0} fNext={1}, dE={2}, {3}", 
						new Object[] {fCurrent.setScale(16, 	 BigDecimal.ROUND_UP).toEngineeringString(), 
				fNext.setScale(16, BigDecimal.ROUND_UP).toEngineeringString(), 
				dE.setScale(16, BigDecimal.ROUND_UP).toEngineeringString(),
				"Next point is in a better position"});
			}
			else if (UProb < Pnew) {
				current = next;
				logger.log(Level.FINE, "U(0,1)={0}, accepting new point with P(new)={1}",
					new Object[] {UProb, Pnew});
			}
			else {
				current.addTransition();
				logger.log(Level.FINE, "Next point({0},{1}) rejected", 
					new Object[] {next.getX(), next.getY()});
			}
			
			Temperature *= alpha;
			iterations++;
		}
		
		return current;
	}
	
	
	public int getIterations() {
		return this.iterations;
	}
}
