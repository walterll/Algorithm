package com.s2.week3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;

public class BaseballElimination {
	private int teamNumber;
	private Map<String, Integer> team;
	private int[] w;
	private int[] l;
	private int[] r;
	private int[][] g;
	// create a baseball division from given filename in format specified below
	public BaseballElimination(String filename) {
		In in = new In(filename);
		teamNumber = Integer.valueOf(in.readLine());
		team = new HashMap<>();
		w = new int[teamNumber];
		l = new int[teamNumber];
		r = new int[teamNumber];
		g = new int[teamNumber][teamNumber];
		for (int i = 0; i < teamNumber; i++) {
			team.put(in.readString(), i);
			w[i] = in.readInt();
			l[i] = in.readInt();
			r[i] = in.readInt();
			for (int j = 0; j < teamNumber; j++) {
				g[i][j] = in.readInt();
			}
		}
//		for (int i = 0; i < teamNumber; i++) {
//			for (int j = 0; j < teamNumber; j++) {
//				System.out.println(g[i][j] + " ");
//			}
//			System.out.println();
//		}
	}
	// number of teams
	public int numberOfTeams() {
		return teamNumber;
	}
	// all teams
	public Iterable<String> teams() {
		Set<String> teams = team.keySet();
		return teams;
	}
	// number of wins for given team
	public int wins(String team) {
		return w[this.team.get(team)];
	}
	// number of losses for given team
	public int losses(String team) {
		return l[this.team.get(team)];
	}
	// number of remaining games for given team
	public int remaining(String team) {
		return r[this.team.get(team)];
	}
	// number of remaining games between team1 and team2
	public int against(String team1, String team2) {
		return g[this.team.get(team1)][this.team.get(team2)];
	}
	// is given team eliminated?
	public boolean isEliminated(String team) {
		int v1 = 1;
		int v2 = 1 + (teamNumber - 1) * (teamNumber - 2) / 2;
		int vt = 1 + (teamNumber - 1) * (teamNumber - 2) / 2 + (teamNumber - 1);
		int e1 = 0;
		int e2 = (teamNumber - 1) * (teamNumber - 2) / 2;
		int e3 = (teamNumber - 1) * (teamNumber - 2) / 2 * 3;
		boolean flag = false;
		FlowEdge[] edge = new FlowEdge[(teamNumber - 1) * (teamNumber - 2) / 2 * 3 + teamNumber - 1];
		for (int i = 0; i < teamNumber; i++) {
			if (i == this.team.get(team)) {
				flag = false;
				continue;
			}
			for (int j = i + 1; j < teamNumber; j++) {
				if (j == this.team.get(team)) {
					flag = true;
					continue;
				}
				edge[e1] = new FlowEdge(0, v1, g[i][j]);
				edge[e2] = new FlowEdge(v1, v2, Double.POSITIVE_INFINITY);
				if (flag)//原本没有此处判断，下标存在bug
					edge[++e2] = new FlowEdge(v1, v2 + (j - i - 1), Double.POSITIVE_INFINITY);
				else
					edge[++e2] = new FlowEdge(v1, v2 + (j - i), Double.POSITIVE_INFINITY);
				v1++;
				e1++;
				e2++;
			}
			if ((wins(team) + remaining(team) - w[i]) < 0)
				return false;
			edge[e3] = new FlowEdge(v2, vt, wins(team) + remaining(team) - w[i]);
			v2++;
			e3++;
		}
		FlowNetwork network = new FlowNetwork(vt + 1);
		for (int i = 0; i < edge.length; i++) 
			network.addEdge(edge[i]);
//			System.out.println(edge[i]);
		System.out.println(network);
		FordFulkerson ff = new FordFulkerson(network, 0, vt);
		System.out.println("最大流：" + ff.value());
		for (int i = 0; i < teamNumber - 1; i++) {
			if (ff.inCut(1 + (teamNumber - 1) * (teamNumber - 2) / 2 + i))
				System.out.println("被此队淘汰:" + i);
					
		}
		return false;
	}
	// subset R of teams that eliminates given team; null if not eliminated
	public Iterable<String> certificateOfElimination(String team) {
		return null;
	}
	public static void main(String[] args) {
		BaseballElimination be = new BaseballElimination("teams4.txt");
		
//		System.out.println("Philadelphia赢了：" + be.wins("Philadelphia"));
//		System.out.println("Atlanta输了：" + be.losses("Atlanta"));
//		System.out.println("Montreal还剩：" + be.remaining("Montreal"));
//		System.out.println("New_York和Atlanta还有：" + be.against("New_York", "Atlanta"));
		be.isEliminated("Philadelphia");
	}
}
