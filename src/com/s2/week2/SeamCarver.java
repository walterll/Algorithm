package com.s2.week2;

import java.awt.Color;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/************************************
 * 算法（下）第二周作业
 * 创建一个SeamCarver类，用于对图片进行SeamCarving处理
 * SeamCarving为一种将图片根据像素内容缩小而非根据比例缩小的算法
 * 拥有removeVerticalSeam和removeHorizontalSeam两个方法
 * 分别从竖向和横向移除一个像素，从而缩小图片的宽度和高度
 * 每次移除的一列或一行为对于图片影响最小的像素，因此可以保留物体的边缘和图像主要信息
 * 具体做法是先根据每个像素和相邻像素的rgb值得差计算出一个energy矩阵
 * 然后使用自己改进的面对像素的dijkstra算法找出总energy最小的一列或一行
 * （一列指的是图像中纵向连续的像素，左上、右上、左下、右下的像素均为连续像素）
 * 最后将找出的最小列/行移除
 * @author Walterll
 *
 */
public class SeamCarver {
	private Picture picture;
	private int width;
	private int height;
	// create a seam carver object based on the given picture
	public SeamCarver(Picture picture) {
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
	//energy矩阵的计算方法为，某个像素的上面和下面像素rgb值的差值的平方和以及左侧和右侧像素rgb值差值得平方和的和的开方
	public double energy(int x, int y) {
		double energy = 0.0;
		//图片最边缘的energy值为默认值1000
		if (x == 0 || x == width - 1 || y == 0 || y == height - 1)
			energy = 1000;
		else {
			Color colorUp = picture.get(x, y - 1);		//上方像素
			Color colorDown = picture.get(x,  y + 1);	//下方像素
			Color colorLeft = picture.get(x - 1, y);	//左侧像素
			Color colorRight = picture.get(x + 1, y);	//右侧像素
			//计算差值的平方和
			energy = (colorUp.getRed() - colorDown.getRed()) * (colorUp.getRed() - colorDown.getRed())
					+ (colorUp.getGreen() - colorDown.getGreen()) * (colorUp.getGreen() - colorDown.getGreen())
					+ (colorUp.getBlue() - colorDown.getBlue()) * (colorUp.getBlue() - colorDown.getBlue())
					+ (colorLeft.getRed() - colorRight.getRed()) * (colorLeft.getRed() - colorRight.getRed())
					+ (colorLeft.getGreen() - colorRight.getGreen()) * (colorLeft.getGreen() - colorRight.getGreen())
					+ (colorLeft.getBlue() - colorRight.getBlue()) * (colorLeft.getBlue() - colorRight.getBlue());
			//开方
			energy = Math.sqrt(energy);
		}
		return energy;
	}
	
//	//返回图片像素的拓扑排序
//	private Stack<Integer> topological(boolean isVertical) {
//		int w = width;
//		int	h = height;
//		Stack<Integer> reversePost = new Stack<>();
//		boolean[] marked = new boolean[w * h];
//		for (int v = 0; v < w * h; v++) 
//			if (!marked[v]) 
//				dfs(isVertical, w, h, marked, reversePost, v);
//		return reversePost;
//	}
//	private void dfs(boolean isVertical, int w, int h, boolean[] marked, Stack<Integer> reversePost, int v) {
//		marked[v] = true;
//		if (isVertical) {
//			//未到最后一列
//			if ((v % w) < (w - 1)) {
//				if ((v / w) > 0)
//					if (!marked[v - w + 1])
//						dfs(isVertical, w, h, marked, reversePost, v - w + 1);
//				if (!marked[v + 1])
//					dfs(isVertical, w, h, marked, reversePost, v + 1);
//				if ((v / w) < h - 1)
//					if (!marked[v + w + 1])
//						dfs(isVertical, w, h, marked, reversePost, v + w + 1);
//			}
//		} else {
//			//未到最后一行
//			if ((v / w) < (h - 1)) {
//				if ((v % w) > 0) 
//					if (!marked[v + w - 1])
//						dfs(isVertical, w, h, marked, reversePost, v + w - 1);
//				if (!marked[v + w])
//					dfs(isVertical, w, h, marked, reversePost, v + w);
//				if ((v % w) < w - 1)
//					if (!marked[v + w + 1])
//						dfs(isVertical, w, h, marked, reversePost, v + w + 1);
//			}			
//		}
//		reversePost.push(v);
//	}
	/*****************
	 * 返回图片像素的拓扑排序
	 * 像素矩阵为一个有向无环图
	 * 每个像素按顺序从0开始索引
	 * 例：4*6图片的像素的索引为
	 * 0  1  2  3 
	 * 4  5  6  7
	 * 8  9  10 11
	 * 12 13 14 15
	 * 16 17 18 19
	 * 20 21 22 23
	 * 当拓扑方向为竖直时，每个像素的连接到的像素为左下、下方和右下的像素
	 * 即：1连接4、5、6，即存在三条有向边1》4、1》5、1》6
	 * 若拓扑方向为水平时，仅仅将energy矩阵翻转后按照同竖直情况相同的方法处理
	 * 因此仅仅需要将长宽值对换，4*6图片计算水平拓扑顺序时按照6*4图片的竖直方向计算
	 * @param isVertical 拓扑方向是否为竖直
	 * @return 返回拓扑顺序的索引
	 */	
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
				//迭代调用深度优先搜索
				dfs(w, h, marked, reversePost, v);
		return reversePost;
	}
	//改写的深度优先搜索，将相邻点的搜索改为搜索左下、下方和右下的点
	private void dfs(int w, int h, boolean[] marked, Stack<Integer> reversePost, int v) {
		marked[v] = true;
		//未到最后一行，最后一行的像素不存在下一条边
		if ((v / w) < (h - 1)) {
			//若不是最左侧的像素，则存在左下方的边
			if ((v % w) > 0) 
				//左下的像素还未标记
				if (!marked[v + w - 1])
					dfs(w, h, marked, reversePost, v + w - 1);
			//下方像素还未标记
			if (!marked[v + w])
				dfs(w, h, marked, reversePost, v + w);
			//若不是最右侧的像素，则存在右下方的边
			if ((v % w) < w - 1)
				//右下的像素还未标记
				if (!marked[v + w + 1])
					dfs(w, h, marked, reversePost, v + w + 1);
		}			
		//每次完成一个迭代将v推入栈，v是当前线路中最深的点，逆序即为拓扑排序。原理详见算法：拓扑排序
		reversePost.push(v);
	}
	
	
	// sequence of indices for horizontal seam
	/******************
	 * 找出当前图片中水平方向最小的一排连续的像素
	 * 采用方法是计算图像的翻转的energy矩阵，其他原理同findVerticalSeam
	 * @return
	 */
	public int[] findHorizontalSeam() {
		//建立二维数组时需了解清楚两个维度与图像高与宽的映射关系，此处double[width][height]实际将高为height，长为width的图片翻转
		double[][] energy = new double[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				//二维数组的两个维度同图像中的（x,y）是相反的，因此energy[i][j]=energy(i,j)就是将图像的对称像素存入数组
				//此处非常容易出错，包括二维数组循环的方法也需非常清楚，这里卡了很久
				energy[i][j] = energy(i, j);
			}
		}
//		//测试翻转矩阵
//		for (int row = 0; row < width; row++) {
//            for (int col = 0; col < height; col++)
//                StdOut.printf("%9.0f ", energy[row][col]);
//            StdOut.println();
//        }
//		System.out.println("???");
		//////////////////////
		Stack<Integer> top = topological(false);
		int[] horizontalSeam = new int[width];
		Stack<Integer> seam = AcyclicSP(false, top, energy);
		for (int i = 0; i < width; i++)
			horizontalSeam[i] = seam.pop();
//		for (int i = 0; i < width; i++)
//			System.out.println(horizontalSeam[i]);
		return horizontalSeam;
	}
	/**********************
	 * 使用修改的dijkstra算法按照拓扑排序计算energy矩阵的最小路径
	 * 不使用有向图和边，直接面向像素计算最小路径，length为energy值
	 * @param isVertical	是否竖直方向
	 * @param top			拓扑排序的索引
	 * @param energy		energy矩阵
	 * @return
	 */
	private Stack<Integer> AcyclicSP(boolean isVertical, Iterable<Integer> top, double[][] energy) {
		int w, h;
		//计算水平方向时使用翻转的energy矩阵，因此将高和宽对换
		if (!isVertical) {
			w = height;
			h = width;
		} else {
			w = width;
			h = height;
		}
		int[] edgeTo = new int[w * h];					//存储每个像素点的上一个像素点
		double[] distTo = new double[w * h];			//存储到达每个像素点的距离
		IndexMinPQ<Double> pq = new IndexMinPQ<>(w * h);//优先队列，队列顶端为目前距离最近的点
		//先给所有点的距离设为无穷大，即不可达
		for (int v = 0; v < w * h; v++) 
			distTo[v] = Double.POSITIVE_INFINITY;
		//为第一行的点赋值，否则全部点为infinity无法正常relax，第一行的索引值和自己的横坐标相同，因此i点即为energy[0][i]
		for (int i = 0; i < w; i++) {
			distTo[i] = energy[0][i];	//此处注意，energy[0][i]指的是energy矩阵第一行第i列的点
			pq.insert(i, energy[0][i]);	//同时将此点存入优先队列，用于后面的放松操作
		}
		//根据拓扑排序遍历索引，进行relax操作
		//具体方法为比较i点的距离值和i点左下（下方、右下）点的energy值得和是否小于左下点的距离值，来判断是否需要relax
		for (int i : top) {
			//根据索引获得对应的点的坐标，例：4*6图像的索引为6的点的坐标为（2，1）
			int x = i % w;
			int y = i / w;
			//该点不是最后一行
			if (y < h - 1) {
				//该点不是最左一列
				if (x > 0)
					//此处就是之前搞反了energy矩阵和坐标的对应关系
//					if (distTo[i + w - 1] > distTo[i] + energy[x - 1][y + 1]) {
//						distTo[i + w - 1] = distTo[i] + energy[x - 1][y + 1];
					//与左下的点比较，i+w-1就是索引i的左下点的索引，坐标为（x-1,y+1），对应energy[y+1][x-1]
					if (distTo[i + w - 1] > distTo[i] + energy[y + 1][x - 1]) {
						//将这条边放松，重新计算i+w-1点的距离值，并且将edgeTo[i+w-1]设为i点
						distTo[i + w - 1] = distTo[i] + energy[y + 1][x - 1];
						edgeTo[i + w - 1] = i;
						//将i+w-1点插入优先队列或者修改优先队列中的值
						if (pq.contains(i + w - 1))
							pq.change(i + w - 1, distTo[i + w - 1]);
						else
							pq.insert(i + w - 1, distTo[i + w - 1]);
					}
				//与下面的点比较
//				if (distTo[i + w] > distTo[i] + energy[x][y + 1]) {
//					distTo[i + w] = distTo[i] + energy[x][y + 1];
				if (distTo[i + w] > distTo[i] + energy[y + 1][x]) {
					distTo[i + w] = distTo[i] + energy[y + 1][x];
					edgeTo[i + w] = i;
					if (pq.contains(i + w))
						pq.change(i + w, distTo[i + w]);
					else
						pq.insert(i + w, distTo[i + w]);
				}
				//与右下的点比较
				if (x < w - 1)
//					if (distTo[i + w + 1] > distTo[i] + energy[x + 1][y + 1]) {
//						distTo[i + w + 1] = distTo[i] + energy[x + 1][y + 1];
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
		//全部点遍历完成后，每个点都保存了距离和路径
		double minDistance = 0.0;
		int minPix = 0;
		//找出最后一排距离最小的点
		for (int i = 0; i < w; i++) {
			if (minDistance == 0.0 || distTo[w * (h - 1) + i] < minDistance) {
				minDistance = distTo[w * (h - 1) + i];
				minPix = i;
			}
		}
		System.out.println(minPix);
		System.out.println(minDistance);
		Stack<Integer> seam = new Stack<>();		//seam存储最小距离的路径上的点
		seam.push(minPix);							//先将最后一个点推入栈，minPix为点的横坐标
		//从最后一行开始向上遍历，获得每一行在最短路径上的点的横坐标
		//例：若最小路径的点为（3，0），（2，1），（1，2），（2，3），（1，4），（2，5）
		//则获得的最小路径的数组为（3，2，1，2，1，2）
		for (int col = 0; col < h - 1; col++) {
			minPix = edgeTo[w * (h - col - 1) + minPix] % w;	//edgeTo[w*(h-col-1)+minPix]是索引值，%w转换为横坐标
			seam.push(minPix);
		}
//		for (Integer i : seam) {
//			System.out.println(i);
//		}
		return seam;
	}
	// sequence of indices for vertical seam
	/****************************
	 * 找出竖直方向的最小路径
	 * @return
	 */
	public int[] findVerticalSeam() {
		double[][] energy = new double[height][width];		//二维数组存储energy值
		//注意外循环和内循环的顺序
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				energy[i][j] = energy(j, i);		//二维数组[x][y]和点的坐标(x,y)是相反的，特别注意
			}
		}
		Stack<Integer> top = topological(true);		//获得索引竖直方向的拓扑排序
		int[] verticalSeam = new int[height];
		Stack<Integer> seam = AcyclicSP(true, top, energy);	//按照拓扑排序，获得竖直方向的最小路径
		for (int i = 0; i < height; i++)
			verticalSeam[i] = seam.pop();			//将存储最小路径的stack转换成数组返回
		for (int i = 0; i < height; i++)
			System.out.println(verticalSeam[i]);
		return verticalSeam;
	}
	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {  
		Picture newPicture = new Picture(width, height - 1);
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height - 1; row++) {
				//将需要删去的点的下方的像素上移一个像素即完成删除操作
				if (row >= seam[col])
					newPicture.set(col, row, picture.get(col, row + 1));
				else
					newPicture.set(col, row, picture.get(col, row));
			}
		}
//		System.out.println(picture.height());
		height--;
		picture = newPicture;		//修改picture变量使其指向新的图片
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
//		System.out.println(picture.height());
		width--;
		picture = newPicture;
	}
	
	public static void main(String[] args) {
//		Picture picture = new Picture("12x10.png");
//        StdOut.printf("image is %d pixels wide by %d pixels high.\n", picture.width(), picture.height());
//        
//        SeamCarver sc = new SeamCarver(picture);
//        
//        StdOut.printf("Printing energy calculated for each pixel.\n");        
//
//        for (int row = 0; row < sc.height(); row++) {
//            for (int col = 0; col < sc.width(); col++)
//                StdOut.printf("%9.0f ", sc.energy(col, row));
//            StdOut.println();
//        }
//        System.out.println(sc.topological(true));
//        sc.findVerticalSeam();
		Picture inputImg = new Picture("chameleon.png");
        int removeColumns = 100;
        int removeRows = 50; 

        StdOut.printf("image is %d columns by %d rows\n", inputImg.width(), inputImg.height());
        SeamCarver sc = new SeamCarver(inputImg);

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
