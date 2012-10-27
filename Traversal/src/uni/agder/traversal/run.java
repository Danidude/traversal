package uni.agder.traversal;

import java.util.ArrayList;

public class run {
	static Graph graph;
	static Bruteforce bruteForce;
	static ArrayList<ArrayList<Integer>> solutions;
	public static void main(String [ ] args)
	{
		graph = new Graph();
		bruteForce = new Bruteforce();
		graph.generateGraph();
		solutions = bruteForce.bruteForceGraph(graph);
		if(solutions == null)
		{
			System.err.println("System failure");
		}
		else
			bruteForce.printSolutions(solutions);
	
	}
}
