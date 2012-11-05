package uni.agder.traversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AntSystem {
	private int numberOfAnts = 1000;
	/* Used to calculate the amount of pheromones that evaporate */
	private double evaporation = 0.25;
	/* Used to weigh the importance of the pheromone deposits */
	private double alpha = 0.125;
	/*Is a map over all the nodes and their pathData*/
	private Map<Node, AntNodeData> allNodesData;
	/*List of all the nodes in the graph*/
	private ArrayList<Node> listOfNodes;
	Graph graph;
	
	public AntSystem(Graph graph){
		this.graph = graph;
		listOfNodes = new ArrayList<Node>();
		listOfNodes = (ArrayList<Node>) graph.getNodes();
		
		allNodesData = new HashMap<Node, AntNodeData>();
		
		for(Node n: listOfNodes)
		{
			AntNodeData nodeData = new AntNodeData(n);
			allNodesData.put(n, nodeData);
		}
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
	
	/*
	 * Contains the proberbility for each path on an node.
	 */
	private class AntNodeData
	{
		private int nodeID;
		private Map<Node, Float> proberbiletyOfPath;
		
		public AntNodeData(Node n)
		{
			nodeID = n.NodeID;
			proberbiletyOfPath = new HashMap<Node, Float>();
			inizilizePathProb(n);
		}
		
		private void inizilizePathProb(Node n)
		{
			float startProb = 1/n.getPath().size();
			for(Node n2: n.getPath())
			{
				proberbiletyOfPath.put(n2, startProb);
			}
		}
	}
}
