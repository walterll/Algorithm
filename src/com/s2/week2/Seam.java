package com.s2.week2;

import java.awt.Color;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Seam {
	private Picture picture;
	private int width;
	private int height;
	// create a seam carver object based on the given picture
	public Seam(Picture picture) {
		this.picture = picture;
		this.width = picture.width();
		this.height = picture.height();
	}
	// current picture
	public Picture picture() {
		return picture;
	}
	// width of current picture
	public int width() {
		return width;
	}
	// height of current picture
	public int height() {
		return height;
	}
	// energy of pixel at column x and row y
	//作业指定提交的版本
//	public double energy(int x, int y) {
//		double energy = 0.0;
//		if (x == 0 || x == width - 1 || y == 0 || y == height - 1)
//			energy = 1000;
//		else {
//			Color colorUp = picture.get(x, y - 1);
//			Color colorDown = picture.get(x,  y + 1);
//			Color colorLeft = picture.get(x - 1, y);
//			Color colorRight = picture.get(x + 1, y);
//			energy = (colorUp.getRed() - colorDown.getRed()) * (colorUp.getRed() - colorDown.getRed())
//					+ (colorUp.getGreen() - colorDown.getGreen()) * (colorUp.getGreen() - colorDown.getGreen())
//					+ (colorUp.getBlue() - colorDown.getBlue()) * (colorUp.getBlue() - colorDown.getBlue())
//					+ (colorLeft.getRed() - colorRight.getRed()) * (colorLeft.getRed() - colorRight.getRed())
//					+ (colorLeft.getGreen() - colorRight.getGreen()) * (colorLeft.getGreen() - colorRight.getGreen())
//					+ (colorLeft.getBlue() - colorRight.getBlue()) * (colorLeft.getBlue() - colorRight.getBlue());
//			energy = Math.sqrt(energy);
//		}
//		return energy;
//	}
	//自己尝试优化的版本，同时考虑斜边像素差异
	public double energy(int x, int y) {
		double energy = 0.0;
		if (x == 0 || x == width - 1 || y == 0 || y == height - 1)
			energy = 1000;
		else {
			Color colorUp = picture.get(x, y - 1);
			Color colorDown = picture.get(x,  y + 1);
			Color colorLeft = picture.get(x - 1, y);
			Color colorRight = picture.get(x + 1, y);
			Color colorLeftUp = picture.get(x - 1, y - 1);
			Color colorRightUp = picture.get(x + 1, y - 1);
			Color colorLeftDown = picture.get(x - 1, y + 1);
			Color colorRightDown = picture.get(x + 1, y + 1);
			energy = (colorUp.getRed() - colorDown.getRed()) * (colorUp.getRed() - colorDown.getRed())
					+ (colorUp.getGreen() - colorDown.getGreen()) * (colorUp.getGreen() - colorDown.getGreen())
					+ (colorUp.getBlue() - colorDown.getBlue()) * (colorUp.getBlue() - colorDown.getBlue())
					+ (colorLeft.getRed() - colorRight.getRed()) * (colorLeft.getRed() - colorRight.getRed())
					+ (colorLeft.getGreen() - colorRight.getGreen()) * (colorLeft.getGreen() - colorRight.getGreen())
					+ (colorLeft.getBlue() - colorRight.getBlue()) * (colorLeft.getBlue() - colorRight.getBlue())
					+ (colorLeftUp.getRed() - colorRightDown.getRed()) * (colorRightDown.getRed() - colorRightDown.getRed())
					+ (colorLeftUp.getGreen() - colorRightDown.getGreen()) * (colorRightDown.getGreen() - colorRightDown.getGreen())
					+ (colorLeftUp.getBlue() - colorRightDown.getBlue()) * (colorRightDown.getBlue() - colorRightDown.getBlue())
					+ (colorRightUp.getRed() - colorLeftDown.getRed()) * (colorRightUp.getRed() - colorLeftDown.getRed())
					+ (colorRightUp.getGreen() - colorLeftDown.getGreen()) * (colorRightUp.getGreen() - colorLeftDown.getGreen())
					+ (colorRightUp.getBlue() - colorLeftDown.getBlue()) * (colorRightUp.getBlue() - colorLeftDown.getBlue());
			energy = Math.sqrt(energy);
		}
		return energy;
	}
	

		private Stack<Integer> topological(boolean isVertical) {
			int w,h;
			if (!isVertical) {
				h = width;
				w = height;
			} else {
				w = width;
				h = height;
			}
			Stack<Integer> reversePost = new Stack<>();
			boolean[] marked = new boolean[w * h];
			for (int v = 0; v < w * h; v++) 
				if (!marked[v]) 
					dfs(w, h, marked, reversePost, v);
			return reversePost;
		}
	private void dfs(int w, int h, boolean[] marked, Stack<Integer> reversePost, int v) {
		marked[v] = true;

		if ((v / w) < (h - 1)) {
			if ((v % w) > 0) 
				if (!marked[v + w - 1])
					dfs(w, h, marked, reversePost, v + w - 1);
			if (!marked[v + w])
				dfs(w, h, marked, reversePost, v + w);
			if ((v % w) < w - 1)
				if (!marked[v + w + 1])
					dfs(w, h, marked, reversePost, v + w + 1);
		}			
	
		reversePost.push(v);
	}
	
	
	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		double[][] energy = new double[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				energy[i][j] = energy(i, j);
			}
		}

		Stack<Integer> top = topological(false);
		int[] horizontalSeam = new int[width];
		Stack<Integer> seam = AcyclicSP(false, top, energy);
		for (int i = 0; i < width; i++)
			horizontalSeam[i] = seam.pop();

		return horizontalSeam;
	}
	
	private Stack<Integer> AcyclicSP(boolean isVertical, Iterable<Integer> top, double[][] energy) {
		int w, h;
		if (!isVertical) {
			w = height;
			h = width;
		} else {
			w = width;
			h = height;
		}
		int[] edgeTo = new int[w * h];
		double[] distTo = new double[w * h];
		IndexMinPQ<Double> pq = new IndexMinPQ<>(w * h);
		for (int v = 0; v < w * h; v++) 
			distTo[v] = Double.POSITIVE_INFINITY;

		for (int i = 0; i < w; i++) {
			distTo[i] = energy[0][i];
			pq.insert(i, energy[0][i]);
		}
		for (int i : top) {
			int x = i % w;
			int y = i / w;
			if (y < h - 1) {
				if (x > 0)
					if (distTo[i + w - 1] > distTo[i] + energy[y + 1][x - 1]) {
						distTo[i + w - 1] = distTo[i] + energy[y + 1][x - 1];
						edgeTo[i + w - 1] = i;
						if (pq.contains(i + w - 1))
							pq.change(i + w - 1, distTo[i + w - 1]);
						else
							pq.insert(i + w - 1, distTo[i + w - 1]);
					}
				
				if (distTo[i + w] > distTo[i] + energy[y + 1][x]) {
					distTo[i + w] = distTo[i] + energy[y + 1][x];
					edgeTo[i + w] = i;
					if (pq.contains(i + w))
						pq.change(i + w, distTo[i + w]);
					else
						pq.insert(i + w, distTo[i + w]);
				}
				if (x < w - 1)
					if (distTo[i + w + 1] > distTo[i] + energy[y + 1][x + 1]) {
						distTo[i + w + 1] = distTo[i] + energy[y + 1][x + 1];
						edgeTo[i + w + 1] = i;
						if (pq.contains(i + w + 1))
							pq.change(i + w + 1, distTo[i + w + 1]);
						else
							pq.insert(i + w + 1, distTo[i + w + 1]);
					}
			}
		}
		double minDistance = 0.0;
		int minPix = 0;
		for (int i = 0; i < w; i++) {
			if (minDistance == 0.0 || distTo[w * (h - 1) + i] < minDistance) {
				minDistance = distTo[w * (h - 1) + i];
				minPix = i;
			}
		}
		Stack<Integer> seam = new Stack<>();
		seam.push(minPix);
		for (int col = 0; col < h - 1; col++) {
			minPix = edgeTo[w * (h - col - 1) + minPix] % w;
			seam.push(minPix);
		}

		return seam;
	}
	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		double[][] energy = new double[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				energy[i][j] = energy(j, i);
			}
		}
		Stack<Integer> top = topological(true);
		int[] verticalSeam = new int[height];
		Stack<Integer> seam = AcyclicSP(true, top, energy);
		for (int i = 0; i < height; i++)
			verticalSeam[i] = seam.pop();
		return verticalSeam;
	}
	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {  
		Picture newPicture = new Picture(width, height - 1);
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height - 1; row++) {
				if (row >= seam[col])
					newPicture.set(col, row, picture.get(col, row + 1));
				else
					newPicture.set(col, row, picture.get(col, row));
			}
		}

		height--;
		picture = newPicture;
	}
	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {    
		Picture newPicture = new Picture(width - 1, height);
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width - 1; col++) {
				if (col >= seam[row])
					newPicture.set(col, row, picture.get(col + 1, row));
				else
					newPicture.set(col, row, picture.get(col, row));
			}
		}

		width--;
		picture = newPicture;
	}
	
	public static void main(String[] args) {

		Picture inputImg = new Picture("chameleon.png");
        int removeColumns = 100;
        int removeRows = 0; 

        StdOut.printf("image is %d columns by %d rows\n", inputImg.width(), inputImg.height());
        Seam sc = new Seam(inputImg);

        for (int i = 0; i < removeRows; i++) {
            int[] horizontalSeam = sc.findHorizontalSeam();
            sc.removeHorizontalSeam(horizontalSeam);
        }

        for (int i = 0; i < removeColumns; i++) {
            int[] verticalSeam = sc.findVerticalSeam();
            sc.removeVerticalSeam(verticalSeam);
        }
        Picture outputImg = sc.picture();

        StdOut.printf("new image size is %d columns by %d rows\n", sc.width(), sc.height());

        inputImg.show();
        outputImg.show();
	}
}
