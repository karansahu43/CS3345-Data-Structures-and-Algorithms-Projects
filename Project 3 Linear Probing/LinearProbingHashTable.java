
/*
 * FileName	LinearProbingHashTable.java
 * Date		july 4 2020
 * Author	Karan Sahu
 * Email	kxs190007@utdallas.edu
 * Course	CS 3345.0U2 summer 2020
 * Version	1.0
 * Copyright 2020, all rights reserved
 *
 * Description
 *
 *     Assigment 2 Binary Search tree implementation with extra functions and the testing of those functions
 * 	
 * 	*/




// LinearProbing Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// bool insert( x )       --> Insert x
// bool delete( x )       --> Remove x
// bool contains( x )     --> Return true if x is present
// void makeEmpty( )      --> Remove all items


/**
 * Probing table implementation of hash tables.
 * Note that all "matching" is based on the equals method.
 * @author Mark Allen Weiss
 */
public class LinearProbingHashTable<AnyType, V>
{
    /**
     * Construct the hash table.
     */
    public LinearProbingHashTable( )
    {
        this( DEFAULT_TABLE_SIZE );
    }

    /**
     * Construct the hash table.
     * @param size the approximate initial size.
     */
    public LinearProbingHashTable( int size )
    {
        allocateArray( size );
        doClear( );
    }

    // a) public boolean insert(K key, V value)
    // inserts entry, rehashes if half full,
    // can re-use deleted entries, throws
    // exception if key is null, returns
    // true if inserted, false if duplicate.
    public boolean insert( AnyType x, V v )
    {
            // Insert x as active
        int currentPos = getLocation( x );
        if( isActive( currentPos ) )
            return false;

        if( array[ currentPos ] == null )
            ++occupied;
        array[ currentPos ] = new HashEntry<>( x, v, true );
        theSize++;
        
            // Rehash; see Section 5.5
        if( occupied > array.length /2 )
            rehash( );
        
        return true;
    }

    // d) private void rehash( )
    // doubles the table size, hashes everything to
    // the new table, omitting items marked deleted
    private void rehash( )
    {
        HashEntry<AnyType, V> [ ] oldArray = array;

            // Create a new double-sized, empty table
        allocateArray( 2 * oldArray.length );
        occupied = 0;
        theSize = 0;

            // Copy table over
        for( HashEntry<AnyType, V> entry : oldArray )
            if( entry != null && entry.isActive )
                insert( entry.element, entry.value);
   }

//    f) public int getLocation(K key)
//           returns the location for the given key,
//           or -1 if not found.
//           (this is the value after probing occurs)
    private int getLocation( AnyType x )
    {
        int offset = 1;
        int currentPos = myhash( x );
        
        while( array[ currentPos ] != null &&
                !array[ currentPos ].element.equals( x ) )
        {
            currentPos += offset;  // Compute ith probe
            //offset += 2;
            if( currentPos >= array.length )
                currentPos -= array.length;
        }
        
        return currentPos;
    }



    // c) public boolean delete(K key)
    // marks the entry deleted but leaves it there,
    // returns true if deleted, false if not found
    public boolean delete( AnyType x )
    {
        int currentPos = getLocation( x );
        if( isActive( currentPos ) )
        {
            array[ currentPos ].isActive = false;
            theSize--;
            return true;
        }
        else
            return false;
    }
    
    /**
     * Get current size.
     * @return the size.
     */
    public int size( )
    {
        return theSize;
    }
    
    /**
     * Get length of internal table.
     * @return the size.
     */
    public int capacity( )
    {
        return array.length;
    }

    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return the matching item.
     */
    public boolean contains( AnyType x )
    {
        int currentPos = getLocation( x );
        return isActive( currentPos );
    }

    /**
     * Return true if currentPos exists and is active.
     * @param currentPos the result of a call to getLocation.
     * @return true if currentPos is active.
     */
    private boolean isActive( int currentPos )
    {
        return array[ currentPos ] != null && array[ currentPos ].isActive;
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty( )
    {
        doClear( );
    }

    private void doClear( )
    {
        occupied = 0;
        for( int i = 0; i < array.length; i++ )
            array[ i ] = null;
    }
    
    private int myhash( AnyType x )
    {
        int hashVal = x.hashCode( );

        hashVal %= array.length;
        if( hashVal < 0 )
            hashVal += array.length;

        return hashVal;
    }
    //-----------------------------------------------------------------------------------------------------
    // b) public V find(K key)
    // returns value for key, or null if not found
    public V find( AnyType x )
    {
        int offset = 1;
        int currentPos = myhash( x );
        
        while( array[ currentPos ] != null &&
                !array[ currentPos ].element.equals( x ) )
        {
            currentPos += offset;  // Compute ith probe
            //offset += 2;
            if( currentPos >= array.length )
                currentPos -= array.length;
        }
        
        return array[ currentPos ].value;
    }

    //--------------------------------------------------------------------------------------------------------------
    // e) public int getHashValue(K key)
    // returns the hash value for the given key.
    // (this is the value before probing occurs)
    public int getHashValue(AnyType x)
    {
        return myhash(x);
    }

    //--------------------------------------------------------------------------------------------------------------
    //    g) public String toString()
    //       returns a formatted string of the hash table,
    //       where k, v is the key and value at this location:
    //            0  k, v
    //            1  
    //            2  k, v   deleted
    //            ...
    public String toString()
    {
        String str = "";
        String deletecheck = "";
        int capacity = capacity();
        for (int i = 0; i < capacity; i++)
        {
            if(array[i]!=null)
            {
                if(array[i].isActive)
                    deletecheck = " ";
                if(array[i].isActive== false)
                deletecheck = "deleted";
                str = str + i + " " + array[i].element + ", " +  array[i].value + " " + deletecheck + "\n";
            }   
            else 
                str = str + i + "\n";



        }
        return str;
    }
    
    private static class HashEntry<AnyType, V>
    {
        public AnyType  element;   // the element
        public V value;
        public boolean isActive;  // false if marked deleted

        public HashEntry( AnyType e, V v )
        {
            this( e, v,  true );
        }

        public HashEntry( AnyType e, V v, boolean i )
        {
            element  = e;
            isActive = i;
            value = v;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 11;

    private HashEntry<AnyType, V> [ ] array; // The array of elements
    private int occupied;                 // The number of occupied cells
    private int theSize;                  // Current size

    /**
     * Internal method to allocate array.
     * @param arraySize the size of the array.
     */
    private void allocateArray( int arraySize )
    {
        array = new HashEntry[ nextPrime( arraySize ) ];
    }

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime( int n )
    {
        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime( int n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }


    // Simple main
    public static void main( String [ ] args )
    {

        LinearProbingHashTable<String, Integer> H = new LinearProbingHashTable<>( );

        

        H.insert( "Lion", 22);
        H.insert( "Jaguar", 432);
        H.insert("Wolf", 2342);
        H.insert( "Hyena", 11);
        H.insert( "pokemon",25253);
        System.out.println( "Testing insert Successfull" );
  
   

        System.out.println( "Testing Find Successfull: Find Lion:  " + H.find("Lion") );
        H.delete("Lion");
        System.out.println( "Delete Lion complete" );
 
        System.out.println( "Get Location of Wolf:   " + H.getLocation("Wolf") );
        System.out.println( "Get hashvalue of pokemon:   " + H.getHashValue("pokemon") );
        System.out.println( "Get hashvalue of Lion:   " + H.getHashValue("Lion") );
        System.out.println( "Get hashvalue of Jaguar:   " + H.getHashValue("Jaguar") );
        System.out.println( "Get hashvalue of Wolf:   " + H.getHashValue("Wolf") );

        System.out.println( H.toString() );

        H.insert( "Simba", 5254);
        H.insert( "Cheetah", 345345);

        System.out.println( "\n\n\nCheck rehashing" );
        System.out.println( H.toString() );



    }
  

}

// Example output

// Testing insert Successfull
// Testing Find Successfull: Find Lion:  22
// Delete Lion complete
// Get Location of Wolf:   10
// Get hashvalue of pokemon:   7
// Get hashvalue of Lion:   8
// Get hashvalue of Jaguar:   9
// Get hashvalue of Wolf:   8
// 0
// 1
// 2
// 3 Hyena, 11
// 4
// 5
// 6
// 7 pokemon, 25253
// 8 Lion, 22 deleted
// 9 Jaguar, 432
// 10 Wolf, 2342




// Check rehashing
// 0
// 1
// 2
// 3
// 4
// 5 Simba, 5254
// 6
// 7
// 8 Hyena, 11
// 9 pokemon, 25253
// 10
// 11
// 12 Jaguar, 432
// 13 Wolf, 2342
// 14 Cheetah, 345345
// 15
// 16
// 17
// 18
// 19
// 20
// 21
// 22
