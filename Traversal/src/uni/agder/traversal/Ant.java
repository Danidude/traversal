package uni.agder.traversal;

import java.util.ArrayList;
import java.util.List;

public class Ant {
	private List<Node> visitedNodes;
	private List<Node> updateOnlyOnceOnEachNode;
	private int distanceTraveled;
	private int totalPheromones = 100;
	private Node currentNode;

	public Ant(Node start){
		distanceTraveled = 0;
		updateOnlyOnceOnEachNode = new ArrayList<Node>();
		visitedNodes = new ArrayList<Node>();
		visitedNodes.add(start);
	}

	public void die(){
		
	}

	public Node getCurrentNode(){
		currentNode = visitedNodes.get(visitedNodes.size() - 1);
		return visitedNodes.get(visitedNodes.size() - 1);
	}

	public int getDistanceTraveled() {
		return distanceTraveled;
	}

	public void depositPheromones(){
		int partialPheromones = totalPheromones/visitedNodes.size();
		for(Node n : updateOnlyOnceOnEachNode){
			n.increasPheromones(partialPheromones);
		}
	}

	public boolean move(Node node){
		if(getCurrentNode().isExit()){
			depositPheromones();
			return false;
		}
		else if(node.getChanceOfDeath() > 0)
		{
			if(!updateOnlyOnceOnEachNode.contains(node))
			{
				updateOnlyOnceOnEachNode.add(node);
			}
			visitedNodes.add(node);
			distanceTraveled++;
			return false;
		}
		else{
			if(!updateOnlyOnceOnEachNode.contains(node))
			{
				updateOnlyOnceOnEachNode.add(node);
			}
			visitedNodes.add(node);
			distanceTraveled++;
			return true;
		}
	}
	public List<Node> getVisitedNodes()
	{
		return visitedNodes;
	}
}
