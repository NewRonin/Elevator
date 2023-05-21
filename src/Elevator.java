import java.util.ArrayList;

public class Elevator {
	
	private int floor; // Number of current floor
	private int direction; // 1 for upper movement, -1 for moving down (in seconds)
	private String name;
	private ArrayList<Integer> requests; // A link for current requests queue
	private ArrayList<Integer> upRequests; 
	private ArrayList<Integer> downRequests; 
	
	
	public Elevator(int floor, int height, String name) {
		this.floor = floor;
		this.name = String.format("[%s]: ", name);
		this.direction = 1; //Starts with moving up
		this.upRequests = new ArrayList<>();
		this.downRequests = new ArrayList<>();
		this.requests = this.upRequests; //Doesn't create a new object
	}

	public void request(int floor, int direction) throws ArrayIndexOutOfBoundsException {
		
	    ArrayList<Integer> queue = this.upRequests;;
	    
	    
	    if (direction < 0) {
	    	queue = this.downRequests;
	    }
		
		int index = binSearch(queue, floor, 0, queue.size()-1);
		
		//Prevents copying
		if(index >= queue.size() || queue.get(index) != floor) {
			queue.add(index, floor);
		}
		
	}
	
	
	public int binSearch(ArrayList<Integer> sortedArray, int key, int low, int high) {

		while (low <= high) {
			int mid = (low + high) / 2;
			if (sortedArray.get(mid) < key) {
				low = mid + 1;
			} else if (sortedArray.get(mid) > key) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		
		return low;
	}
	
	// Moves to the closest floor
	public void move() throws ArrayIndexOutOfBoundsException{
		
		//Don't do anything if nothing is requested
		if(requests.size() == 0) {
			return;
		}
		
		//Find next closest floor
		int i = binSearch(requests, floor, 0, this.requests.size()-1);
		
		
		if (i <= 0) {
			this.direction = 1;
			this.requests = this.upRequests;
			i = 0;
		}
		
		if (this.requests.size() > 0 && i >= this.requests.size() - 1 && floor > this.requests.get(this.requests.size() - 1)) {
			this.direction = -1;
			this.requests = this.downRequests;
			i = this.requests.size() - 1;
		}
		
		//Remove request if requested floor is reached
		if(requests.get(i) == floor) {
			System.out.println(this.name + "Stop at foor: " + Integer.toString(this.floor));
			this.floor += this.direction;
			this.requests.remove(i);
		}
		
		else {
			System.out.println(this.name + "Passed floor: " + Integer.toString(this.floor));
			this.floor += this.direction;
		}
		
	}
	
	public ArrayList<Integer> getRequests() {
		return requests;
	}

	public void setRequests(ArrayList<Integer> requests) {
		this.requests = requests;
	}
		
}
