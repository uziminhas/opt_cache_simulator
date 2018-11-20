/**************************************************************************
 * @author Uzair Minhas
 * @version 5/1/18
 * CS310 Spring 2018
 * Project 4
 * George Mason University
 * 
 * File Name: MySequence.java
 *
 * Description: A class that stores a collection of values as a sorted sequence with no duplicates.
 * 
 ***************************************************************************/

// your implementation of Sequence interface
// header comments here

import java.util.TreeSet;

// do we use a BST for the data structure?
// do we implement TreeSet for the data structure?
// needs to represent a Sorted Set, but the underlying data structure is up to us
// consider adding a private nested tree class
// track size variable when adding/removing items

public class MySequence<T extends Comparable<T>> implements Sequence<T>{
	

	// you decide the internal design of your class:
	//  - it must implement the provided Sequence interface
	//  - it cannot have any other public members (attributes or method) other than
	//    the public constructor and the methods defined in Sequence interface
	private TreeSet<T> sequence;
	
	/**
	 * Constructs a new sequence using TreeSet.
	 */
	public MySequence(){
		sequence = new TreeSet<T>();
	}
	
	/**
	 * Inserts a new value into the sequence.
	 * @param generic type value to be added.
	 */
	public boolean insert(T v) {
		return sequence.add(v);
	}
	
	/**
	 * Removes a value from the sequence.
	 * @return a generic type value that is removed.
	 */
	public boolean remove(T v) {
		return sequence.remove(v);
	}
	
	/**
	 * Checks whether the sequence contains a given value.
	 * @return true whether the sequence contains the given value, false if it does not.
	 */
	public boolean contains(T v) {
		return sequence.contains(v);
	}
	
	/**
	 * Counts values in the sequence that are greater than or equal to a given value.
	 * @return an integer representing the number of values in the sequence that are greater than or equal to a given value.
	 */
	public int countNoSmallerThan(T v) {
		return sequence.tailSet(v).size();
	}
	
	/**
	 * Returns the number of items in the sequence.
	 * @return an integer representing the number of items in the sequence.
	 */
	public int size() {
		return sequence.size();
	}
	
	/**
	 * Returns a string representation of all values in the sequence in ascending order.
	 * @return a string representation of all values in the sequence in ascending order.
	 */
	public String toStringAscendingOrder() {
		String ascend = "";
		for(T val : sequence) {
			ascend = ascend + val + " ";
		}
		return ascend;
	}
	
	// make sure you implement all methods in Sequence interface


	//------------------------------------
	// example test code... edit this as much as you want!
	public static void main(String[] args){
		MySequence<Integer> seq = new MySequence<Integer>();
		
		if(seq.size()==0 && !seq.contains(11) && seq.countNoSmallerThan(10) == 0 
			&& seq.toStringAscendingOrder().equals("")) 	{
			System.out.println("Yay 1");
		}
		
		seq.insert(11);
		seq.insert(5);
		
		if(seq.insert(200) && seq.size()==3 && seq.contains(11) 
			&& seq.countNoSmallerThan(10) == 2 && !seq.remove(20)) 	{
			System.out.println("Yay 2");
		}
		
		seq.insert(112);
		seq.insert(50);
		seq.insert(20);
		
		if(seq.remove(20) && !seq.contains(20) && !seq.insert(200)
			&& seq.countNoSmallerThan(50) == 3 
			&& seq.toStringAscendingOrder().equals("5 11 50 112 200 ")) 	{
			System.out.println("Yay 3");
		}
		

	}
}


