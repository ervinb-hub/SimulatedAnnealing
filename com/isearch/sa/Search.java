package com.isearch.sa;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Search {
	
	private final static Logger logger = Logger.getLogger(Logger.class.getName());
	public static final int FILE_SIZE = 5000000;
	
	public static void main(String[] args) {
		
		setLogging();
		logger.log(Level.INFO, "BEGIN");
		
		double [] limits = new double[] {Double.parseDouble(args[0]), Double.parseDouble(args[1])};
        int dimDomain = (int)(limits[1] - limits[0]) * 10;

		double step = (limits[1] - limits[0]) / dimDomain;
		double[] axisX = new double[dimDomain+1];
		for (int i=0; i<=dimDomain; i++) {
			axisX[i] = limits[0] + i * step;
		}
		double[] axisY = axisX;
		
        int chosenFun = Integer.parseInt(args[2]);
        SurfaceFunction f = new SurfaceFunction();
		
		Node[][] surface = new Node[dimDomain+1][dimDomain+1];
		for (int i=0; i<=dimDomain; i++) {
			for (int j=0; j<=dimDomain; j++) {
				surface[i][j] = new Node(i, j, 
					new BigDecimal(f.eval(chosenFun, axisX[i], axisY[j])));
			}
		}
		
		int x1 = new Random().nextInt(dimDomain+1);
		int x2 = new Random().nextInt(dimDomain+1);
		Node current = new Node(x1, x2);
		
		SimulatedAnnealing sa = new SimulatedAnnealing(surface);
		current = sa.sAnnealing(current);
		logger.log(Level.INFO, "END at Coordinates(X={1},Y={0}) with value {2},iterations={3}", 
                new Object[] { axisX[current.getX()], axisY[current.getY()],current.getValue(),sa.getIterations() });
	}
	
	
	private static void setLogging() {
		FileHandler fileTxt = null;
		try {
			fileTxt = new FileHandler("SimulatedAnnealing.log", 
									FILE_SIZE, 20, true);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		SimpleFormatter formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
	    logger.addHandler(fileTxt);
	    logger.setLevel(Level.INFO);		
	}
}
