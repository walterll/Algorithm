package com.week5;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {
	private SET<Point2D> points;
	// construct an empty set of points
	public PointSET() {
		points = new SET<>();
	}
	// is the set empty?
	public boolean isEmpty() {
		return points.isEmpty();
	}
	// number of points in the set
	public int size() {
		return points.size();
	}
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		points.add(p);
	}
	// does the set contain point p?
	public boolean contains(Point2D p) {            
		return points.contains(p);
	}
	// draw all points to standard draw
	public void draw() {
		
	}
	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		List<Point2D> list = new ArrayList<>();
		for (Point2D point : points) {
			if (rect.contains(point))
				list.add(point);
		}
		return list;
	}
	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		double nearest = points.min().distanceSquaredTo(p);
		Point2D nearestPoint = points.min();
		for (Point2D point : points) {
			double distance = p.distanceSquaredTo(point);
			if (distance < nearest) {
				nearest = distance;
				nearestPoint = point;
			}
		}
		return nearestPoint;
	}
	// unit testing of the methods (optional)
	public static void main(String[] args) {
		Point2D p1 = new Point2D(1, 2);
		Point2D p2 = new Point2D(3, 2);
		Point2D p3 = new Point2D(2, 5);
		Point2D p4 = new Point2D(7, 4);
		Point2D p5 = new Point2D(5, 1);
		Point2D p6 = new Point2D(3, 3);
		PointSET ps = new PointSET();
		ps.insert(p1);
		ps.insert(p2);
		ps.insert(p3);
		ps.insert(p4);
		ps.insert(p5);
		ps.insert(p6);
		RectHV r1 = new RectHV(1, 2, 4, 5);
		System.out.println(ps.nearest(new Point2D(4, 4)));
		System.out.println(ps.range(r1));
	}
}
