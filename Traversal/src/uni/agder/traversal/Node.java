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

	public void addChildren(int number){
		if(this.listOfChildren==null){ new ArrayList<Node> (); }
		for (int start = 0; start < number; start++){
			Node node = new Node();
			this.listOfChildren.add(node);
		}		
	}	

	public Node(){
		this.nodeType = randomEnum.random();
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
}
