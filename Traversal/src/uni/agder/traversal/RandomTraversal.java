package uni.agder.traversal;

import java.util.List;
import java.util.Random;

public class RandomTraversal {
	
	Random random = new Random();
	
	/*
	 * First takes all the humans in the list of humans and places them in random positions in the 
	 * graph. Multiple people can be assigned to the same node. Next it assigns exit nodes, if a 
	 * node is already an exit node another node will be assigned. Then it does the same for the
	 * nodes chance of death. A node cannot both be an exit node and a lethal node. Then for each
	 * human, they will will get the node they are currently standing in, then pick a random path 
	 * out of the node and continue doing so until they either die or reach the exit. 
	 */
	public void randomTraversal(List<Human> listOfHumans, List<Node> listOfNodes, Graph graph, int exits, int lethalNodes){
		graph.placeAllHumans();
		graph.printHumans();
		
		for(Node n : listOfNodes){
			if(n.getChanceOfDeath() != 0){
				System.out.println("Node " + n.NodeID + " is lethal");
			}
			if(n.isExit()){
				System.out.println("Node " + n.NodeID + " is an exit");
			}
		}
		
		for(Human h : listOfHumans){
			while(listOfNodes.get(h.getCurrentNode()).getChanceOfDeath() == 0){
				if(listOfNodes.get(h.getCurrentNode()).isExit()){
					System.out.println("A human made it out alive");
					break;
				}
				int choice = random.nextInt(listOfNodes.get(h.getCurrentNode()).getPath().size());
				System.out.print("Human moves from: " + h.getCurrentNode() + " ");
				int path = listOfNodes.get(h.getCurrentNode()).getPath().get(choice).NodeID;
				h.setCurrentNode(path);
				System.out.println("to: " + h.getCurrentNode() + " ");
				if(listOfNodes.get(h.getCurrentNode()).getChanceOfDeath() == 1){
					System.out.println("This fool is dead");
					break;
				}			
			}
		}
	}
}
