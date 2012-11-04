package uni.agder.traversal;

import java.util.ArrayList;

public class Run {
	static ArrayList<ArrayList<Integer>> solutions;
	public static void main(String [ ] args)
	{
		/*Human human1 = new Human();
		human1.setCurrentNode(1);
		graph.addHuman(human1);
		
		graph.generateRandomGraph(50, 2);
		solutions = bruteForce.bruteForceGraph(graph);
		if(solutions == null)
		{
			System.err.println("System failure");
		}
		else
			bruteForce.printSolutions(solutions);
	*/
		Bruteforce bruteForce = new Bruteforce();		
		Graph graph = new Graph();
		graph.generateRandomGraph(50, 2);
		graph.generateRandomHumans(20);
		graph.createExits(2);
		graph.createLeathalNodes(5);
		graph.placeAllHumans();
		bruteForce.bruteForceGraph(graph);
		RandomTraversal randomTraversal = new RandomTraversal();	
		randomTraversal.randomTraversal(graph.getHumans(), graph.getNodes(), graph, 4, 4);
		
	}
}
