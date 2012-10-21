package uni.agder.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {

	private List<Human> listOfHumans = new ArrayList<Human>();
	private List<Node> listOfNodes = new ArrayList<Node>();
	private int nextNodeID = 1;

	/*
	 * Step one: Create any number of nodes, for instance by using a random
	 * function with a minimum and maximum (rand.nextInt(max - min + 1) + min).
	 * Step two: Create branches by adding paths for each node (remember
	 * that this is a graph and if node A has node B as a path B also
	 * needs node A as a path).
	 * Step three: Find all possible paths by traversing the graph
	 * Step four: Count steps for all possible solutions and print best path
	 */
	public void generateGraph() {
		firstTestList();
	}		
	
	private Node createNode()
	{
		Node newNode = new Node(nextNodeID++);
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
		
	}
	
	public List getNodes()
	{
		return listOfNodes;
	}

}



