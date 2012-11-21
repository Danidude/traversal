package uni.agder.traversal;

import java.util.Random;

public class Human {
	
	private enum Age { ADULT, TEENAGER, CHILD, ELDERLY }
	private enum Gender { MALE, FEMALE }
	private static final RandomAgeEnum<Age> randomAgeEnum =
			new RandomAgeEnum<Age>(Age.class);
	private static final RandomGenderEnum<Gender> randomGenderEnum =
			new RandomGenderEnum<Gender>(Gender.class);
	private Age age;
	private Gender gender;
	private int currentNode;
	private int startNode;
	public boolean hasPath;
	
	public Human(){
		this.age = randomAgeEnum.random();
		this.gender = randomGenderEnum.random();
		hasPath = false;
	}
	
	public void setStartPosition(int i)
	{
		currentNode = i;
		startNode = i;
	}
	
	public int getStartPosition()
	{
		return startNode;
	}
	public void setCurrentNode(int i){
		currentNode = i;
	}
	
	public int getCurrentNode(){
		return currentNode;
	}
	
	private static class RandomAgeEnum<E extends Enum<Age>> {
		private static final Random randomGenerator = new Random();
		private final E[] values;

		public RandomAgeEnum(Class<E> token){
			values = token.getEnumConstants();
		}

		public E random() {
			return values[randomGenerator.nextInt(values.length)];
		}
	}
	
	private static class RandomGenderEnum<E extends Enum<Gender>> {
		private static final Random randomGenerator = new Random();
		private final E[] values;

		public RandomGenderEnum(Class<E> token){
			values = token.getEnumConstants();
		}

		public E random() {
			return values[randomGenerator.nextInt(values.length)];
		}
	}
}

