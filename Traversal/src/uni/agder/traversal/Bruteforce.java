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
	
	public ArrayList<ArrayList<Integer>> bruteForceGraph(Graph graph)
	{
		
		listOfNodes = graph.getNodes();
		
		startNode = findStartNode(listOfNodes);
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
	
	private Node findStartNode(List<Node> list)
	{
		
		for (Node n : list)
		{
			if(n.NodeID == 1)
				return n;
		}
		
		return null;
	}
	
	private void bruteForceStep(ArrayList<ArrayList<Integer>> listOfLists)
	{
		for(ArrayList<Integer> l : listOfLists)
		{
			if(l.get(l.size()-1) != -1 && l.get(l.size()-1) != 0)
				nextStep(l);
			else if(l.get(l.size()-1) == 0)
				tempListOfSolutions.add(l);
		}
	}
	
	private void nextStep(ArrayList<Integer> solution)
	{
		Node currentNode = null;
		for(Node n : listOfNodes)
		{
			if(n.NodeID == solution.get(solution.size()-1))
			{
				currentNode = n;
			}
		}
		if (currentNode == null)
			return;
		
		boolean exsistBefore = false; //Is used to check if the selected route is already in the list.
		//boolean lastToChange = true; //No need to create a new list for the last of the paths, just use the current one.
		int listCounter = 0;
		for(Node n : currentNode.getPath())
		{
			listCounter++;
			exsistBefore = false;
			for(int i : solution)
			{
				if(n.NodeID == i)
				{
					exsistBefore = true;
				}
			}
			ArrayList<Integer> newSolution = createCloneSolution(solution);
			if(exsistBefore == true)
			{
				newSolution.add(-1);
			}
			else
			{
				newSolution.add(n.NodeID);
			}
			tempListOfSolutions.add(newSolution);
		}
		
		
	}
	
	private ArrayList<Integer> createCloneSolution(ArrayList<Integer> list)
	{
		ArrayList<Integer> newClone = new ArrayList<Integer>();
		newClone.addAll(list);
		return newClone;
	}
	
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
