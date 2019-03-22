
public class Red_Car extends Thread {
	
	private int count; 		//Stores the number of a car in each type
	private char type; 		//Stores the type of each car (Red or Blue).
	private Bridge bridge; 	//We use it to run our bridge.



	//Constructor for Red Cars.
	public Red_Car(int count, Bridge bridge, int choice) {
		this.count = count;
		this.bridge = bridge;
		type = 'R';
		System.out.println("Red Car " + count + " Arrived at Time" + Main.time);
		
	}
	
	//Code that runs every time a Red Car starts.
	public void run()  {
			
			try {
					bridge.Crossing(count, type);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
	}
}
