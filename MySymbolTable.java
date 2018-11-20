/**************************************************************************
 * @author Uzair Minhas
 * @version 5/1/18
 * CS310 Spring 2018
 * Project 4
 * George Mason University
 * 
 * File Name: MySymbolTable.java
 *
 * Description: A class that maintains a table for unique symbols (addresses) from a sequence of references.
 * 
 ***************************************************************************/

// your implementation of SymbolTable interface
// header comments here
import java.util.HashMap;

// import HashMap class
// SymbolType is the key and RecordType is the value
// SymbolType represents different symbols and RecordType defines the record of each symbol (address)

public class MySymbolTable<SymbolType, RecordType> implements SymbolTable<SymbolType, RecordType>{

	// you decide the internal design of your class:
	//  - it must implement the provided SymbolTable interface
	//  - it cannot have any other public members (attributes or method) other than
	//    the public constructor and the methods defined in SymbolTable interface
	
	// make sure you implement all methods in SymbolTable interface
	
	private HashMap<SymbolType, RecordType> myTable; //declare new hash map
	
	/**
	 * Constructs a new SymbolTable using HashMap.
	 */
	public MySymbolTable(){
		myTable = new HashMap<SymbolType, RecordType>();
	}
	
	/**
	 * Returns the number of key-value mappings in this SymbolTable.
	 * @return the number of key-value mappings in this SymbolTable.
	 */
	public int size() {
		return myTable.size();
	}
	
	/**
	 * Checks whether the SymbolTable contains a mapping for the specified key.
	 * @param s a SymbolType representing the key.
	 * @return true if the SymbolTable contains a mapping for the given key.
	 */
	public boolean hasSymbol(SymbolType s) {
		return myTable.containsKey(s);
	}
	
	/**
	 * Returns the value to which the specified key is mapped.
	 * @param s a SymbolType representing the key.
	 * @return the value to which the specified key is mapped, or null if the SymbolTable contains no mapping for the key.
	 */
	public RecordType getRecord(SymbolType s) {
		return myTable.get(s);
	}
	
	/**
	 * Associates the given value with the specified key in the SymbolTable.
	 * @param s a SymbolType representing the key.
	 * @param r a RecordType representing the value.
	 */
	public void putRecord(SymbolType s, RecordType r) {
		myTable.put(s, r);
	}
	
	/**
	 * Removes the mapping for the specified key from the SymbolTable, if present.
	 * @param s a SymbolType representing the key.
	 * @return a RecordType representing the value associated with the given key.
	 */
	public RecordType removeSymbol(SymbolType s) {
		return myTable.remove(s);
	}
	
	
	
	//------------------------------------
	// example test code... edit this as much as you want!
	public static void main(String[] args){
		MySymbolTable<String,Integer> table = new MySymbolTable<String,Integer>();
		
		if(table.size()==0 && !table.hasSymbol("a")) 	{
			System.out.println("Yay 1");
		}

		table.putRecord("a",new Integer(136));
		table.putRecord("b",new Integer(112));
		
		if(table.size()==2 && table.hasSymbol("a") && table.getRecord("b").equals(new Integer(112))) 	{
			System.out.println("Yay 2");
		}

		table.putRecord("b",new Integer(211));
		Integer v = table.removeSymbol("a");
		if(table.size()==1 && v.equals(new Integer(136)) && !table.hasSymbol("a") 
			&& table.getRecord("b").equals(new Integer(211))) 	{
			System.out.println("Yay 3");
		}
	
	}


}


