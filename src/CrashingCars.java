

public class CrashingCars extends Bridge {
	private int count;		//Stores the number of a car in each type.
	private char type;		//Stores the type of each car (Red or Blue).
	
	//Constructor CrashingCars
	public CrashingCars() {

	}
	
	//In this method the Threads are coming all together and they are crashing with each other
	public void Crossing(int count, char type) throws InterruptedException {
			
			
			this.count = count;
			try {
				if(type == 'R') {
					Main.time++;
					System.out.printf("\nRed Car %d Passing at Time%d \n",this.count,Main.time);
				}
				else if(type == 'B') {
					Main.time++;
					System.out.printf("\n							Blue Car %d Passing at Time%d \n",this.count,Main.time);
				}
				
			}finally {
				exit(this.count, type); 
			}
			
		}
	
			
		
		//Prints the car that has passed the bridge along with it's type and time.
		public void exit(int count, char type) {
			this.count = count;
			this.setType(type);
			
			if(type == 'R') {
				Main.time ++;
				System.out.printf("							Red Car %d Passed at Time%d \n",this.count,Main.time);
			}
			else if(type == 'B') {
				Main.time++;
				System.out.printf("Blue Car %d Passed at Time%d \n",this.count,Main.time);
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
