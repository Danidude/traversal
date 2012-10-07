package uni.agder.traversal;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private float chanceOfDeath;
	private List <Node> listOfChildren = new ArrayList <Node>();
	
	public float getChanceOfDeath() {
		return chanceOfDeath;
	}
	public void setChanceOfDeath(float chanceOfDeath) {
		this.chanceOfDeath = chanceOfDeath;
	}
}
