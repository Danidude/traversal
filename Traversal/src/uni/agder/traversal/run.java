package uni.agder.traversal;

import java.util.ArrayList;

public class Run {
	static Graph graph;
	static Bruteforce bruteForce;
	static ArrayList<ArrayList<Integer>> solutions;
	public static void main(String [ ] args)
	{
		graph = new Graph();
		Human human1 = new Human();
		human1.setCurrentNode(1);
		graph.addHuman(human1);
		bruteForce = new Bruteforce();
		graph.generateRandomGraph(50, 2);
		solutions = bruteForce.bruteForceGraph(graph);
		if(solutions == null)
		{
			System.err.println("System failure");
		}
		else
			bruteForce.printSolutions(solutions);
	
	}
}
