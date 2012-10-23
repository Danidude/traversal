package uni.agder.traversal;


enum Age { ADULT, TEENAGER, CHILD, ELDERLY }
enum Gender { MALE, FEMALE }

public class Human {
	
	private Age age;
	private Gender gender;
	private int currentNode;
	
	public Human(Age age, Gender gender) {
		this.age = age;
		this.gender = gender;
	}
	
	public void setCurrentNode(int i)
	{
		currentNode = i;
	}
	
	public int getCurrentNode()
	{
		return currentNode;
	}
}
