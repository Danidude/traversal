package uni.agder.traversal;

import java.util.ArrayList;
import java.util.HashMap;

public class Run {
	//static HashMap<ArrayList<Integer>, Integer> solutions = new HashMap<ArrayList<Integer>, Integer>();
	public static void main(String [ ] args)
	{
		
		Bruteforce bruteForce = new Bruteforce();		
		Graph graph = new Graph();
		/*graph.generateRandomGraph(20, 2);
		graph.createExits(2);*/
		graph.generateTestCaseGraph();
		graph.createLeathalNodes(2);
		graph.generateRandomHumans(10);
		graph.placeAllHumans();
		graph.printSpecialNodes();
		graph.printHumans();
		bruteForce.bruteForceGraph(graph);
		ArrayList<ArrayList<Integer>> solutions = bruteForce.getListOfSolutions();
		RandomTraversal randomTraversal = new RandomTraversal();	
		randomTraversal.randomTraversal(graph.getHumans(), graph.getNodes(), graph, 4, 4);
		//bruteForce.printSolutions();
		
		AntSystem antSys = new AntSystem(graph);
		antSys.addSetListOfSolutions(solutions);
		antSys.run();
		//antSys.printPathsFromNodes();
		antSys.printHowManySourvives();
		System.out.println("Avrage number of ants running trhough the graph before getting the same path as BruteForce: "+antSys.getAvrageHowManyAntsBeforeEqualBF());

	}
}
