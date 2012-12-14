package uni.agder.traversal;

import java.util.ArrayList;
import java.util.HashMap;

public class Run {
	//static HashMap<ArrayList<Integer>, Integer> solutions = new HashMap<ArrayList<Integer>, Integer>();
	public static void main(String [ ] args)
	{
		int howManyHumans = 20;
		int howManyRoundsToRun = 100;
		int howManyAntsToRun = 100;
		
		/*Datasets for Bruteforce, random and antsystem*/
		HashMap<Integer, Integer> antSystemData = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> randomData = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> bruteForceData = new HashMap<Integer, Integer>();
		
		int ibruteforceData = 0;
		
		
		/*Used for creating charts*/
		FreeChartTest chartCreator = new FreeChartTest();
		
				
		Graph graph = new Graph();
		graph.generateRandomGraph(40, 2);
		graph.createExits(2);
		//graph.generateTestCaseGraph();
		graph.createLeathalNodes(10);
		graph.generateRandomHumans(howManyHumans);
		graph.placeAllHumans();
		graph.printSpecialNodes();
		graph.getHumans().get(0).setCurrentNode(0);
		//graph.printHumans();
		
		int randomSurvivors = 0;
		float sumAntRuns =0;
		int sumAntSurvivors = 0;
		/*Used for to check how far the loop is.*/
		int checkHowFar = 0;
		
		/*
		ibruteforceData = ibruteforceData/howManyRoundsToRun;
		randomSurivers = randomSurivers/howManyRoundsToRun;
		System.out.println("In bruteforce "+ibruteforceData+" people survived out of "+howManyHumans);
		System.out.println("Avrage random sourivers are: "+randomSurivers);*/
		
		//bruteForce.printSolutions();
				
				
		for (int numberOfAnts = 1; numberOfAnts<howManyAntsToRun; numberOfAnts++)
		{	
			sumAntSurvivors = 0;
			for(int i=1; i<howManyRoundsToRun; i++)
			{
				graph.changeLeathalNdes(10);
				graph.generateRandomHumans(howManyHumans);
				graph.placeAllHumans();
				
				Bruteforce bruteForce = new Bruteforce();
				bruteForce.bruteForceGraph(graph);
				
				//ArrayList<ArrayList<Integer>> solutions = bruteForce.getListOfSolutions();
				
				ibruteforceData += bruteForce.getSurivers();
				
				
				RandomTraversal randomTraversal = new RandomTraversal();
				randomTraversal.randomTraversal(graph.getHumans(), graph.getNodes(), graph, 4, 4);
				
				
				//System.out.println(randomTraversal.getSurvives());
				//randomData.put(i, randomTraversal.getSurvives());
				
				randomSurvivors += randomTraversal.getSurvives();
				
				
				
				AntSystem antSys = new AntSystem(graph, numberOfAnts);
				
				//antSys.addSetListOfSolutions(solutions);
				
				antSys.run();
				//antSys.printPathsFromNodes();
				//antSys.printHowManySourvives();
				//System.out.println("Avrage number of ants running trhough the graph before getting the same path as BruteForce: "+antSys.getAvrageHowManyAntsBeforeEqualBF());
				//sumAntRuns += antSys.getAvrageHowManyAntsBeforeEqualBF();
				
				
				sumAntSurvivors += antSys.getSurivers();
				
			}
			
			if(numberOfAnts == 20 || numberOfAnts == 40 || numberOfAnts == 60 || numberOfAnts == 80)
			{
				System.out.println(numberOfAnts+"% done.");
			}
			ibruteforceData = ibruteforceData/howManyRoundsToRun;
			randomSurvivors = randomSurvivors/howManyRoundsToRun;
			sumAntSurvivors = sumAntSurvivors/howManyRoundsToRun;
			
			
			randomData.put(numberOfAnts, randomSurvivors);
			bruteForceData.put(numberOfAnts, ibruteforceData);
			antSystemData.put(numberOfAnts, sumAntSurvivors);
		}
		
		
		
		//sumAntRuns = sumAntRuns/howManyRoundsToRun;
		
		
		chartCreator.createLineChartFromSurivers(antSystemData, randomData, bruteForceData, howManyRoundsToRun);
		
		//System.out.println("Avrage number of ants run trough the graph beofre they are as good as bruteforce: "+sumAntRuns);
		//System.out.println("Avrage number of surivers in AntSystem: "+sumAntSurivers);
	}
}
