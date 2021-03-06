package uni.agder.traversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AntSystem {
	private int numberOfAnts = 100;
	/* Used to calculate the amount of pheromones that evaporate */
	private double evaporation = 0.25;
	/* Used to weigh the importance of the pheromone deposits */
	private double alpha = 0.125;
	/*Used to determine the chosen path*/
	private int atractiveness = 200;
	/*The starting proberbility for each path*/
	private HashMap<Node, ArrayList<Node>> bestPathsForEachNode = new HashMap<Node, ArrayList<Node>>();
	/*Used to contain the best path for each node*/
	private HashMap<Integer, ArrayList<Integer>> listOfBruteForceSolutions = new HashMap<Integer, ArrayList<Integer>>();
	/*Contains all the solutions form bruteforce*/
	private HashMap<Node, Integer> howManyStepsToBeEqualBF = new HashMap<Node, Integer>();
	/*Contains the number of ants that have run through the graph before they find the same route as bruteforce*/
	
	Random rand;
	
	Graph graph;
	
	public AntSystem(Graph graph, int numberOfAnts){
		this.graph = graph;
		rand = new Random();
		this.numberOfAnts = numberOfAnts;
	}
	
	public AntSystem(Graph graph, ArrayList<ArrayList<Integer>> list){
		this.graph = graph;
		rand = new Random();
		
	}
	
	/*
	 * Diminish the amount of pheromones in each node 
	 * based on the evaporation.
	 * */
	public void updatePheromonTrail(){
		for(Node n : graph.getNodes()){
			
			n.setAmountOfPheromones((int)((1-evaporation)*n.getAmountOfPheromones()));
		}
	}
	
	/*
	 * Get the current ant and the current node.
	 * Get the nodes the ant can chose to move to from the 
	 * current node, and only gets nodes it have not viseted before.
	 * Use the pheromone values in each node combined
	 * with alpha to calculate the different probabilities.
	 * Make sure that the probability of all combined
	 * choices equals 1.
	 * */
	public boolean calculateTransitionProbabilities(Ant a){
		int totalPheromones = 0;
		ArrayList<Node> possiblePaths = new ArrayList<Node>();
		
		for(Node n : a.getCurrentNode().getPath())
		{
				totalPheromones += n.getAmountOfPheromones();
				possiblePaths.add(n);
			
		}
		int totalChanceOfPath = atractiveness * possiblePaths.size();
		int totalChance = totalChanceOfPath+totalPheromones;
		
		if(totalChance == 0 || a.getCurrentNode().isExit())
		{
			if(a.getCurrentNode().isExit())
			{
				a.depositPheromones();
			}
				
			return false;
		}
		
		/*Randomly selects a number of the total number of pheromones*/
		int theChosenPathNumber = rand.nextInt(totalChance)+1;
		
		int i = atractiveness;
		for(Node n: possiblePaths)
		{
			if(theChosenPathNumber <= n.getAmountOfPheromones()+i)
			{
				return a.move(n);
			}
			else
				i += atractiveness+n.getAmountOfPheromones();
		}
		System.out.println("Error: calculateTransitionProbabilities did not find path.");
		return false;
	}
	
	public void run()
	{
		for (Node n : graph.getNodes())
		{	
			if(n.hasWayToExit)
			{
				runAnts(n);
			}
			
		}
	}
	
	/*
	 * Runs ants with a given current node.
	 * */
	private void runAnts(Node currentNode)
	{	ArrayList<Node> bestRoute = new ArrayList<Node>();
		int antGroup = 0;
		for (int i = 1; i<numberOfAnts; i++)
		{
			if(antGroup == 20)
			{
				updatePheromonTrail();
				int abc = graph.getNodes().get(8).getAmountOfPheromones();
				antGroup = 0;
			}
			Ant ant = new Ant(currentNode);
			antGroup++;
			boolean antMoving = true;
			if(currentNode.getChanceOfDeath() < 1)
			while(antMoving)
			{
				antMoving = calculateTransitionProbabilities(ant);
			}
			boolean hasChanged = false;
			if((bestRoute.isEmpty() || ant.getVisitedNodes().size() <= bestRoute.size()) && ant.getVisitedNodes().get(ant.getVisitedNodes().size()-1).isExit())
			{
				bestRoute = (ArrayList<Node>) ant.getVisitedNodes();
				hasChanged = true;
			}
			
			
			/*if(bestRoute.size() == listOfBruteForceSolutions.get(ant.getVisitedNodes().get(0).NodeID).size()-1 && hasChanged && bestRoute.size() > 2)
				if(!howManyStepsToBeEqualBF.containsKey(ant.getVisitedNodes().get(0).NodeID))
				{
					howManyStepsToBeEqualBF.put(ant.getVisitedNodes().get(0), i);
					//System.out.println("One added. "+ ant.getVisitedNodes().get(0).NodeID);
				}*/
					
				
			bestPathsForEachNode.put(currentNode, bestRoute);
		}
		
		//Prints the path, needs to be moved to Run.
		
	}
	
	
	public void addSetListOfSolutions(ArrayList<ArrayList<Integer>> list)
	{
		for(ArrayList<Integer> l : list)
		{
			listOfBruteForceSolutions.put(l.get(0), l);
		}
	}
	
	public Integer getAvrageHowManyAntsBeforeEqualBF()
	{
		int total = 0;
		int howMany = 0;
		for(Node n:graph.getNodes())
		{
			if(howManyStepsToBeEqualBF.containsKey(n))
			{
				howMany += howManyStepsToBeEqualBF.get(n);
				total++;
			}
		}
		if(total != 0)
		howMany = howMany/total;
		return howMany;
	}
	
	public void printPathsFromNodes()
	{
		for(Node node : graph.getNodes())
		{
			System.out.print("Best way from node "+node.NodeID+" is: ");
			if(bestPathsForEachNode.get(node).size() < 1)
			{
				System.out.print("There is no way from this node to an exit");
			}
			for(Node n: bestPathsForEachNode.get(node))
			{
				System.out.print(n.NodeID+" ");
			}
			System.out.println("");
		}
	}
	
	public int getSurivers()
	{
		updatePheromonTrail();
		//graph.resetPheremones();
		int deaths = 0;
		int sourvived = 0;
		ArrayList<Human> humansWithOutAPath = new ArrayList<Human>();
		for(Node node : graph.getNodes())
		{
			for(Human human:graph.getHumans())
			{
				
				/*if(human.getStartPosition() == node.NodeID && bestPathsForEachNode.containsKey(node))
				{
					sourvived++;
				}
				else if(!bestPathsForEachNode.containsKey(node) && human.getStartPosition() == node.NodeID)
				{
					humansWithOutAPath.add(human);
				}*/
				
				
				if(human.getStartPosition() == node.NodeID && !bestPathsForEachNode.isEmpty() && bestPathsForEachNode.get(node).size() > 0)
				{
					sourvived++;
				}
				else if(human.getStartPosition() == node.NodeID && !bestPathsForEachNode.containsKey(node))
				{
					humansWithOutAPath.add(human);
				}
				else if(bestPathsForEachNode.isEmpty())
				{
					//humansWithOutAPath.add(human);
				}
			}
		}
		if(!humansWithOutAPath.isEmpty())
			{
				RandomTraversal rt = new RandomTraversal();
				rt.randomTraversal(humansWithOutAPath, graph.getNodes(), graph, 0, 0);
				sourvived += rt.getSurvives();
			}
		
		//System.out.println(sourvived);
		return sourvived;
	}
	
	public void printHowManySourvives()
	{
		int deaths = 0;
		int sourvived = 0;
		for(Node node : graph.getNodes())
		{
			for(Human human:graph.getHumans())
			{
				if(human.getStartPosition() == node.NodeID && bestPathsForEachNode.get(node).size() > 0)
				{
					sourvived++;
				}
				else if(human.getStartPosition() == node.NodeID && bestPathsForEachNode.get(node).size() <= 0)
				{
					deaths++;
				}
			}
		}
		System.out.println("In the Ant System "+deaths+" have died and "+sourvived+" have surived out of "+graph.getHumans().size());
	}

}
