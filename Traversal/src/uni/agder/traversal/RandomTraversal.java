package uni.agder.traversal;

import java.util.List;
import java.util.Random;

public class RandomTraversal {
	/*
	 * Get the graph
	 * Get a list of all humans
	 * For each human: select a random path until you either reach the exit or die
	 * Print amount of survivors and total amount of humans at start
	 */

	Random random = new Random();

	public void randomTraversal(List<Human> listOfHumans, List<Node> listOfNodes, Graph graph, int exits, int lethalNodes){
		graph.placeAllHumans();
		graph.printHumans();
		// TODO: This can cause the same node to be assigned as an exit multiple times, fix it
		for(int i = 0; i < exits; i++){
			listOfNodes.get(random.nextInt(listOfNodes.size())).setExit(true);
		}
		// TODO: Do this properly, same problem as above
		for(int i = 0; i < lethalNodes; i++){
			listOfNodes.get(random.nextInt(listOfNodes.size())).setChanceOfDeath(1);
		}
		
		for(Node n : listOfNodes){
			if(n.getChanceOfDeath()==1){
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
