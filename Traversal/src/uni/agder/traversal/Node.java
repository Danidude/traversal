package uni.agder.traversal;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private float chanceOfDeath;
	private List <Branch> listOfBranches = new ArrayList <Branch>();
	
	public float getChanceOfDeath() {
		return chanceOfDeath;
	}
	public void setChanceOfDeath(float chanceOfDeath) {
		this.chanceOfDeath = chanceOfDeath;
	}
}
