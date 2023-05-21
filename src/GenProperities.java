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

	/*Returns random request from [1, height] floor 
	 */
	public int[] randRequest() {
		
		int[] params = new int[2];
		
		params[0] = random.nextInt(this.height - 1) + 1;
		params[1] = random.nextInt(this.height); //Random number for direction
				
		return params;
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
