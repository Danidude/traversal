package uni.agder.traversal;


enum Age { ADULT, TEENAGER, CHILD, ELDERLY }
enum Gender { MALE, FEMALE }

public class Human {
	
	private Age age;
	private Gender gender;
	private int atNode;
	
	public Human(Age age, Gender gender) {
		this.age = age;
		this.gender = gender;
	}
	
	public void setAtNode(int i)
	{
		atNode = i;
	}
	
	public int getAtNode()
	{
		return atNode;
	}
}
