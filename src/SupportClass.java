
public class SupportClass {
	
	protected int Num_Red; //Variable that stores the total amount of Red cars
	protected int Num_Blue; //Variable that stores the total amount of Blue cars

	//Support class constructor
	public SupportClass() {
		
		
	}
	
	//Defines which type of car will start first based on their number.
	public void SetTurn(int Num_Red, int Num_Blue) {
		this.Num_Red = Num_Red;
		this.Num_Blue = Num_Blue;
		
		if(this.Num_Blue == 0 || this.Num_Blue <= this.Num_Red)
			Main.turn = 'R';
		else if(this.Num_Red == 0 || this.Num_Blue > this.Num_Red)
			Main.turn = 'B';
	}
	
}
