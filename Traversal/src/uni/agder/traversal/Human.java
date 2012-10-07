package uni.agder.traversal;


enum Age { Adult, Teenager, Child, Elderly }
enum Gender { Male, Female }
public class Human {
	
	private Age age;
	private Gender gender;
	
	public Human(Age age, Gender gender) {
		this.age = age;
		this.gender = gender;
	}
}
