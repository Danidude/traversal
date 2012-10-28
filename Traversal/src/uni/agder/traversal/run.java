package uni.agder.traversal;

import java.util.ArrayList;

public class Run {
	static Graph graph;
	static Bruteforce bruteForce;
	static ArrayList<ArrayList<Integer>> solutions;
	public static void main(String [ ] args)
	{
		/*Human human1 = new Human();
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
	*/
		
		graph = new Graph();
		graph.generateRandomGraph(10, 2);
		graph.generateRandomHumans(5);
		RandomTraversal randomTraversal = new RandomTraversal();	
		randomTraversal.randomTraversal(graph.getHumans(), graph.getNodes(), graph, 1, 2);
	}
}
