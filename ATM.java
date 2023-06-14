/*
 * Stephen Chan
 * period 7
 * 10/16/12
 * ATM- we write a program simulating the amount of people who will go to the ATM in one hour
 */

import java.util.*;
public class ATM {

	private Queue<Customer> line;
	
	public ATM(){
		
		line=new LinkedList();
	}
	
	//this simulate the hour of customers and prints out the results
	public void simulate(){
		
		int total=0;
		int timePassed=0;
		int people=0;
		while(timePassed<60){
			
			total += randCustomers();
			people = getAmtCustomerUse();
			timePassed += getUsageTime(people);
		}
		
		System.out.print("amount of customers who used the ATM: " + people);
		System.out.print("avg waiting time of customers: " + timePassed/people);
		System.out.print("amount of waiting customers: " + (total-people));
		
		
	}
	
	public static void main(String args[]){
		
		ATM sim = new ATM();
		sim.simulate();
	}
	
	//returns the amount of customers who used the atm, keeps the queue intact 
	private int getAmtCustomerUse(){
		
		int counter=0;
		Queue<Customer> temp = new LinkedList();
		while(line.peek().getUsage()!=0){

			temp.add(line.remove());
		}
		
		while(temp.peek().getUsage()!=0){

			line.add(temp.remove());
			counter++;
		}
		
		return counter;
	}
	
	//gets the total amount of time waiting (used time)
	private int getUsageTime(int t){
		
		int total=0;
		Queue<Customer> temp = new LinkedList();
		for(int i=0; i<t; i++){
			
			total += line.peek().getUsage();
			temp.add(line.remove());
		}
		
		while(temp.peek().getUsage()!=0){

			line.add(temp.remove());
		}
		
		return total;
	}
	
	//randomly makes and adds 0-3 customers to the Queue and returns the amount
	private int randCustomers(){
		
		int temp = (int) (Math.random()*3); 
		int customers = 0;
		int a;
		
		for(int i=0; i<temp; i++){
			
			if(line.isEmpty())
				a=0;
			
			else
				a = line.peek().getArrival() + line.peek().getUsage();
	
			Customer c = new Customer(a);
			line.add(c);
			customers++;
		}
		
		return customers; 
	}
}
