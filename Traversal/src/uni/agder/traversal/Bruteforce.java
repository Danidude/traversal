package uni.agder.traversal;


import java.util.ArrayList;
import java.util.List;

public class Bruteforce {
	private ArrayList<ArrayList<Integer>> listOfSolutions = new ArrayList<ArrayList<Integer>>(); 
	private ArrayList<ArrayList<Integer>> tempListOfSolutions;
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
	//Finds the node we start from to create solutions.
	private Node findStartNode(List<Node> nList, List<Human> hList)
	{

		for (Node n : nList)
		{
			if(n.NodeID == hList.get(0).getCurrentNode())
				return n;
		}

		return null;
	}
	//This goes trough all the solutions and does one step for each of them.(That may spawn many more solutions, as shown in the function under)
	private void bruteForceStep(ArrayList<ArrayList<Integer>> listOfLists)
	{
		for(ArrayList<Integer> l : listOfLists)
		{
			if(l.get(l.size()-1) != -1 && l.get(l.size()-1) != 0)//Only want to do a step of unfinished solutions.
				nextStepInSolution(l);
			else if(l.get(l.size()-1) == 0)//If it is a solution that is solved, it adds it so tempList, so it is kept.
				tempListOfSolutions.add(l);
		}
	}

	//This checks all the paths in the currentNode and creates one solution for each one of them, and then adds them to the tempSolutionsList.	
	private void nextStepInSolution(ArrayList<Integer> solution)
	{
		//First find the currentNode for this specific solution.
		Node currentNode = findCurrentNode(solution);

		//If it bugs out, just exit;
		if (currentNode == null)
			return;

		boolean exsistBefore = false; //Is used to check if the selected route is already in the list.

		for(Node n : currentNode.getPath())
		{
			exsistBefore = checkIfSolutionLoops(n, solution);

			if(!exsistBefore)
			{
				createCloneSolution(solution, n.NodeID);
			}

		}


	}

	//Finds the node that is at the end of the solution and returns it.
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

	//Checks if the node is already in the list, if it is, it return true, else it return false.
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

	//Clones a solution, and then adds it to the tempList.
	private void createCloneSolution(ArrayList<Integer> list, int nextNode)
	{
		ArrayList<Integer> newClone = new ArrayList<Integer>();
		newClone.addAll(list);
		newClone.add(nextNode);
		tempListOfSolutions.add(newClone);
	}

	//Test to see if all the solutions are legal solutions(I.e. that they reached a end-point).
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
	 * Needs to be changed to be more dynamic.
	 **/
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
	//First it goes trough all the solutions to find the one with the shortest path, then puts all with that length into a list it returns.
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

	//Prints out the solution in a nice fashion.
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
