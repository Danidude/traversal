package uni.agder.traversal;

public class AntSystem {
	private int numberOfAnts = 1000;
	/* Used to calculate the amount of pheromones that evaporate */
	private double evaporation = 0.25;
	/* Used to weigh the importance of the pheromone deposits */
	private double alpha = 0.125;
	Graph graph;
	
	public AntSystem(Graph graph){
		this.graph = graph;
	}
	
	/*
	 * Diminish the amount of pheromones in each node 
	 * based on the evaporation.
	 * */
	public void updatePheromonTrail(){
		
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
	public void calculateTransitionProbabilities(){
		
	}
}
