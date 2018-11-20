<h1 align="center"> OPT Cache Simulation </h1>

## Overview

Java-based simulator of how cache works using the OPT / optimal replacement policy. Unlike long-term storage spaces, cache consists of only a limited amount of space that consequently requires one of the addresses to be replaced with a newly accessed address that is not in the cache. If an address requested by a reference is already in cache, the reference is considered as a **cache hit**; otherwise, it is considered as cache miss. A new address will need to be added into the cache when there is a **cache miss**. If there is still unused space in cache, it is straightforward to add. If the cache is already full, we will have to select one existing address from cache to replace.



## MyPriorityQueue.java

Maintains a max priority queue where priority of a parent node is higher than the priority of its children. It should support operations to count the number of items (size), to return the max item (peek), to remove the max item (remove), to insert an item with its corresponding priority in to the queue (insert), to check whether there exists an item with a
priority (contains), and to update the priority of an item (updatePriority).

## MySymbolTable.java

Maintains the table for unique symbols (addresses) from a sequence of references. In the pair <SymbolType, RecordType>, SymbolType represents different symbols and RecordType defines the record of each symbol (address), in this case the last access time. The class supports operations to count how many symbols are present in the table (size), to
check whether a symbol is present in the table (hasSymbol), to find and return the record we store in table for a symbol (getRecord), to set the record for a symbol (putRecord), and to find and remove the entry of a symbol from the table (removeSymbol).

## MySequence.java

Stores a collection of values as a sorted sequence with no duplicates. Supports operations such as inserting a new value in to the collection (insert), removing a value from the collection (remove), finding if a value is present in the collection (contains), finding how many values are present in the collection (size), and finding a string representation of all
values in collection in ascending order (toStringAscendingOrder).

## StackDistCollector.java

Calculates the (LRU) stack distance for any given sequence of accesses.
