package osProject3;

public class Box {
	boolean cheese;
	
	public Box() { // box constructor
	}
	
	public Box(boolean cheese) { // box constructor that allows you to set the status of cheese in the box.
		this.cheese = cheese;
	}
	
	public boolean isCheese() { // isCheese() method that returns true if the box contains cheese.
		if (this.cheese == true) {
			return true;
		}
		else {
			return false;
		}
	}
}
