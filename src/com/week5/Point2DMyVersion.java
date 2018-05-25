package com.week5;

public class Point2DMyVersion implements Comparable<Point2DMyVersion> {
	private double x;
	private double y;
	// construct the point (x, y)
	public Point2DMyVersion(double x, double y) {
		this.x = x;
		this.y = y;
	}
	// x-coordinate
	public double x() {
		return x;
	}
	// y-coordinate
	public double y() {
		return y;
	}
	// Euclidean distance between two points
	public double distanceTo(Point2DMyVersion that) {
		return Math.sqrt(distanceSquaredTo(that));
	}
	// square of Euclidean distance between two points
	public double distanceSquaredTo(Point2DMyVersion that) {
		return (that.x - x) * (that.x - x) + (that.y - y) * (that.y - y);
	}
	// for use in an ordered symbol table
	public int compareTo(Point2DMyVersion that) {
		//两点重合
    	if ((that.x == this.x) && (that.y == this.y))
    		return 0;
    	//参考点小于此点
    	if ((that.y < this.y) || ((that.y == this.y) && (that.x < this.x)))
    		return 1;
    	//参考点大于此点
    	else
    		return -1;
	}
	// does this point equal that object?
	public boolean equals(Object that) {
		Point2DMyVersion thatPoint = (Point2DMyVersion)that;
		return ((thatPoint.x == x) && (thatPoint.y == y));
	}
	// draw to standard draw
	public void draw() {
		
	}
	// string representation
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(x);
		sb.append(" ");
		sb.append(y);
		sb.append("\n");
		return sb.toString();
	}
}
