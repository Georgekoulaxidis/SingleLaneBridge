
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class Main {
	//Here is our static variables that we want to be shared between the Threads.
	

	static Lock lock = new ReentrantLock(); // lock variable is used to create a critical section, in our code, and prevents other Threads to join it, while another Thread is in it.
	static int time = 0; //time variable is used to count the time that a car has arrived, is passing and passed the bridge.
	static char turn; //turn is used define the type of a car (Red or Blue).
	static int line = 1; //The meaning behind line variable is to keep the cars in a specific row.


	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int Num_Red = (int) (Math.random()*7+1); //Random integer number from 1-7 that represents the Red cars.
		int Num_Blue = (int) (Math.random()*7+1); //Random integer number from 1-7 that represents the Blue cars.

		int R = 0; 	//Next free position in Red Array.
		int B = 0;	//Next free position in Blue Array.
		int i = 0;
		int choice; //Action that user wants to be completed
		
		SupportClass support = new SupportClass(); 	 //Deals with some extra functions for scenario 3 and 4.
		Scanner input = new Scanner(System.in);		//A variable that stores user's choice about scenarios.
		Bridge bridge = null;					   //Bridge variable, we use it to create the bridge.
		
		Red_Car RedTable[] = new Red_Car[Num_Red]; 		//Array for Red Cars with length equal to Num_Cars.
		Blue_Car BlueTable[] = new Blue_Car[Num_Blue];	//Array for Blue Cars with length equal to Num_Cars.
		
		
		
		System.out.println("Pick a scenario from 1-4 or 5 for exit: ");
		do {
			choice = input.nextInt(); //We let user to pick the scenario that he wants
		}while(choice < 1 || choice > 5); //User must pick a number from 1-5 or else he won't get out of while.
		
		
		switch(choice) {
			case 1:
				bridge = new CrashingCars();					//Bridge creation for Scenario 1
				break;
			case 2:
				bridge = new UnfairBridge();					//Bridge creation for Scenario 2
				break;
			case 3:
				support.SetTurn(Num_Red, Num_Blue);
				bridge = new FairBridge(Num_Red, Num_Blue);		//Bridge creation for Scenario 3
				break;
			case 4:
				support.SetTurn(Num_Red, Num_Blue);
				bridge = new PerfectBridge(Num_Red, Num_Blue);	//Bridge creation for Scenario 4
				break;
			case 5:
				System.exit(0);
		}
		input.close();
		
		System.out.println("Left Side			Bridge			Right Side");
		
		
		do {
			time++; //Goes up every time a Car is arriving.
			if((i%2 == 0 && R!= Num_Red) || B == Num_Blue) {
				RedTable[R] = new Red_Car(R+1, bridge,choice); 		//Create Red Cars
				R++;
			}
			else{
				BlueTable[B] = new Blue_Car(B+1, bridge, choice); 	// Create Blue Cars
				B++;
			}
			i++;
			
		}while(i < (Num_Red+Num_Blue));
		
		i=0; B=0; R=0;
		//Starts all Threads
		do {
			
			if((i%2 == 0 && R!= Num_Red) || B == Num_Blue) {
				RedTable[R].start(); 	//Starts Red cars Threads
				R++;
			}
			else{
				BlueTable[B].start(); 	//Starts Blue cars Threads
				B++;
			}

			i++;
		}while(i < (Num_Red+Num_Blue));
		
		
		i=0; B=0; R=0;
		do {
			
			if((i%2 == 0 && R!= Num_Red) || B == Num_Blue) {
				try {
					RedTable[R].join();
					R++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				try {
					BlueTable[B].join();
					B++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			i++;
		}while(i < (Num_Red+Num_Blue));
	}
	
}
