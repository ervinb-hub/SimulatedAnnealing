package com.isearch.sa;

import java.util.Random;

public class Direction {
	
	private int domainSize;
	
	private int nextX;
	private int nextY;
	
	private final int NW = 0;
	private final int N  = 1;
	private final int NE = 2;
	private final int E  = 3;
	private final int SE = 4;
	private final int S  = 5;
	private final int SW = 6;
	private final int W  = 7;
	
	public Direction(Node current, int dsize) {
		this.nextX = current.getX();
		this.nextY = current.getY();
		this.domainSize = dsize;
	}
	
	public Node next() {
		int direction = new Random().nextInt(8); 	
		
		switch(direction) {
			case NW:
				this.nextX -= 1;
				this.nextY += 1;
				break;
			case N:
				this.nextY += 1;
				break;
			case NE:
				this.nextX += 1;
				this.nextY += 1;
				break;
			case E:
				this.nextX += 1;
				break;
			case SE:
				this.nextX += 1;
				this.nextY -= 1;
				break;
			case S:
				this.nextY -= 1;
				break;
			case SW:
				this.nextX -= 1;
				this.nextY -= 1;
				break;
			case W:
				this.nextX -= 1;
				break;
		}
		
		if (nextX > domainSize)
			nextX -= 2;
		
		if (nextY > domainSize)
			nextY -= 2;
		
		if (nextX < 0)
			nextX += 2;
		
		if (nextY < 0)
			nextY += 2;
		
		Node nextMove = new Node(nextX, nextY);
		return nextMove;
	}
}
