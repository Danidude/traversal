package uni.agder.traversal;

import java.util.ArrayList;
import java.util.List;

public class Ant {
	private List<Node> visitedNodes;
	private int distanceTraveled;
	private int totalPheromones = 100;
	private Node currentNode;

	public Ant(Node start){
		distanceTraveled = 0;
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
		for(Node n : visitedNodes){
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
			visitedNodes.add(node);
			distanceTraveled++;
			return false;
		}
		else{
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
