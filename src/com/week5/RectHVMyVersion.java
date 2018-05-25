package com.week5;

public class RectHVMyVersion {
	private double xmin;
	private double ymin;
	private double xmax;
	private double ymax;
	// construct the rectangle [xmin, xmax] x [ymin, ymax]
	// throw a java.lang.IllegalArgumentException if (xmin > xmax) or (ymin > ymax)
	public RectHVMyVersion(double xmin, double ymin, double xmax, double ymax) {
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
	}
	// minimum x-coordinate of rectangle
	public double xmin() {
		return xmin;
	}
	// minimum y-coordinate of rectangle
	public double ymin() {
		return ymin;
	}
	// maximum x-coordinate of rectangle
	public  double xmax() {
		return xmax;
	}
	// maximum y-coordinate of rectangle
	public  double ymax() {
		return ymax;
	}
	// does this rectangle contain the point p (either inside or on boundary)?
	public boolean contains(Point2DMyVersion p) {
		if ((p.x() >= xmin) && (p.x() <= xmax) && (p.y() >= ymin) && (p.y() <= ymax))
			return true;
		return false;
	}
	// does this rectangle intersect that rectangle (at one or more points)? 
	public boolean intersects(RectHVMyVersion that) {
		if ((xmax > that.xmax()) && (xmin < that.xmin()))
			if (((ymin < that.ymin()) && (ymax > that.ymin())) 
				|| ((ymin < that.ymax()) && ymax > that.ymax()))
				return true;
		if ((ymax > that.ymax()) && (ymin < that.ymin()))
			if (((xmin < that.xmin()) && (xmax > that.xmin())) 
				|| ((xmin < that.xmax()) && xmax > that.xmax()))
				return true;
		return false;
	}
	// Euclidean distance from point p to closest point in rectangle
	public double distanceTo(Point2DMyVersion p) {
		if ((xmax >= p.x()) && (xmin <= p.x()))
			if (ymin > p.y())
				return ymin - p.y();
			else return p.y() - ymax;
		if ((ymax >= p.y()) && (ymin <= p.y()))
			if (xmin > p.x())
				return xmin - p.x();
			else return p.x() - xmax;
		return Math.sqrt(distanceSquaredTo(p));
	}
	// square of Euclidean distance from point p to closest point in rectangle
	public  double distanceSquaredTo(Point2DMyVersion p) {
		if ((p.x() < xmin) && (p.y() < ymin))
			return (xmin - p.x()) * (xmin - p.x()) + (ymin - p.y()) * (ymin - p.y());
		if ((p.x() < xmin) && (p.y() > ymax))
			return (xmin - p.x()) * (xmin - p.x()) + (p.y() - ymax) * (p.y() - ymax);
		if ((p.x() > xmax) && (p.y() < ymin))
			return (p.x() - xmax) * (p.x() - xmax) + (ymin - p.y()) * (ymin - p.y());
		if ((p.x() > xmax) && (p.y() > ymax))
			return Math.sqrt((p.x() - xmax) * (p.x() - xmax) + (p.y() - ymax) * (p.y() - ymax));
		return distanceTo(p);
	}
	// does this rectangle equal that object?
	public boolean equals(Object that) {
		RectHVMyVersion thatRec = (RectHVMyVersion)that;
		return ((xmin == thatRec.xmin()) && (xmax == thatRec.xmax()) 
				&& (ymin == thatRec.ymin()) && (ymax == thatRec.ymax()));
	}
	// draw to standard draw
	public void draw() {
		
	}
	// string representation 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(xmin);
		sb.append(" ");
		sb.append(xmax);
		sb.append("\n");
		sb.append(ymin);
		sb.append(" ");
		sb.append(ymax);
		sb.append("\n");
		return sb.toString();
	}
}
