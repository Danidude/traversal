package uni.agder.traversal;

import java.util.ArrayList;
import java.util.HashMap;

public class Run {
	//static HashMap<ArrayList<Integer>, Integer> solutions = new HashMap<ArrayList<Integer>, Integer>();
	public static void main(String [ ] args)
	{
		int howManyRoundsToRun = 1000;
		
		Bruteforce bruteForce = new Bruteforce();		
		Graph graph = new Graph();
		/*graph.generateRandomGraph(40, 2);
		graph.createExits(2);*/
		graph.generateTestCaseGraph();
		graph.createLeathalNodes(2);
		graph.generateRandomHumans(20);
		graph.placeAllHumans();
		graph.printSpecialNodes();
		graph.printHumans();
		bruteForce.bruteForceGraph(graph);
		ArrayList<ArrayList<Integer>> solutions = bruteForce.getListOfSolutions();
		
		int randomSurivers = 0;
		for(int i=0; i<howManyRoundsToRun; i++)
		{
			RandomTraversal randomTraversal = new RandomTraversal();	
			randomTraversal.randomTraversal(graph.getHumans(), graph.getNodes(), graph, 4, 4);
			randomSurivers += randomTraversal.getSurvives();
		}
		randomSurivers = randomSurivers/howManyRoundsToRun;
		System.out.println("Avrage random sourivers are: "+randomSurivers);
		//bruteForce.printSolutions();
		
		
		float sumAntRuns =0;
		int sumAntSurivers = 0;
		for(int i=0; i<howManyRoundsToRun; i++)
		{
			AntSystem antSys = new AntSystem(graph);
			antSys.addSetListOfSolutions(solutions);
			antSys.run();
			//antSys.printPathsFromNodes();
			//antSys.printHowManySourvives();
			//System.out.println("Avrage number of ants running trhough the graph before getting the same path as BruteForce: "+antSys.getAvrageHowManyAntsBeforeEqualBF());
			sumAntRuns += antSys.getAvrageHowManyAntsBeforeEqualBF();
			sumAntSurivers += antSys.getSurivers();
		}
		sumAntRuns = sumAntRuns/howManyRoundsToRun;
		sumAntSurivers = sumAntSurivers/howManyRoundsToRun;
		System.out.println("Avrage number of ants run trough the graph beofre they are as good as bruteforce: "+sumAntRuns);
		System.out.println("Avrage number of surivers in AntSystem: "+sumAntSurivers);
	}
}
