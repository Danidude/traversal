package uni.agder.traversal;

import java.util.ArrayList;

public class Run {
	static ArrayList<ArrayList<Integer>> solutions;
	public static void main(String [ ] args)
	{
		
		Bruteforce bruteForce = new Bruteforce();		
		Graph graph = new Graph();
		graph.generateRandomGraph(20, 2);
		graph.generateRandomHumans(20);
		graph.createExits(2);
		graph.createLeathalNodes(5);
		graph.placeAllHumans();
		bruteForce.bruteForceGraph(graph);
		RandomTraversal randomTraversal = new RandomTraversal();	
		randomTraversal.randomTraversal(graph.getHumans(), graph.getNodes(), graph, 4, 4);
		AntSystem antSys = new AntSystem(graph);
		antSys.run();

	}
}
