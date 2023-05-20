import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

public class Main {
	
	private static GenProperities properties = new GenProperities();
	
	public static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args){
		
		//Properties initializing
		
		setProperities(properties);
		
		//Elevator initializing
		
		System.out.println("Enter the staring floor");
		
		int floor = in.nextInt();
		
		Elevator elevator1 = new Elevator(floor, properties.getHeight(), "Elevator 1");
		Elevator elevator2 = new Elevator(floor, properties.getHeight(), "Elevator 2");
		
		//Elevator cycle
		long start = System.currentTimeMillis();
		while(true) {
			
			long end = System.currentTimeMillis();
			int counter = 1;
			//With chosen span creates random requests to elevator
			if ((end - start) / 1000F >= properties.getSpan()) {
				floor = properties.randRequest();
				
				//Split tasks by elevators
				if(elevator1.getRequests().size() > elevator2.getRequests().size()) {
					elevator2.request(floor);
				}
				else {
					elevator1.request(floor);
				}
			}
			//Makes elevator move
			try {
				Thread.sleep(properties.getVelocity() * 1000);
			} catch (InterruptedException e) {
				System.out.println("Elevator velocity error");
			}
			elevator2.move();
			elevator1.move();
		}
		
	}
	
	public static void setProperities(GenProperities properties) {
		
		System.out.println("Enter the height of building");
		
		properties.setHeight(in.nextInt());
		
		System.out.println("Enter the lenght of interval (in seconds)");
		
		properties.setSpan(in.nextDouble());
		
		/*Since the velocity of elevator is not required,
		 * I've hardcoded it
		*/
		properties.setVelocity(3);
		
	}

}
