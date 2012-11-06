package uni.agder.traversal;

import java.util.Random;

public class AntSystem {
	private int numberOfAnts = 1000;
	/* Used to calculate the amount of pheromones that evaporate */
	private double evaporation = 0.25;
	/* Used to weigh the importance of the pheromone deposits */
	private double alpha = 0.125;
	/*Used to determine the chosen path*/
	private int atractiveness = 200;
	/*The starting proberbility for each path*/
	Random rand;
	
	Graph graph;
	
	public AntSystem(Graph graph){
		this.graph = graph;
		rand = new Random();
	}
	
	/*
	 * Diminish the amount of pheromones in each node 
	 * based on the evaporation.
	 * */
	public void updatePheromonTrail(){
		for(Node n : graph.getNodes()){
			
			n.increasPheromones((int)((1-evaporation)*n.getAmountOfPheromones()));
		}
	}
	
	/*
	 * Get the current ant and the current node.
	 * Get the nodes the ant can chose to move to from the 
	 * current node.
	 * Use the pheromone values in each node combined
	 * with alpha to calculate the different probabilities.
	 * Make sure that the probability of all combined
	 * choices equals 1.
	 * */
	public boolean calculateTransitionProbabilities(Ant a){
		int totalPheromones = 0;
		int totalChanceOfPath = atractiveness * a.getCurrentNode().getPath().size();
		for(Node n : a.getCurrentNode().getPath())
		{
			totalPheromones += n.getAmountOfPheromones();
		}
		int totalChance = totalChanceOfPath+totalPheromones;
		
		/*Randomly selects a number of the total number of pheromones*/
		int theChosenPathNumber = rand.nextInt(totalChance)+1;
		
		int i = atractiveness;
		for(Node n: a.getCurrentNode().getPath())
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
			if(!n.isExit())
			runAnts(n);
		}
	}
	
	/*
	 * Runs ants with a given current node.
	 * */
	private void runAnts(Node currentNode)
	{
		int antGroup = 0;
		for (int i = 0; i<numberOfAnts; i++)
		{
			if(antGroup == 20)
			{
				updatePheromonTrail();
			}
			Ant ant = new Ant(currentNode);
			antGroup++;
			boolean antMoving = true;
			while(antMoving)
			{
				antMoving = calculateTransitionProbabilities(ant);
			}
			System.out.println("Done");
				
		}
	}

}
