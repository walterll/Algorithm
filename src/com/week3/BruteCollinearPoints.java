package com.week3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
	private List<LineSegment> segments = new ArrayList<>();
	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		if (points == null)
			throw new java.lang.IllegalArgumentException();
		for (int point1 = 0; point1 < points.length; point1++) {
			if (points[point1] == null)
				throw new java.lang.IllegalArgumentException();
	        for (int point2 = point1 + 1; point2 < points.length; point2++) {
	        	if (points[point1].compareTo(points[point2]) == 0)
	        		throw new java.lang.IllegalArgumentException();
	        	for (int point3 = point2 + 1; point3 < points.length; point3++) {
	        		for (int point4 = point3 + 1; point4 < points.length; point4++) {
	        			if ((points[point1].compareTo(points[point2]) != 0)
	        				&& (points[point1].compareTo(points[point3]) != 0)
	        				&& (points[point1].compareTo(points[point4]) != 0)
	        				&& (points[point2].compareTo(points[point3]) != 0)
	        				&& (points[point2].compareTo(points[point4]) != 0)
	        				&& (points[point3].compareTo(points[point4]) != 0)) {
	        				double slope12 = points[point1].slopeTo(points[point2]);
	        				double slope13 = points[point1].slopeTo(points[point3]);
	        				double slope14 = points[point1].slopeTo(points[point4]);
	        				if (((slope12 == slope13) && (slope12 == slope14)) 
	        					|| ((Math.abs(slope12) == Double.POSITIVE_INFINITY)
	        						&& (Math.abs(slope13) == Double.POSITIVE_INFINITY)
	        						&& (Math.abs(slope14) == Double.POSITIVE_INFINITY))) {
	        					points[point1].draw();
	        					points[point2].draw();
	        					points[point3].draw();
	        					points[point4].draw();
	        					Point minPoint = points[point1].compareTo(points[point2]) < 0
	        							? points[point1] : points[point2];
	        					minPoint = minPoint.compareTo(points[point3]) < 0
	        							? minPoint : points[point3];
	        					minPoint = minPoint.compareTo(points[point4]) < 0
	        							? minPoint : points[point4];
	        					Point maxPoint = points[point1].compareTo(points[point2]) > 0
	        							? points[point1] : points[point2];
	        					maxPoint = maxPoint.compareTo(points[point3]) > 0
	        							? maxPoint : points[point3];
	        					maxPoint = maxPoint.compareTo(points[point4]) > 0
	        							? maxPoint : points[point4];
	        					System.out.println(points[point1]);
	        					System.out.println(points[point2]);
	        					System.out.println(points[point3]);
	        					System.out.println(points[point4]);
	        					System.out.println("------------");
	        					segments.add(new LineSegment(minPoint, maxPoint));
//	        					try {
//									Thread.sleep(5000);
//								} catch (InterruptedException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
	        				}
	        					
	        			}
	        		}
	        	}
	        }
			
		}
	}
	// the number of line segments
	public int numberOfSegments() {
		   return segments.size();
	}
	// the line segments
	public LineSegment[] segments() {
		   return (LineSegment[])segments.toArray(new LineSegment[segments.size()]);
	}
	
	public static void main(String[] args) {
		StdDraw.clear();
		StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(-0.05*10, 1.05*10);
        StdDraw.setYscale(-0.05*10, 1.05*10);
		Point[] points = new Point[13];
		points[0] = new Point(0, 0);
		points[1] = new Point(0, 1);
		points[2] = new Point(1, 1);
		points[3] = new Point(1, 2);
		points[4] = new Point(1, 6);
		points[5] = null;
		points[6] = new Point(2, 4);
		points[7] = new Point(3, 3);
		points[8] = new Point(3, 2);
		points[9] = new Point(3, 6);
		points[10] = new Point(2, 0);
		points[11] = new Point(4, 2);
		points[12] = new Point(6, 3);
		BruteCollinearPoints bc = new BruteCollinearPoints(points);
		for (int i = 0; i < bc.numberOfSegments(); i++)
			System.out.println(bc.segments()[i]);
	}
}
