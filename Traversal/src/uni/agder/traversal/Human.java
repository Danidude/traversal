package uni.agder.traversal;


enum Age { ADULT, TEENAGER, CHILD, ELDERLY }
enum Gender { MALE, FEMALE }

public class Human {
	
	private Age age;
	private Gender gender;
	
	public Human(Age age, Gender gender) {
		this.age = age;
		this.gender = gender;
	}
}
