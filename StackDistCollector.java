/**************************************************************************
 * @author Uzair Minhas
 * @version 5/1/18
 * CS310 Spring 2018
 * Project 4
 * George Mason University
 * 
 * File Name: StackDistCollector.java
 *
 * Description: A class that calculates the (LRU) stack distance for any given sequence of access times.
 * 
 ***************************************************************************/

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.io.PrintWriter;
import java.io.FileWriter;

// class to calculate (LRU) stack distance for any given sequence of accesses
public class StackDistCollector{

	/**
	 * Processes a single access and calculates the LRU stack distance
	 * @param address a provided address
	 * @return an integer value representing the LRU stack distance
	 */
	public Integer access(int address){
		// process one access and calculate the LRU stack distance for that access
		// - if the address has never been accessed before, he distance is infinity  
		// - otherwise the distance is how many distinct addresses have been accessed 
		//    since the address was accessed last time (including itself)
		//
		// return the calculated stack distance
		// if the distance is infinity, return null
		// 
		// Hint: get the help from symbolTable and pastAccesses
		// Note: also need to perform maintenance tasks before return, including:
		// 	1. update symbolTable 
		//  2. update pastAccesses 
		//  3. advance time to the next cycle
		//
		// symbolTable:
		//   - must have one entry for every address that have been accessed
		//   - must store the lastest access time for that address in the table
		//   - to remember the distinct addresses we have accessed
		//
		// pastAccesses:
		//   - also store the lastest access time for every distinct address 
		//   - store them as an ordered collection and support efficient operation
		//   - to remember the time of distinct accesses in the past (before this.time)
		//
		// time:
		//   - indicate which cycle the address is accessed
		//   - move to the next cycle for a new reference
		
		// T' represents the last time an address was accessed
		// T represents the current time
		// T-1 represents 1 minus the current time
		// LRU is calculated as the number of distinct elements from T' to T-1
		
		// The first time access is called with 'd' the access time is infinity (return null)
		// Add to the table with the address and last access time (current time)
		// Don't create a new SymbolRecord if it already exists in the table
		// When an address is accessed, the last time it was accessed was "right now", so set its last time as the current time
		
		// The stack distance is the number of distinct past accesses between your last access for an address and the current time
		// Use pastAccesses field here
		
		// NO NEED TO USE A STACK HERE
		
		SymbolRecord Tprime = symbolTable.getRecord(address);
		
		if(Tprime == null) {
			//creates a new SymbolRecord based on current time
			SymbolRecord entry = new SymbolRecord(address, this.time);
			
			//adds SymbolRecord to hash table
			symbolTable.putRecord(address, entry); 
			
			//add current access time in sequence
			pastAccesses.insert(time);
			
			this.time++;
			return null;
		}
		else {
			
			//count distinct accesses from the last time was accessed (getLastAccessTime()) to current time less 1 (T-1)
			int distance = pastAccesses.countNoSmallerThan(Tprime.getLastAccessTime()); //plus or minus 1?
			
			//remove past access time from sequence for SymbolRecord
			pastAccesses.remove(Tprime.getLastAccessTime());
			
			//add current access time in sequence
			pastAccesses.insert(this.time);
			
			//update SymbolRecord for latest access time
			Tprime.setLastAccessTime(this.time);
			
			//update hash table for SymbolRecord with new access time
			symbolTable.putRecord(this.time, Tprime);
			
			this.time++;
			return distance;
		}
		
		
		
	}


	// DO NOT MODIFY BELOW THIS LINE
    //-----------------------------------------------
    //-----------------------------------------------

	private int time; 
	// integer value to simulate clock cycle time for accesses
	// initialize as zero for the start of each sequence
	// advance by one for each access in the sequence


	MySymbolTable<Integer, SymbolRecord> symbolTable;  
	// symbol table to remember the distinct addresses we have accessed
	// Integer represents the address
	
	MySequence<Integer> pastAccesses; 
	// sequence to remember the time of distinct accesses in the past (before this.time)
	
	Stack<Integer> distSequence;  
	// storage of the calculated distances for each access in the sequence up to this.time
	// storage organized as a stack (FILO)
	
	// constructor
	public StackDistCollector(){
		time = 0;
		symbolTable = new MySymbolTable<Integer, SymbolRecord>();
		pastAccesses = new MySequence<Integer>();
		distSequence = new Stack<Integer>();
		
	}
	
	// accessor
	public int getTime(){
		return time;
	}
	
	// collect the stack distances for a new sequence
	// input: a plain text file with a sequence of integer numbers separated by space
	// output: another plain text file with the calculated sequence of stack distances
	//         in REVERSE order (again a sequence of integer numbers separated by space)
	// 
	public void processSequence(String inFileName, String outFileName) throws IOException{
		// reset everything to start over
		time = 0;
		pastAccesses = new MySequence<Integer>(); //represents a sequence of access times, based on whether existing or new address has been called
		symbolTable = new MySymbolTable<Integer, SymbolRecord>();
		distSequence = new Stack<Integer>();

		// open the input file
		Scanner input = new Scanner(new File(inFileName));
		
		// process accesses one by one and push the distance calculated onto a stack
		while(input.hasNext()) {
			int address = Integer.parseInt(input.next());
			// add it to distance stack
			distSequence.push(access(address));
		}
	
		// output distances into the outFile
		PrintWriter out = new PrintWriter(new FileWriter(outFileName));
		while (!distSequence.isEmpty()){
			Integer dist = distSequence.pop();
			if (dist!=null)
				out.print(dist.toString()+" ");
			else
				out.print("inf ");
		}
		out.close();
	}
	
	
	
	public static void main(String[] args) throws IOException{
	
		StackDistCollector collector = new StackDistCollector();
		
		if(collector.access(20)==null && collector.symbolTable.size() == 1
			&& collector.pastAccesses.contains(0)){
			System.out.println("Yay 1");
		}

		if(collector.access(20)==1 && collector.symbolTable.getRecord(20).getLastAccessTime() == 1
			&& collector.pastAccesses.contains(1) && !collector.pastAccesses.contains(0)){
			System.out.println("Yay 2");
		}
		
		if(collector.access(32)==null && collector.access(20)==2 && collector.access(20)==1
			&& collector.access(20)==1 && collector.access(32)==2){
			System.out.println("Yay 3");
		}
		
		if(collector.getTime() == 7 && collector.pastAccesses.toStringAscendingOrder().equals("5 6 ") ){
			System.out.println("Yay 4");
		}
		
		collector.processSequence("inputs/input1_reverse.txt","inputs/input1_my_distance.txt");
		//additional testing
		collector.processSequence("inputs/input2_reverse.txt","inputs/input2_my_distance.txt");
		collector.processSequence("inputs/input3_reverse.txt","inputs/input3_my_distance.txt");
		collector.processSequence("inputs/input4_reverse.txt","inputs/input4_my_distance.txt");
		collector.processSequence("inputs/input5_reverse.txt","inputs/input5_my_distance.txt");
		collector.processSequence("inputs/input6_reverse.txt","inputs/input6_my_distance.txt");


		// you can manually inspect the output file: inputs/input1_my_distance.txt
		// expected output should be identical to inputs/input1_distance.txt
		
		
	}
	
	
}

/**
*  
*  Class for record we want to keep for every symbol (address) from the access sequence
*
*  used in symbol table construction
*/
class SymbolRecord{
	private int address;  		//address of the symbol (as integer)
	private int lastAccessTime; //clock cycle of the latest access to the symbol
	
	// constructor
	public SymbolRecord(int address, int time){
		this.address = address;
		this.lastAccessTime = time;
	}
	
	// accessors
	public int getAddress(){
		return this.address;
	}
	
	public int getLastAccessTime(){
		return this.lastAccessTime;
	}
	
	// mutator for lastAccessTime
	public void setLastAccessTime(int newTime){
		this.lastAccessTime = newTime;
	}
	
}