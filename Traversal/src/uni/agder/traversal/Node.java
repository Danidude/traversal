package uni.agder.traversal;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private float chanceOfDeath;
	private int capacity;
	private List<Node> listOfChildren;
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}	
	public float getChanceOfDeath() {
		return chanceOfDeath;
	}
	public void setChanceOfDeath(float chanceOfDeath) {
		this.chanceOfDeath = chanceOfDeath;
	}
	
	public List<Node> getChildren(){
		if (this.listOfChildren == null) {return new ArrayList<Node> (); }
		else return this.listOfChildren;
	}
	
	public void setChildren(List<Node> listOfChildren){
		this.listOfChildren = listOfChildren;
	}
	
	public void addChild(Node node){
		if(this.listOfChildren==null){ new ArrayList<Node> (); }
		this.listOfChildren.add(node);
	}	
	public Node(){
		
	}
}
