
public class PerfectBridge extends Bridge {
	
	private int count; //Stores the number of the car for each type.
	private char type; //Stores the type of each car (Red or Blue).
	private int i = 1;	
	private int RedLine = 1; // Stores the number of Red car which is the next to cross the bridge.
	private int BlueLine = 1; //Stores the number of Blue car which is the next to cross the bridge.
	private int nred; //Stores the number of Red cars which are waiting to cross the bridge.
	private int nblue; //Stores the number of Blue cars which are waiting to cross the bridge.
	
	public PerfectBridge(int Num_Red, int Num_Blue) {
		nred = Num_Red;
		nblue = Num_Blue;
	}
	
	/*We give priority to the type with most cars and we let two cars from that type to pass and one from the other. 
	When the types have the same amount of cars they are passing the one after the other.*/
	synchronized void Crossing(int count, char type) throws InterruptedException {
		this.count = count;
		this.type = type;
		
		try {
			
			//While the car hasn't the right type or turn it will wait.
			while(!(this.type == Main.turn && this.type == 'R' && this.count == RedLine) && !(this.type == Main.turn && this.type <= 'B' && this.count == BlueLine)) {
					wait();
					this.count = count;
					this.type = type;
			}
			
			if(this.type == Main.turn && this.type == 'R' && this.count == RedLine) { //Red car attempting to cross the bridge.
					Main.time++;
					System.out.printf("\nRed Car %d Passing at Time%d \n",this.count,Main.time);
					exit(this.count, this.type);
					
			
			}
			else if(this.type == Main.turn && this.type == 'B' && this.count== BlueLine) { //Blue car attempting to cross the bridge.
					Main.time++;
					System.out.printf("\n							Blue Car %d Passing at Time%d \n",this.count,Main.time);
					exit(this.count, this.type);
			}
			
			Checker(); //Checks if any of the types are out of car and the other isn't.

			
			LeavingBridge(); //Decreasing the total amount of Red or Blue cars.
						
			notifyAll(); //We "wake up" all the other Threads that have been waiting.
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/*Prints the car that has passed the bridge along with it's type and time. 
	Also it calls the methods that are used to define the type of the car that is next (Red or Blue).*/
	synchronized void exit(int count, char type) {
		this.count = count;
		this.setType(type);
		
		Main.time++;
		if(type == 'R') {
			System.out.printf("							Red Car %d Passed at Time%d \n", this.count, Main.time);
			BlueTurn();
			
		}
		else if(type == 'B') {
			System.out.printf("Blue Car %d Passed at Time%d \n", this.count, Main.time);
			RedTurn();
		}
		
	}
	
	/*Every time that a Red or a Blue car leaves the bridge this method decrease the total amount 
	  of Red or Blue cars, respectively.*/
	synchronized void LeavingBridge() {
		if(this.type == 'R')
			nred--;
		else
			nblue--;
	}
	
	//if the difference between Blue and Red cars is bigger than 1, then we let two cars from Blue type and one from Red.
	synchronized void RedTurn() {
		if(nblue-nred > 1) {
			if(i==2) { //Here we check if 2 Blue cars have crossed the bridge. If it's true we switch type.
				i=1;
				BlueLine++;
				Main.turn = 'R';
			}
			else{
				i++; //We increase i every time a Blue car is crossing the bridge.
				BlueLine++;
				Main.turn = 'B';
				
			}
		}
		else { //If we don't have difference > 1 we just switch type.
			BlueLine++;
			Main.turn = 'R';

		}
	}
	
	//Checks if the number of cars from one type is empty and from the other isn't, then we switch type
	synchronized void Checker() {
		if(nred == 0 && nblue != 0) {
			Main.turn = 'B';
			Main.line++;
			
		}
		else if(nred !=0 && nblue == 0) {
			Main.turn = 'R';
			Main.line++;
		}
		
	}
	
	//if the difference between Red and Blue cars is bigger than 1, then we let two cars from Red type and one from Blue.
	synchronized void BlueTurn() {
		if(nred-nblue > 1) { 
			if(i == 2) {	//Here we check if 2 Red cars have crossed the bridge. If it's true we switch type.
				i=1;
				RedLine++;
				Main.turn = 'B';
			}
			else {
				i++;	//We increase i every time a Red car is crossing the bridge.
				RedLine++;
				Main.turn = 'R';
			
				
			}
		}
		else { //If we don't have difference > 1 we just switch type.
			RedLine++;
			Main.turn = 'B';

		}
	}
	
	//Type getter
	public char getType() {
		return type;
	}
	
	//Type setter
	public void setType(char type) {
		this.type = type;
	}
}


