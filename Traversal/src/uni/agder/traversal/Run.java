package uni.agder.traversal;

import java.util.ArrayList;
import java.util.HashMap;

public class Run {
	//static HashMap<ArrayList<Integer>, Integer> solutions = new HashMap<ArrayList<Integer>, Integer>();
	public static void main(String [ ] args)
	{
		int howManyRoundsToRun = 100;
		
		/*Datasets for Bruteforce, random and antsystem*/
		HashMap<Integer, Integer> antSystemData = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> randomData = new HashMap<Integer, Integer>();
		int bruteforceData = 0;
		
		
		/*Used for creating charts*/
		FreeChartTest chartCreator = new FreeChartTest();
		
		Bruteforce bruteForce = new Bruteforce();		
		Graph graph = new Graph();
		graph.generateRandomGraph(50, 2);
		graph.createExits(2);
		//graph.generateTestCaseGraph();
		graph.createLeathalNodes(2);
		graph.generateRandomHumans(20);
		graph.placeAllHumans();
		graph.printSpecialNodes();
		graph.printHumans();
		bruteForce.bruteForceGraph(graph);
		ArrayList<ArrayList<Integer>> solutions = bruteForce.getListOfSolutions();
		
		bruteforceData = bruteForce.getSurivers();
		
		int randomSurivers = 0;
		for(int i=1; i<howManyRoundsToRun; i++)
		{
			RandomTraversal randomTraversal = new RandomTraversal();
			randomTraversal.randomTraversal(graph.getHumans(), graph.getNodes(), graph, 4, 4);
			
			randomData.put(i, randomTraversal.getSurvives());
			
			randomSurivers += randomTraversal.getSurvives();
		}
		randomSurivers = randomSurivers/howManyRoundsToRun;
		System.out.println("Avrage random sourivers are: "+randomSurivers);
		//bruteForce.printSolutions();
		
		
		float sumAntRuns =0;
		int sumAntSurivers = 0;
		//for(int i=0; i<howManyRoundsToRun; i++)
		for (int numberOfAnts = 1; numberOfAnts<howManyRoundsToRun; numberOfAnts++)
		{
			AntSystem antSys = new AntSystem(graph, numberOfAnts);
			antSys.addSetListOfSolutions(solutions);
			antSys.run();
			//antSys.printPathsFromNodes();
			//antSys.printHowManySourvives();
			//System.out.println("Avrage number of ants running trhough the graph before getting the same path as BruteForce: "+antSys.getAvrageHowManyAntsBeforeEqualBF());
			sumAntRuns += antSys.getAvrageHowManyAntsBeforeEqualBF();
			
			antSystemData.put(numberOfAnts, antSys.getSurivers());
			
			sumAntSurivers += antSys.getSurivers();
		}
		sumAntRuns = sumAntRuns/howManyRoundsToRun;
		sumAntSurivers = sumAntSurivers/howManyRoundsToRun;
		
		chartCreator.createLineChartFromSurivers(antSystemData, randomData, bruteforceData, howManyRoundsToRun);
		
		System.out.println("Avrage number of ants run trough the graph beofre they are as good as bruteforce: "+sumAntRuns);
		System.out.println("Avrage number of surivers in AntSystem: "+sumAntSurivers);
	}
}
