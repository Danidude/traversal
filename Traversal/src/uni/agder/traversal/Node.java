package uni.agder.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Node {

	private enum NodeType { STAIRS, BATHROOM, HALLWAY, CAR_DECK }
	private static final RandomEnum<NodeType> randomEnum =
			new RandomEnum<NodeType>(NodeType.class);
	private float chanceOfDeath;
	private int capacity;
	private NodeType nodeType;
	private List<Node> listOfPaths;
	public int NodeID;	
	private boolean isExit;

	public Node(int ID){
		this.NodeID = ID;
		this.nodeType = randomEnum.random();
		this.isExit = false;
		chanceOfDeath = 0;
		
	}

	public boolean isExit() {
		return isExit;
	}

	public void setExit(boolean isExit) {
		this.isExit = isExit;
	}

	public int getSize(){
		if(this.listOfPaths == null){
			return 0;
		}
		else return listOfPaths.size();
	}

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

	public List<Node> getPath(){
		if (this.listOfPaths == null) {return new ArrayList<Node> (); }
		else return this.listOfPaths;
	}

	public void setPath(List<Node> listOfChildren){
		this.listOfPaths = listOfChildren;
	}

	public void addPath(Node node){
		if(this.listOfPaths==null){listOfPaths = new ArrayList<Node> (); }
		boolean isInList = false;
		for (Node n : listOfPaths){
			if (n.NodeID == node.NodeID){
				isInList=true;
			}
		}
		if (isInList == false && node.NodeID != this.NodeID){
			this.listOfPaths.add(node);
			node.addPath(this);
		}	
	}	

	private static class RandomEnum<E extends Enum<NodeType>> {
		private static final Random randomGenerator = new Random();
		private final E[] values;

		public RandomEnum(Class<E> token){
			values = token.getEnumConstants();
		}

		public E random() {
			return values[randomGenerator.nextInt(values.length)];
		}
	}

	public void printPath(){
		System.out.print("Node "+NodeID+" that is a "+nodeType+" have access to ");
		if(listOfPaths.size() != 0){
			for (Node n : listOfPaths){
				System.out.print(n.NodeID+" ");
			}
		}
		System.out.println(" ");
	}
}
