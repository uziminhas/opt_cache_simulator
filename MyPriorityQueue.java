/**************************************************************************
 * @author Uzair Minhas
 * @version 5/1/18
 * CS310 Spring 2018
 * Project 4
 * George Mason University
 * 
 * File Name: MyPriorityQueue.java
 *
 * Description: A class that represents a max priority queue.
 * 
 ***************************************************************************/

// your implementation of PriorityQueue interface
// header comments here


// One additional requirement is the value type T must be Comparable.
// When two items are compared to determine the order in priority queue, follow the rules below:
//  1. use the priority of them to determine the order 
//  2. if they are of the same priority, use their values (of type T) to determine the order
//  3. if they are of the same priority and same value, any order is fine

// represents a max priority queue - where larger elements have higher priority (i.e. larger addresses with higher values)
// implement using a max heap order in the array, with a default size that expands when necessary
// MAY BE POSSIBLE TO IMPLEMENT USING A LINKED LIST, OTHERWISE USE A DYNAMIC ARRAY
// create a private nested class for Pair - create an array of Pairs

// You do not want to implement a compareTo() method as you will overwrite the underlying data types compareTo() method.
// use .compareTo to break a tiebreaker in the event two symbols have the same address/same priority (i.e. compare the addresses)

// USE CODE FROM THE TEXTBOOK! REVIEW PRIORITY QUEUE LECTURE SLIDES!

public class MyPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T>
{
	
	// you decide the internal design of your class:
	//  - it must implement the provided PriorityQueue interface
	//  - it cannot have any other public members (attributes or method) other than
	//    the public constructor and the methods defined in PriorityQueue interface
	/**
	 * Private inner class which represents a Pair object.
	 * @param <T> generic type value of class.
	 */
	private class Pair<T extends Comparable<T>> implements Comparable<Pair<T>> {
		T key;
		int priority;
		
		/**
		 * Constructs a new Pair.
		 * @param key generic type value of key.
		 * @param priority integer which represents priority of Pair.
		 */
		public Pair(T key, int priority) {
			this.key = key;
			this.priority = priority;
		}
		
		/**
		 * Returns key associated with this Pair.
		 * @return key associated with this Pair.
		 */
		public T getKey() {
			return this.key;
		}
		
		/**
		 * Returns priority value for this Pair.
		 * @return priority value for this Pair.
		 */
		public int getPriority() {
			return this.priority;
		}
		
		/**
		 * Sets priority value for this Pair.
		 * @param p new priority value to be set.
		 */
		public void setPriority(int p) {
			this.priority = p;
		}
		
		/**
		 * Compares this Pair with a provided Pair.
		 * @param a provided Pair to be compared against.
		 * @return a positive, negative, or zero value depending on comparison of Pairs.
		 */
		public int compareTo(Pair<T> val) {
			if(this.getPriority() == val.getPriority()){
				return this.getKey().compareTo(val.getKey());
			}
			else {
				return this.getPriority() - val.getPriority();
			}
		}
	}
	

	static final int INF = Integer.MAX_VALUE; // the max priority to use: infinity
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize; // number of elements in heap
    private Pair<T>[] array; // heap array of pairs
	
	/**
     * Construct an empty PriorityQueue.
     */
    public MyPriorityQueue()
    {
    		currentSize = 0;
    		array = new Pair[DEFAULT_CAPACITY + 1];
    }
    
    /**
     * Returns the number of items in this PriorityQueue.
     * @return the number of items in this PriorityQueue.
     */
    public int size() {
    		return currentSize;
    }
    
    /**
     * Returns the max item in this PriorityQueue.
     * @return the max item in this PriorityQueue.
     */ 
    public T peek() {
    		return array[1].getKey();
    }
    
    /**
     * Inserts a new value with a given priority into this PriorityQueue.
     * @param v a generic type value to be added.
     * @param p the priority associated with the value to be added.
     */
    public void insert(T v, int p) {
    	
    		if(currentSize + 1 == array.length) {
    			//expand/double the array
    			Pair<T>[] newArr = new Pair[array.length * 2];
    			for(int i = 0; i < array.length; i++) {
    				newArr[i] = array[i];
    			}
    			array = newArr;
    		}
    		
    		int hole = currentSize + 1; //add hole at next empty node in array
    		Pair temp = new Pair(v,p); //create new Pair with value/priority provided
    		array[0] = temp;
    		    		
    		while(array[hole/2].compareTo(temp) < 0) {
    			array[hole] = array[hole/2]; //if parent has less priority than temp, swap parent with hole
    			hole = hole / 2;
    		}
    		
    		array[hole] = temp; //add temp at hole when correct priority is determined
    		currentSize++; //increment current size
    
    }
    
    /**
     * Removes a value from the PriorityQueue.
     * @return a generic type value that is removed from the PriorityQueue.
     */
    public T remove() {
    		T maxItem;
    		if(array[1] == null) {
    			maxItem = null;
    		}
    		else {
    			maxItem = array[1].getKey();
    			array[1] = array[currentSize--]; //swap first element with last element in array
    			
    			//update priority queue to maintain heap order
    			int child = 0;
    			int hole = 1; //set hole at first index 
    			Pair temp = array[hole]; //create a temp Pair
    			for( ; hole * 2 <= currentSize; hole = child) {
    				child = hole * 2;
    				if(child != currentSize && array[child + 1].getPriority() > array[child].getPriority()) {
    					child++;
    				}
    				if(array[child].getPriority() > temp.getPriority()) {
    					array[hole] = array[child];
    				}
    				else {
    					break;
    				}
    			}
    			array[hole] = temp;
    			
    		}
    		return maxItem;
    }
    
    /**
     * Updates the priority of an item in the PriorityQueue.
     * @param v the value to be updated
     * @param p the new priority of the item.
     */
    public void updatePriority(T v, int p) {
    	
    		//loop through array of Pairs to update priority
    		for(int i = 1; i <= currentSize; i++) {
    			if(array[i].getKey().equals(v)) {
    				array[i].setPriority(p); //if symbols are equal, set priority
    			}
    			else {
    				if(array[i].getPriority() <= p) {
    					array[i].setPriority(array[i].getPriority() - 1); //For any item x with a priority <= v's priority, decrement x's priority by 1
    				}
    			}
    		}
    		
    		//perform necessary adjustment to ensure a valid priority queue after updating
    		for(int i = currentSize / 2; i > 0; i--) {
    			int child = 0;
    			Pair temp = array[i]; //create a temp Pair
    			for( ; i * 2 <= currentSize; i = child) {
    				child = i * 2;
    				if(child != currentSize && array[child + 1].getPriority() > array[child].getPriority()) {
    					child++;
    				}
    				if(array[child].getPriority() > temp.getPriority()) {
    					array[i] = array[child];
    				}
    				else {
    					break;
    				}
    			}
    			array[i] = temp;
    		}
    }
    
    /**
     * Checks whether an item exists with the corresponding priority.
     * @param v a value to be checked.
     * @param p a priority to be checked.
     * @return true if the PriorityQueue contains the given value and priority, false if it does not.
     */
    public boolean contains(T v, int p) {
    		boolean check = false;
    		for(int i = 1; i <= currentSize; i++) {
    			if(array[i].getKey().equals(v) && array[i].getPriority() == p){
    				check = true;
    			}
    		}
    		return check;
    }
    
    /**
     * Private helper method to print out all values in the PriorityQueue.
     */
    private void printAll() {
    		for(int i = 1; i <= currentSize; i++) {
    			System.out.println("Symbol: " + array[i].getKey() + " | " + "Priority: " + array[i].getPriority());
    		}
    }
    
    
	// make sure you implement all methods in PriorityQueue interface

	//------------------------------------
	// example test code... edit this as much as you want!
	// note: you might want to add a method like printPQ() for debugging purpose
	
	public static void main(String[] args){
		MyPriorityQueue<String> pq = new MyPriorityQueue<String>();
		
		if(pq.size()==0 && pq.remove()==null && !pq.contains("a", 4)) 	{
			System.out.println("Yay 1");
		}
		
		pq.insert("a",4);
		pq.insert("b",10);
		pq.insert("h",2);
		
		//helper function call
		//pq.printAll();
		
		if(pq.size()==3 && (pq.peek()).equals("b") && pq.contains("a", 4) && pq.contains("h", 2)
			&& pq.contains("b",10)) {
			System.out.println("Yay 2");
		}
		
		//helper function call
		//pq.printAll();

		if((pq.remove()).equals("b") && !pq.contains("b",10) & pq.size()==2 
			&& (pq.peek().equals("a")) ) {
			System.out.println("Yay 3");
		}
		
		//helper function call
		//pq.printAll();
		
		pq.insert("d",4);
		
		if ((pq.peek()).equals("d")) {System.out.println("Yay 4");}
		
		pq.insert("b",10);
		pq.insert("f",3);
		pq.updatePriority("a",3);
		if (pq.size() == 5 && pq.contains("a",3) && pq.contains("b",10) && pq.contains("d",4) 
			&& pq.contains("f",2) && pq.contains("h",1)) {
			System.out.println("Yay 5");
		}
		
		//helper function call
		//pq.printAll();


	}


}




