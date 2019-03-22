
public class Blue_Car extends Thread{

	protected int count;	//Stores the number of a car in each type
	private char type;     	//Stores the type of each car (Red or Blue).
	private Bridge bridge; 	//We use it to run our bridge.

	//Constructor for Blue Cars.
	public Blue_Car(int count, Bridge bridge, int choice) {
		this.count = count;
		this.bridge = bridge;
		type = 'B';
		System.out.println("							Blue Car " + count + " Arrived at Time" + Main.time); //Printing Blue Cars arrival.
	}
	
	//Code that runs every time a Blue Car starts.
	public void run()  {
		

			
			try {
				bridge.Crossing(this.count, type);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
	}
		
}
