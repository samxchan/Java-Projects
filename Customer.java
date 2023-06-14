
public class Customer {

	private int arrivalTime;
	private int usageTime;
	
	public Customer(int a){
		
		arrivalTime = a;
		usageTime = (int) (Math.random()*3+1);
	}
	
	public int getArrival(){
		
		return arrivalTime;
	}
	
	public int getUsage(){
		
		return usageTime;
	}
	
}
