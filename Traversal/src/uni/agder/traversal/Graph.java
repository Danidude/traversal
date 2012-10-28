package uni.agder.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {

	private List<Human> listOfHumans = new ArrayList<Human>();
	private List<Node> listOfNodes = new ArrayList<Node>();
	private int nodeID = 0;

	/*
	 * Step one: Create any number of nodes, for instance by using a random
	 * function with a minimum and maximum (rand.nextInt(max - min + 1) + min).
	 * Step two: Create branches by adding paths for each node (remember
	 * that this is a graph and if node A has node B as a path B also
	 * needs node A as a path).
	 * Step three: Find all possible paths by traversing the graph
	 * Step four: Count steps for all possible solutions and print best path
	 */
	public void generateFixedGraph() {
		firstTestList();
		for (Node n : listOfNodes)
		{
			n.printPath();
		}
	}
	
	public void generateRandomGraph(int nodes, int branches){
		randomGraph(nodes, branches);
		for (Node n : listOfNodes){
			n.printPath();
		}
	}
	
	private Node createNode()
	{
		Node newNode = new Node(nodeID++);
		return newNode;
	}
	
	//Just to create a test list
	private void firstTestList()
	{
		for(int i = 0; i < 8; i++)
		{
			listOfNodes.add(createNode());
		}
	
		listOfNodes.get(0).addPath(listOfNodes.get(1));
		listOfNodes.get(0).addPath(listOfNodes.get(3));
		listOfNodes.get(0).addPath(listOfNodes.get(4));
		
		listOfNodes.get(2).addPath(listOfNodes.get(1));
		listOfNodes.get(2).addPath(listOfNodes.get(3));
		listOfNodes.get(2).addPath(listOfNodes.get(6));
		
		listOfNodes.get(5).addPath(listOfNodes.get(4));
		listOfNodes.get(5).addPath(listOfNodes.get(6));
		listOfNodes.get(5).addPath(listOfNodes.get(7));
		
		
		listOfNodes.get(3).setChanceOfDeath(1);
		listOfNodes.get(4).setChanceOfDeath(1);
		
		//System.out.println(listOfNodes.size());
		
		for (Node n : listOfNodes)
		{
			n.printPath();
			//System.out.println(listOfNodes.size());
		}
		
		Human human1 = new Human();
		human1.setCurrentNode(1);
		listOfHumans.add(human1);	
	}
	
	/*
	 * Does not actually create n branches but rather ~n. A feature-bug likely created
	 * by random.nextInt(x) as this is from 0 (inclusive) to x (exclusive)
	 */
	public void randomGraph(int nodes, int branches){
		Random random = new Random();
		for(int i = 0; i < nodes; i++){
			listOfNodes.add(createNode());
		}
		for(Node n: listOfNodes){
			int k = n.getSize();
			for(int j = k; j < branches; j++){
				n.addPath(listOfNodes.get(random.nextInt(listOfNodes.size())));
			}			
		}		
	}
	
	public List<Node> getNodes(){
		return listOfNodes;
	}
	
	public List<Human> getHumans(){
		return listOfHumans;
	}
	
	public void addHuman(Human human){
		listOfHumans.add(human);
	}
	
	public void generateRandomHumans(int number){
		for(int i = 0; i < number; i++){
			Human human = new Human();
			listOfHumans.add(human);
		}
	}
	
	public void placeAllHumans(){
		Random randomNode = new Random();
		for(Human h : listOfHumans){
			h.setCurrentNode(randomNode.nextInt(listOfNodes.size()));
		}
	}
	
	public void printHumans(){
		for(Human h : listOfHumans){
			System.out.println("There is a human at: " + h.getCurrentNode());
		}
	}
}



