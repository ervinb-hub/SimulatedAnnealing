package com.isearch.sa;

import java.math.BigDecimal;

public class Node {
	
	private int x;
	private int y;
	private BigDecimal value;
	private int transitions;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.value = new BigDecimal(0);
		this.transitions = 20;
	}
	
	public Node(int x, int y, BigDecimal val) {
		this.x = x;
		this.y = y;
		this.value = val;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void addTransition() {
		this.transitions--;
	}
	
	public int getTransitions() {
		return this.transitions;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
