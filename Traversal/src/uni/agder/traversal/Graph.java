package uni.agder.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {

	private List<Human> listOfHumans = new ArrayList<Human>();
	private List<Node> listOfNodes = new ArrayList<Node>();
	private int nodeID = 0;
	Random random = new Random();

	/*
	 * Step one: Create any number of nodes
	 * Step two: Create branches by adding paths for each node (remember
	 * that this is a graph and if node A has node B as a path B also
	 * needs node A as a path).
	 */
	
	private Node createNode(){
		Node newNode = new Node(nodeID++);
		return newNode;
	}
	
	// Used to create specific graphs for testing purposes
	@SuppressWarnings("unused")
	private void generateTestCaseGraph(){
		for(int i = 0; i < 7; i++){
			listOfNodes.add(createNode());
		}
	
		listOfNodes.get(0).addPath(listOfNodes.get(3));
		listOfNodes.get(1).addPath(listOfNodes.get(4));
		listOfNodes.get(2).addPath(listOfNodes.get(5));
		
		listOfNodes.get(3).addPath(listOfNodes.get(4));
		listOfNodes.get(4).addPath(listOfNodes.get(5));
		
		listOfNodes.get(3).addPath(listOfNodes.get(6));
		listOfNodes.get(4).addPath(listOfNodes.get(6));
		listOfNodes.get(5).addPath(listOfNodes.get(6));
						
		listOfNodes.get(3).setChanceOfDeath(1);
		listOfNodes.get(4).setChanceOfDeath(1);
		
	}
	
	public void generateRandomGraph(int nodes, int branches){
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
		for(Human h : listOfHumans){
			h.setCurrentNode(random.nextInt(listOfNodes.size()));
		}
	}
	
	public void printHumans(){
		for(Human h : listOfHumans){
			System.out.println("There is a human at: " + h.getCurrentNode());
		}
	}
	
	public void createExits(int numberOfExits){
		int counter = 1;
		while(counter <= numberOfExits){
			int luckyOne = random.nextInt(listOfNodes.size());
			if(!listOfNodes.get(luckyOne).isExit()){
				listOfNodes.get(luckyOne).setExit(true);
				counter++;
			}
		}
	}
	
	public void createLeathalNodes(int numberOfLeathalNodes){
		int counter = 1;
		while(counter <= numberOfLeathalNodes){
			int lethal = random.nextInt(listOfNodes.size());
			if(!listOfNodes.get(lethal).isExit() && listOfNodes.get(lethal).getChanceOfDeath() == 0){
				listOfNodes.get(lethal).setChanceOfDeath(1);
				counter++;
			}
		}
	}
	
	public void printSpecialNodes(){
		for(Node n : listOfNodes){
			if(n.getChanceOfDeath() != 0){
				System.out.println("Node " + n.NodeID + " is lethal");
			}
			if(n.isExit()){
				System.out.println("Node " + n.NodeID + " is an exit");
			}
		}
	}
	
	public void printGraph(){
		for (Node n : listOfNodes){
			n.printPath();
		}
	}
}



