package uni.agder.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tree {
	
	private Node rootNode;
	private List<Human> listOfHumans = new ArrayList<Human>();
	private int maximumAmountOfChildren = 5;
	private int treeDepth = 5;
	Random randomGenerator = new Random();
	
	public void generateTree() {
			rootNode = new Node();
	}
	public static void main(String [ ] args)
	{
		Tree tree = new Tree();
		tree.generateTree();
	}
}


