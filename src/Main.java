import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

public class Main {
	
	private static GenProperities properties = new GenProperities();
	
	public static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args){
		
		//Properties initializing
		
		//Every building has at least 1 floor, sets default floor at 1st level
		int floor = 1;
		
		try {
			setProperities(properties);
		}
		catch (InputMismatchException e) {
			System.out.println("Wrong properities input!");
			
		}
		
		//Elevator initializing
		
		System.out.println("Enter the staring floor");
		
		try {
			floor = in.nextInt();
			//If starting floor doens't match the max height of building
			if (floor > properties.getHeight()) throw new InputMismatchException();
		}
		catch (InputMismatchException e) {
			System.out.println("Wrong floor number input!");
		}
		
		Elevator elevator1 = new Elevator(floor, properties.getHeight(), "Elevator 1");
		Elevator elevator2 = new Elevator(floor, properties.getHeight(), "Elevator 2");
		
		//Elevator cycle
		long start = System.currentTimeMillis();
		while(true) {
			
			long end = System.currentTimeMillis();
			int counter = 1;
			int direction;
			//With chosen span creates random requests to elevator
			if ((end - start) / 1000F >= properties.getSpan()) {
				int[] params = properties.randRequest();
				
				floor = params[0];
				
				if (params[1] % 2 == 0) {
					direction = 1;
				}
				else {
					direction = -1;
				}
				
				System.out.println(String.format("Request recieved. Floor: %o, direction: %s", floor, (direction>0) ? "up" : "down"));
				
				//Split tasks by elevators
				if(elevator1.getRequests().size() > elevator2.getRequests().size()) {
					elevator2.request(floor, direction);
				}
				else {
					elevator1.request(floor, direction);
				}
			}
			//Makes elevator move
			try {
				Thread.sleep(properties.getVelocity() * 1000);
			} catch (InterruptedException e) {
				System.out.println("Elevator velocity error");
			}
			
			try {
				elevator2.move();
				elevator1.move();
			}
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Unexpected error in evelators' work");
			}
		}
		
	}
	
	public static void setProperities(GenProperities properties) throws InputMismatchException {
		
		System.out.println("Enter the height of building");
		
		properties.setHeight(in.nextInt());
		
		System.out.println("Enter the lenght of interval (in seconds)");
		
		properties.setSpan(in.nextDouble());
		
		//The velocity of elevator was not required, but it's actually important
		System.out.println("Enter the time required by elevator to pass one floor (in seconds)");
		properties.setVelocity(in.nextInt());
		
	}

}
