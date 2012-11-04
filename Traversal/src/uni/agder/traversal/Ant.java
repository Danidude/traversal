package uni.agder.traversal;

import java.util.ArrayList;
import java.util.List;

public class Ant {
	private List<Node> visitedNodes;
	private int distanceTraveled;
	private int totalPheromones = 100;

	public Ant(Node start){
		distanceTraveled = 0;
		visitedNodes = new ArrayList<Node>();
		visitedNodes.add(start);
	}

	public void die(){

	}

	public Node getCurrentNode(){
		return visitedNodes.get(visitedNodes.size() - 1);
	}

	public int getDistanceTraveled() {
		return distanceTraveled;
	}

	public void depositPheromones(){
		int partialPheromones = totalPheromones/visitedNodes.size();
		for(Node n : visitedNodes){
			n.setAmountOfPheromones(partialPheromones);
		}
	}

	public void move(Node node){
		if(getCurrentNode().isExit()){
			depositPheromones();
		}
		else{
			visitedNodes.add(node);
			totalPheromones++;
		}
	}
}
