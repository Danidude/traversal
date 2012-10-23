package uni.agder.traversal;


import java.util.ArrayList;
import java.util.List;
/*
 * This class will bruteforce a Created graph, and return the best path from a given stating node
 * to the end point. At the moment is the start point, human 0 in the given graph, and endpoint is the node with ID = 8.
 * 
 * Step 1: 	Creates a list of all the given nodes in the graph.
 * 
 * Step 2: 	Finds the start node from the given human.
 * 
 * Steo 3: 	Creates a list of integers, the lists are the paths of possible solutions, and adds this to a list of possible solutions.
 * 
 * Step 4: 	It now enters a loop, where it is only finished when all the possible solutions in the list of possible solutions are valid
 * 			solutions.
 * 
 * Step 5:	In the loop it creates a temporary list for the new solutions to be created from the not yet finished solutions.
 * 
 * Step 6:	Runs the burteforce function on the list of possible solutions. This function does this:
 * 				Checks if the last entry of the list is a -1 or 0, if not it does the next step for this possible solution(Moves
 * 				the current node ahead and creates new solutions for each path it may take.)
 * 				If the list ends with a 0, it saves it to the temporarily list so it is not deleted. The ones with -1 in the end, are
 * 				discarded.
 * 
 * Step 7:	In the nextstep function, it fiends the currentnode, witch is the last integer in the list. Then it finds that node with that ID
 * 			and creates new solutions from the paths from the node. Each new solutions have a new ID added, with is equal to the path nodeID
 * 			they where created from. (If the paths takes the possible solution to a node it have already visited, it does not create a new
 * 			solution, just jumps over this then. This is to avoid infinite loops.)
 * 
 * Step 8:	Now we delete all the old solutions in the list of possible solutions, then takes all the possible solutions from the
 * 			temporally list of solutions and add them to the list of possible solutions.
 * 
 * Step 9:	Checks if all the solutions created ends with 0, if it does the loop ends, if not it starts over from step 4.
 * 
 * Step 10:	Now it finds the shortest path and returns a list with all the solutions with the shortest path.
 */
public class Bruteforce {
	private ArrayList<ArrayList<Integer>> listOfSolutions = new ArrayList<ArrayList<Integer>>(); //Contains the current solutions.
	private ArrayList<ArrayList<Integer>> tempListOfSolutions; //Hold on the next solutions until it is trasnferd over to the solution list.
	private ArrayList<Integer> solution;
	private List<Node> listOfNodes = new ArrayList<Node>();
	private Node startNode;
	private boolean isFinished;
	
	//Main function.
	public ArrayList<ArrayList<Integer>> bruteForceGraph(Graph graph)
	{
		
		listOfNodes = graph.getNodes();
		
		startNode = findStartNode(listOfNodes, graph.getHumans());
		if(startNode == null)
			return null;
		solution = new ArrayList<Integer>();
		solution.add(startNode.NodeID);
		listOfSolutions.add(solution);
		isFinished = false;
		
		while (!isFinished)
		{
			tempListOfSolutions = new ArrayList<ArrayList<Integer>>();
			bruteForceStep(listOfSolutions);
			listOfSolutions.clear();
			listOfSolutions.addAll(tempListOfSolutions);
			isFinished = isDone(listOfSolutions);
			isAtEnd(listOfSolutions);
			
		}
		
		listOfSolutions = findBestSolutions(listOfSolutions);
		return listOfSolutions;
		
		
		
	}
	/*
	 * Finds the node we want to start the bruteforce from.
	 * Current it finds what node the human 0 stands in.
	 * TO DO: Move this function into Graph, takes a human and returns the node that human stands in.
	 */
	private Node findStartNode(List<Node> nlist, List<Human> hlist)
	{
		for (Node n : nlist)
		{
			if(n.NodeID == hlist.get(0).getAtNode())
				return n;
		}
		
		return null;
	}
	/*
	 * This goes trough all the solutions, checks if they ends with 0 or -1.
	 * If not: Then it takes that list and runs nextStepInSolution.
	 * If 0: Saves that solution to tempListOfSolutions for save keeping. (This is one of the valid solutions.)
	 * If -1: The list is discarded.
	 */
	private void bruteForceStep(ArrayList<ArrayList<Integer>> listOfLists)
	{
		for(ArrayList<Integer> l : listOfLists)
		{
			//Only want to do a step of unfinished solutions.
			if(l.get(l.size()-1) != -1 && l.get(l.size()-1) != 0)
				nextStepInSolution(l);
			//If it is a solution that is solved, it adds it so tempList, so it is kept.
			else if(l.get(l.size()-1) == 0)
				tempListOfSolutions.add(l);
		}
	}
	
	/*
	 * This functions takes a list of integers(NodeID's), finds the last entery(CurrentNode) and then finds all the paths this node leades
	 * too. Once this is done, it checks each paths nodeID to see if the solution have been to this node before, if not, it creats a new
	 * solution from the old one and adds the paths nodeID to the solution. If it have been to this node before, ot dose nothing and goes
	 * to the next path.
	 */
	private void nextStepInSolution(ArrayList<Integer> solution)
	{
		//First find the currentNode for this specific solution.
		Node currentNode = findCurrentNode(solution);
		
		//If it bugs out, just exit. (Just a safety measure.)
		if (currentNode == null)
			return;
		//Is used to check if the selected route is already in the list
		boolean exsistBefore = false;
		
		for(Node n : currentNode.getPath())
		{
			exsistBefore = checkIfSolutionLoops(n, solution);
			
			if(!exsistBefore)
			{
				createCloneSolution(solution, n.NodeID);
			}
			
		}
		
		
	}
	
	/*
	 * It takes a solution list, and searches for the node with the ID same as the last Integer as the last entery in the solution.
	 */
	private Node findCurrentNode(ArrayList<Integer> solution)
	{
		for(Node n : listOfNodes)
		{
			if(n.NodeID == solution.get(solution.size()-1))
			{
				return n;
			}
		}
		return null;
	}
	
	/*
	 * Checks if the node is already in the list, if it is, it return true, else it return false.
	 */
	private boolean checkIfSolutionLoops(Node n, ArrayList<Integer> l)
	{
		for(int i : l)
			{
				if(n.NodeID == i)
				{
					return true;
				}
			}
		return false;
	}
	
	/*
	 * Clones a solution, then adds the given intiger to the list as the last entry. Then it saves it to tempListOfSolutions.
	 */
	private void createCloneSolution(ArrayList<Integer> list, int nextNode)
	{
		ArrayList<Integer> newClone = new ArrayList<Integer>();
		newClone.addAll(list);
		newClone.add(nextNode);
		tempListOfSolutions.add(newClone);
	}
	
	/*
	 * Test to see if all the solutions are legal solutions. A legal or vial solution is a solution that ends with a 0.
	 * returns false if there is one or more solutions that do not have 0 as their last entry.
	 * else it return true.
	 */
	private boolean isDone(ArrayList<ArrayList<Integer>> list)
	{
		for(ArrayList<Integer> l : list)
		{
			if(l.get(l.size()-1) != 0)
			{
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * Checks if the solution have reached node 8, witch is the end node in our test scenario.
	 * TO DO: Needs to change so that more nodes may be end-points, and not only node with ID = 8.
	 * Maybe create a bool in the nodes that is true if it is a legal end-point.
	 */
	private void isAtEnd(ArrayList<ArrayList<Integer>> list)
	{
		for(ArrayList<Integer> l : list)
		{
			if(l.get(l.size()-1) == 8)
			{
				l.add(0);
			}
		}
	}
	
	/*
	 * Goes trough the list, and finds the shortest list. Then it finds all the lists with this lengths and returns
	 * all those lists.
	 */
	private ArrayList<ArrayList<Integer>> findBestSolutions(ArrayList<ArrayList<Integer>> list)
	{
		ArrayList<ArrayList<Integer>> theBest = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> prev = list.get(0);
		for(ArrayList<Integer> l : list)
		{
			if(prev.size() > l.size())
			{
				prev = l;
			}
		}
		for(ArrayList<Integer> l : list)
		{
			if(prev.size() == l.size())
			{
				theBest.add(l);
			}
		}
		
		return theBest;
	}
	
	/*
	 * Takes a list with list of integers, then proceeds to print them out all the integers, list by list.
	 */
	public void printSolutions (ArrayList<ArrayList<Integer>> list)
	{
		int number = 1;
		for(ArrayList<Integer> l : list)
		{
			System.out.print("Solution number "+number+": ");
			number++;
			for(int i : l)
			{
				System.out.print(" "+i);
			}
			System.out.println(".");
		}
		
	}
}
