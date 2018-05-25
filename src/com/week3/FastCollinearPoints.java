package com.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private List<LineSegment> segments = new ArrayList<>();
	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {
		if (points == null)
			throw new java.lang.IllegalArgumentException();
		for (int p = 0; p < points.length; p++) {
			if (points[p] == null)
				throw new java.lang.IllegalArgumentException();			
		}
		Arrays.sort(points);
		//最外层循环，遍历每一个点
		for (int p = 0; p < points.length; p++) {
			Point[] slopeTo = new Point[points.length - 1];
		
			
//			内层循环1
//			遍历并复制其他点，存入slopeTo[]数组
			for (int q = 0; q < points.length; q++) {
				if (q < p) {
					if (points[p].compareTo(points[q]) == 0)
						throw new java.lang.IllegalArgumentException();
					slopeTo[q] = points[q];
				} else if (q > p) {
					if (points[p].compareTo(points[q]) == 0)
						throw new java.lang.IllegalArgumentException();
					slopeTo[q - 1] = points[q];
				}
			}

			//按照slopeTo[]中点关于points[p]的斜率关系排序
			Arrays.sort(slopeTo, points[p].SLOPE_ORDER);
			int equalSlopePointsNum = 2;

			//内层循环2
			//遍历slopeTo[]中根据斜率排好序的点，找出斜率相同的超过3个点
			for (int i = 1; i < slopeTo.length; i++) {
				if ((points[p].slopeTo(slopeTo[i - 1]) == points[p].slopeTo(slopeTo[i]))
					|| ((Math.abs(points[p].slopeTo(slopeTo[i - 1])) == Double.POSITIVE_INFINITY)
						&& (Math.abs(points[p].slopeTo(slopeTo[i])) == Double.POSITIVE_INFINITY))) {
					equalSlopePointsNum++;
					StdDraw.setPenColor(StdDraw.RED);
					points[p].draw();
					StdDraw.setPenColor(StdDraw.BLACK);
					slopeTo[i-1].draw();
					slopeTo[i].draw();
				
				} else {
					if (equalSlopePointsNum > 3) {
						Point[] pointsOnSegment = new Point[equalSlopePointsNum-1];
//						pointsOnSegment[0] = points[p];
						for (int j = 0; j < equalSlopePointsNum - 1; j++) {
							pointsOnSegment[j] = slopeTo[i - 1 - j];
						}
						Arrays.sort(pointsOnSegment);
						if (points[p].compareTo(pointsOnSegment[0]) < 0) {
							System.out.println("共线点数：" + equalSlopePointsNum + 
											   "最小点：" + pointsOnSegment[0] + 
											   " 最大点：" + pointsOnSegment[equalSlopePointsNum - 2]);
							segments.add(new LineSegment(points[p], pointsOnSegment[equalSlopePointsNum - 2]));
						}
						//该如何避免添加子线段，现有办法较复杂，不知道有没有简便方法
//						segments.add(new LineSegment(pointsOnSegment[0], q))
					}
					equalSlopePointsNum = 2;
				}
			}
			if (equalSlopePointsNum > 3) {
				Point[] pointsOnSegment = new Point[equalSlopePointsNum-1];
//				pointsOnSegment[0] = points[p];
				for (int j = 0; j < equalSlopePointsNum - 1; j++) {
					pointsOnSegment[j] = slopeTo[slopeTo.length - 1 - j];
				}
				Arrays.sort(pointsOnSegment);
				if ((points[p].compareTo(pointsOnSegment[0]) < 0) 
						|| (points[p].compareTo(pointsOnSegment[equalSlopePointsNum - 2]) > 0)) {
						System.out.println("共线点数：" + equalSlopePointsNum + 
										   "最小点：" + pointsOnSegment[0] + 
										   " 最大点：" + pointsOnSegment[equalSlopePointsNum - 2]);
						segments.add(new LineSegment(points[p], pointsOnSegment[equalSlopePointsNum - 2]));
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
		return segments.toArray(new LineSegment[segments.size()]);
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
		points[3] = null;
		points[4] = new Point(1, 3);
		points[5] = new Point(2, 2);
		points[6] = new Point(1, 4);
		points[7] = new Point(3, 3);
		points[8] = new Point(3, 2);
		points[9] = new Point(3, 6);
		points[10] = new Point(2, 0);
		points[11] = new Point(4, 2);
		points[12] = new Point(2, 0);
		FastCollinearPoints fc = new FastCollinearPoints(points);
		for (int i = 0; i < fc.numberOfSegments(); i++)
			System.out.println(fc.segments()[i]);
		
		// read the n points from a file
//	    In in = new In(args[0]);
//	    int n = in.readInt();
//	    Point[] points = new Point[n];
//	    for (int i = 0; i < n; i++) {
//	        int x = in.readInt();
//	        int y = in.readInt();
//	        points[i] = new Point(x, y);
//	    }
//
//	    // draw the points
//	    StdDraw.enableDoubleBuffering();
//	    StdDraw.setXscale(0, 32768);
//	    StdDraw.setYscale(0, 32768);
//	    for (Point p : points) {
//	        p.draw();
//	    }
//	    StdDraw.show();
//
//	    // print and draw the line segments
//	    FastCollinearPoints collinear = new FastCollinearPoints(points);
//	    for (LineSegment segment : collinear.segments()) {
//	        StdOut.println(segment);
//	        segment.draw();
//	    }
//	    StdDraw.show();
	}
}
