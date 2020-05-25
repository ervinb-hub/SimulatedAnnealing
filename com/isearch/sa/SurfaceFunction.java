package com.isearch.sa;

public class SurfaceFunction {

    interface Function {
    	double eval(double x1, double x2);
    }

    
    private Function[] availableFunctions = new Function[] {
        // SmoothSurface - Example of SUCCESS
        new Function() {
            public double eval(double x1, double x2) {
		        return x1 * Math.exp(-Math.pow(x1, 2) - Math.pow(x2, 2));
            }
        },
        // MonkeySaddle - Example of smoth surface with critical points 
        new Function() {
            public double eval(double x1, double x2) {
                return Math.pow(x1, 3) - 3 * x1 * Math.pow(x2, 2);
            }
        },
        // EasomFunction - Example of global minimum confined in a small area with rough edges, FAILURE
        new Function() {
            public double eval(double x1, double x2) {
                return -Math.cos(x1) * Math.cos(x2) * Math.exp(-(Math.pow(x1-Math.PI, 2)) - (Math.pow(x2-Math.PI, 2)));
            }
        }
    };


    public double eval(int index, double x1, double x2) {
        return availableFunctions[index].eval(x1, x2);
    }
}
