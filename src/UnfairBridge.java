

class UnfairBridge extends Bridge {
	
	
	private int count;	//Stores the number of a car in each type.
	private char type;	//Stores the type of each car (Red or Blue).
	
	//UnfairBridge Constructor
	public UnfairBridge() {
		
	

	}
	


	

	//Only one Thread can use this method and we don't have crashes
	public void Crossing(int count, char type) throws InterruptedException {
		
		Main.lock.lock();	//Beginning of Critical Section. Only one Thread at a time is able to enter after this point.
		this.count = count; //Car Number
		try {
			
			
			
			Main.time++; //Time variable increased by one every time a Car is trying to pass the bridge.
			if(type == 'R') {
				System.out.printf("\nRed Car %d Passing at Time%d \n",this.count,Main.time); //Prints that a Red Car is passing the bridge, also it shows the number of the car and the time.
			}
			else if(type == 'B') {
				System.out.printf("\n							Blue Car %d Passing at Time%d \n",this.count,Main.time); //Prints that a Blue Car is passing the bridge, also it shows the number of the car and the time.
			}
			
			exit(this.count, type);

			
					
		}finally {
			Main.lock.unlock(); //End of Critical Section. Now another Thread can enter this Critical Section.
		}
		
	}

		
	
	//Prints the car that has passed the bridge along with it's type and time.
	public void exit(int count, char type) {
		this.count = count;
		this.setType(type);
		
		Main.time++;
		if(type == 'R') {
			System.out.printf("							Red Car %d Passed at Time%d \n",this.count,Main.time);
		}
		else if(type == 'B') {
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
