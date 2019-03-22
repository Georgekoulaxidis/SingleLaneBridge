

public class FairBridge extends Bridge {
	
	private int count; 	//Stores the number of a car in each type.
	private char type;	//Stores the type of each car (Red or Blue).
	private boolean Red = false; //We use it to know when a Red car has crossed the bridge.
 	private boolean Blue = false; //We use it to know when a Blue car has crossed the bridge.
	private int nred; //Stores the number of Red cars which are waiting to cross the bridge.
	private int nblue; //Stores the number of Blue cars which are waiting to cross the bridge.

	

	//FairBridge constructor
	public FairBridge(int Num_Red, int Num_Blue) {
		nred = Num_Red;
		nblue = Num_Blue;
		
	}
	
	
	//The Threads are crossing the bridge the same way they arrived and without crashes.
	synchronized void Crossing(int count, char type) throws InterruptedException {
		
			this.count = count;
			this.type = type;
			
			try {
				
				//If a Thread comes and it isn't it's turn, then it will wait.
				while(!(this.type == Main.turn && this.type == 'R' && this.count <= Main.line) && !(this.type == Main.turn && this.type <= 'B' && this.count <= Main.line)) {
						wait();
						this.count = count;
						this.type = type;
				}
				
				if(this.type == Main.turn && this.type == 'R' && this.count <= Main.line) {	
						Main.time++;
						System.out.printf("\nRed Car %d Passing at Time%d \n",this.count,Main.time);
						exit(this.count, this.type);
						
				
				}
				else if(this.type == Main.turn && this.type == 'B' && this.count <= Main.line) {
						Main.time++;
						System.out.printf("\n							Blue Car %d Passing at Time%d \n",this.count,Main.time);
						exit(this.count, this.type);
				}
				
				Checker(); //Checks if any of the types are out of car and the other isn't.
				
				if(Red && Blue) //Checks if both type of cars, for a specific line, have crossed the bridge. 
					UpLine();
				
				LeavingBridge(); //Decrease total amount of cars for each type.
							
				notifyAll(); //When a Thread ends it notifies every other.
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	//If both types have crossed the bridge we increase line, so the next cars could pass wait.
	synchronized void UpLine() {
		if(this.count == Main.line) {
			Main.line++;
			Red = false;
			Blue = false;
		}
		else if(this.count < Main.line && this.type == 'R')
			Red = false;
		else if(this.count < Main.line && this.type == 'B')
			Blue = false;
		
	}
	
	//Turn for Red cars to cross the bridge.
	synchronized void RedTurn() {
		Main.turn = 'R';
	}
	
	//Turn for Blue cars to cross the bridge.
	synchronized void BlueTurn() {
		Main.turn = 'B';
	}
	
	/*Prints the car that has passed the bridge along with it's type and time. 
	Also it calls the methods that are used to define the type of the car that is next (Red or Blue).*/
	synchronized void exit(int count, char type) {
		this.count = count;
		this.setType(type);
		
		Main.time++;
		if(type == 'R') {
			System.out.printf("							Red Car %d Passed at Time%d \n", this.count, Main.time);
			Red = true;
			BlueTurn();
			
		}
		else if(type == 'B') {
			System.out.printf("Blue Car %d Passed at Time%d \n", this.count, Main.time);
			Blue = true;
			RedTurn();
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
	
	/*Every time that a Red or a Blue car leaves the bridge this method decrease the total amount 
	  of Red or Blue cars, respectively.*/
	synchronized void LeavingBridge() {
		if(this.type == 'R')
			nred--;
		else
			nblue--;
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
