import java.util.Random;

public class GenProperities {
	
	private int height; // Height of the building, holds number of floors
	private double span; // Interval between floors
	private int velocity; // Amount of seconds required to pass 1 floor
	final Random random = new Random();
	
	public GenProperities() {
		//Void constructor
	}
	
	public GenProperities(int height, double span, int velocity) {
		this.height = height;
		this.span = span;
		this.velocity = velocity;
	}

	//Returns random request from [1, height] floor
	public int randRequest() {
		return random.nextInt(this.height - 1) + 1;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getHeight() {
		return height;
	}

	public double getSpan() {
		return span;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setSpan(double span) {
		this.span = span;
	}
	
	

}
