package com.week5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class KdTree {
	private Node root;
	private class Node {
		private Point2D point;
		private Node left, right;
		private boolean vertical;
		private int N;
		Node(Point2D point, boolean vertical, int N) {
			this.point = point;
			this.vertical = vertical;
			this.N = N;
		}
	}
	// construct an empty set of points
	public KdTree() {
	}
	// is the set empty?
	public boolean isEmpty() {
		return (root == null);
	}
	// number of points in the set
	public int size() {
		return size(root);
	}
	private int size(Node node) {
//		if (isEmpty())		//原版本，这样是错的
//			return 0;
		if (node == null)	//必须这样写
			return 0;
		else return node.N;
	}
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		root = insert(root, p, false);
	}
	private Node insert(Node node, Point2D p, boolean isVertical) {
		if (node == null)
			return new Node(p, isVertical, 1);
		//得65分的版本没有这个判断，插入已经存在的点会出错
		if ((node.point.x() != p.x()) || (node.point.y() != p.y())) {
			if (!node.vertical) {
				if (node.point.x() > p.x())
					node.left = insert(node.left, p, true);
				else node.right = insert(node.right, p, true);
			} else if (node.point.y() > p.y())
						node.left = insert(node.left, p, false);
					else node.right = insert(node.right, p, false);
		} 
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	private boolean contains(Node node, Point2D p) {
		if (node == null)
			return false;
		//68分版本没有以下判断，永远无法查找成功
		if ((node.point.x() == p.x()) && (node.point.y() == p.y())) 
			return true;
		if (!node.vertical) {
			if (node.point.x() > p.x())
				return contains(node.left, p);
			else return contains(node.right, p);
		}
		if (node.point.y() > p.y())
			return contains(node.left, p);
		else return contains(node.right, p);
	}
	// does the set contain point p?
	public boolean contains(Point2D p) {            
		return contains(root, p);
	}
	// draw all points to standard draw
	public void draw() {
		
	}
	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		List<Point2D> list = new ArrayList<>();
		list = (List<Point2D>) range(root, rect,new RectHV(0, 0, 1, 1), list);
		return list;
	}
	//localRect为目前已经分割的矩形
	private Iterable<Point2D> range(Node node, RectHV rect, RectHV localRect, List<Point2D> it) {
		if (node == null)
			return it;
		//75分版本没有这句无法添加点
		if (rect.contains(node.point))
			it.add(node.point);
		if (!node.vertical) {
			if (localRect.intersects(rect)) {
				it = (List<Point2D>) range(node.left, rect, new RectHV(localRect.xmin(), localRect.ymin(),
							node.point.x(), localRect.ymax()),it);
				range(node.right, rect, new RectHV(node.point.x(), localRect.ymin(),
						localRect.xmax(), localRect.ymax()),it);
			}
		} else if (localRect.intersects(rect)) {
				it = (List<Point2D>) range(node.left, rect, new RectHV(localRect.xmin(), localRect.ymin(),
					localRect.xmax(), node.point.y()),it);
				range(node.right, rect, new RectHV(localRect.xmin(), node.point.y(),
				localRect.xmax(), localRect.ymax()),it);
		
		}
		return it;
	}
	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (root == null)
			return null;
		Point2D nearestPoint = nearest(root, root.point, new RectHV(0, 0, 1, 1), p);
		return nearestPoint;
	}
	private Point2D nearest(Node node, Point2D nearestPoint, RectHV rect, Point2D p) {
		if (node == null)
			return nearestPoint;
		double nearest = nearestPoint.distanceSquaredTo(p);
		nearestPoint = node.point.distanceSquaredTo(p) <= nearestPoint.distanceSquaredTo(p) ? node.point : nearestPoint;//这句没有加的话就永远是root
		Point2D leftNearest = nearestPoint;
		Point2D rightNearest = nearestPoint;
		if (!node.vertical) {
			RectHV leftRect = new RectHV(rect.xmin(), rect.ymin(), node.point.x(), rect.ymax());
			RectHV rightRect = new RectHV(node.point.x(), rect.ymin(), rect.xmax(), rect.ymax());
			if (leftRect.distanceSquaredTo(p) < nearest)
				leftNearest = nearest(node.left, leftNearest, leftRect, p);
			if (rightRect.distanceSquaredTo(p) < nearest)
				rightNearest = nearest(node.right, rightNearest, rightRect, p);
		} else {
			RectHV upperRect = new RectHV(rect.xmin(), node.point.y(), rect.xmax(), rect.ymax());
			RectHV lowerRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point.y());
			if (upperRect.distanceSquaredTo(p) < nearest)
				rightNearest = nearest(node.right, rightNearest, upperRect, p);
			if (lowerRect.distanceSquaredTo(p) < nearest)
				leftNearest = nearest(node.left, leftNearest, lowerRect, p);
		}
		return leftNearest.distanceSquaredTo(p) <= rightNearest.distanceSquaredTo(p) ? leftNearest : rightNearest;
	}
	// unit testing of the methods (optional)
	public static void main(String[] args) {
		Point2D p1 = new Point2D(0.1, 0.2);
		Point2D p2 = new Point2D(0.3, 0.2);
		Point2D p3 = new Point2D(0.2, 0.5);
		Point2D p4 = new Point2D(0.7, 0.4);
		Point2D p5 = new Point2D(0.5, 0.1);
		Point2D p6 = new Point2D(0.3, 0.3);
		KdTree ps = new KdTree();
		ps.insert(p1);
		ps.insert(p2);
		ps.insert(p3);
		ps.insert(p4);
		ps.insert(p5);
		ps.insert(p6);
		RectHV r1 = new RectHV(0.1, 0.3, 0.4, 0.6);
//		System.out.println(ps.nearest(new Point2D(0.8, 0.4)));
		System.out.println(ps.range(r1));
//		System.out.println(ps.contains(new Point2D(5,1)));
//		System.out.println(ps.contains(new Point2D(0.7, 0.4)));
	}
}
