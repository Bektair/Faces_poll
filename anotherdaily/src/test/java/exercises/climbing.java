package exercises;

public class climbing {

	private int lastlast=1; //Solved n=1
	private int last=2; // Solved n = 2
	private int count = 2; //First unknown n=3
	private int stairs;
	
	public climbing(int stairs) {
		this.stairs = stairs;
	}
	
	//I want it to use the first known values to add towards the last
	public int fibona(){
		if(count==stairs) return last;
		int temp = last;
		last = last+lastlast;
		lastlast = temp;
		count++;
		return fibona();
  }

	
	public static void main(String[] args) {
		//
		climbing c = new climbing(10);
		System.out.println(c.fibona());
		
		
		//I CLIMB stairccase, n steps needed to each top.
		//Each time you can climb either 1 or 2 steps
		//In how many distinct ways can you climb to the top?
		//Input is total number of steps?
		//Total number of steps >= 1000.
	}

}
