


/*
 * FileName	MyLinkedList.java
 * Date		june 13 2020
 * Author	Karan Sahu
 * Email	kxs190007@utdallas.edu
 * Course	CS 3345.0U2 summer 2020
 * Version	1.0
 * Copyright 2020, all rights reserved
 *
 * Description
 *
 *     Assigment 1 linkedlist implementation with extra functions and the testing of those functions
 * 	
 * 	*/




/**
 * LinkedList class implements a doubly-linked list.
 */
import java.util.ArrayList;
import java.util.List;



public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }
    
    private void clear( )
    {
        doClear( );
    }
    
    /**
     * Change the size of this collection to zero.
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
        modCount++;
    }

    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Adds an item to this collection, at the end.
     * @param anyType any object.
     * @return true.
     */
    public boolean add( AnyType anyType )
    {
        add( size( ), anyType );   
        return true;         
    }
    
    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
        modCount++;
    }   
    
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        
        return p.data;
    }
    
    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /////////////////////////////////// Project 1 new additions /////////////////////////////////////////////////////////////////

   // a.  itemCount
   // receives a value and returns a count of the number of times this item
   // is found in the list.
   public int itemCount( AnyType x )
   {
    int itemcount=0;
    for( int i = 0; i < size(); i++ ){

        AnyType nodeValue = get( i );
        if(nodeValue == x)
            itemcount++;
    }
       return itemcount;
   }
///////////////////////////////////////////////////////////////////////////////////////////////
    // b.  swap
    // receives two index positions as parameters and swaps the two nodes 
    // (the nodes, not just the values inside) at these positions, provided 
    // both positions are within the current size.
    public boolean swap( int index1, int index2)
    {
        if (size()-1 < index1 || index1 < 0|| size()-1 < index2 || index2 < 0 || index1 == index2)
        {
            return false;
        }
        AnyType data1 = get(index1);
        Node<AnyType> node1 = getNode( index1, 0, size( ) - 1 );
        Node<AnyType> node2 = getNode( index2, 0, size( ) - 1 );


        
        
        //swap data
        node1.data = node2.data;
        node2.data = data1;

        return true;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
// c.  sublist
// receives two indexes and returns an ArrayList of node values from the first
// index to the second index, provided the indexes are valid.

public ArrayList<AnyType> sublist( int index1, int index2)
{

    ArrayList<AnyType> sublistholder = new ArrayList<AnyType>();
    if(index1 == index2 || index2>= size() || index1 <0 )
    {
        return (sublistholder);
    }
    for( int i = index1; i < index2+1; i++ ){

        AnyType nodeValue = get( i );
        sublistholder.add(nodeValue);
    }


    return (sublistholder);
}


    /////////////////////////////////////////////////////////////////////////////////////////////
    // d.  select
    // receives a variable number of indexes, and returns an ArrayList of node values
    // corresponding to each index given, provided the indexes are valid.

    public ArrayList<AnyType> select(int... index)
{

    ArrayList<AnyType> sublistholder = new ArrayList<AnyType>();

    for( int i = 0; i < index.length; i++ ){

        AnyType nodeValue = get( index[i] );
        sublistholder.add(nodeValue);
    }


    return (sublistholder);
}

/////////////////////////////////////////////////////////////////////////////////////////////////
// e.  reverse
// returns a new MyLinkedList that has the elements in reverse order.

public MyLinkedList<AnyType> reverse()
{

    MyLinkedList<AnyType> reverselst = new MyLinkedList<>( );
    for( int i = 0; i< size(); i++ ){
        int j = size()-1;
        j = j - i;
        reverselst.add(get(j));

    }
    return (reverselst);
}

///////////////////////////////////////////////////////////////////////////////////////////////
// f.  erase 
// receives an index position and number of elements as parameters, and
// removes elements beginning at the index position for the number of 
// elements specified, provided the index position is within the size
// and together with the number of elements does not exceed the size.

public void erase( int index, int erasenum)
{

    for( int i =0 ; i < erasenum; i++ ){
        index = index ;
        remove(index);

    }


}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
// g.  insertList
// receives a List and an index position as parameters, and copies all of the 
// passed list into the existing list at the position specified by the parameter,
// provided the index position does not exceed the size.

public void insertList( List<AnyType> insertlist, int index)
{

for(int i=0; i<insertlist.size();i++)
{
    addBefore(getNode(index), insertlist.get(i));
    index++;
}
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// h.  shift
// receives an integer and shifts the list this many nodes forward or backward,
// for example, if passed 2, the first two nodes move to the tail, or if 
// passed -3, the last three nodes move to the front.  
   
//       +2:  abcde -> cdeab       -3:  abcde ->  cdeab

public int shift(  int shiftnum)
{
    if(shiftnum >0)
    {
        for(int i=0; i<shiftnum;i++)
        {
            add(get(0));
            remove(0);
            
        }
    return 1;
    }
    if (shiftnum<0)
    {
        shiftnum= shiftnum*-1;
        for(int i=0; i<shiftnum;i++)
        {
            addBefore(getNode(0), get(size()-1));
            remove(size()-1);
            
        }
    return -1;   
    }
    return 0;
}
///////////////////////////////////////////////////////////////////////////////////////////////////////




    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;       
        }
    }
    
    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );

        for( int i = 0; i < 10; i++ )
                lst.add( i );
        for( int i = 20; i < 30; i++ )
                lst.add( 0, i );

        
        /////////Test Item count///////
        System.out.println( "_________________________    Testing Item count   ___________________________________________\n\n" );
        lst.add(7);
        System.out.println( lst );
        System.out.println( "Testing Item count function for 7 should be 2. Itemcount return value: " + lst.itemCount(7));
        if(lst.itemCount(7)==2)
        System.out.println( "ItemCount is working properly!\n");
        System.out.println( "_____________________________________________________________________________________________\n\n" );
        /////////////////////////////////////

        ///////////Test swap//////////////////
        System.out.println( "_________________________    Testing swap        ___________________________________________\n\n" );
        lst.swap(2, 18);
        System.out.println( lst );
        System.out.println( "If index value 2 and 18 were swapped then then the swap function was successfull!\n");
        System.out.println( "_____________________________________________________________________________________________\n\n" );
        /////////////////////////////////////////////////

        ////////// Test sublist ///////////////////////////////////

        System.out.println( "_________________________    Testing sublist       ___________________________________________\n\n" );
        ArrayList<Integer> sublisttemparray = new ArrayList<Integer>();
            sublisttemparray = lst.sublist(2, 18);
            System.out.println("Linked list: "+ lst );
            System.out.println( "Array sublist index 2-18: " + sublisttemparray );
            System.out.println("If 2-18 was printed sublist function was successfull!\n" );
            System.out.println( "_____________________________________________________________________________________________\n\n" );


        ////////////////////////////////////////////////////////////

        ////////// Test select ///////////////////////////////////

        System.out.println( "_________________________    Testing select      ___________________________________________\n\n" );
        sublisttemparray.clear();
            sublisttemparray = lst.select(2, 18, 0, 19, 10);
            System.out.println("Linked list: "+ lst );
            System.out.println( "Array select index 2, 18, 0, 19, 10: " + sublisttemparray );
            System.out.println("If 2, 18, 0, 19, 10 was printed sublist function was successfull!\n" );
            System.out.println( "_____________________________________________________________________________________________\n\n" );


        ////////////////////////////////////////////////////////////

        ////////////////// Test reverse ////////////////////////////////
        System.out.println( "_________________________    Testing reverse     ___________________________________________\n\n" );
        MyLinkedList<Integer> reversetst = lst.reverse();
        System.out.println("Linked list: "+ lst );
        System.out.println("Reversed Linked list: "+ reversetst + "\n");
        System.out.println( "_____________________________________________________________________________________________\n\n" );
        //////////////////////////////////////////////////////////////////

        /////////////////// Test erase /////////////////////////////////
        System.out.println( "_________________________    Testing erase       ___________________________________________\n\n" );
        System.out.println("Original Linked list: "+ reversetst );
        reversetst.erase(1, 5);
        System.out.println("Erased Linked list:   "+ reversetst );
        System.out.println("If index 1-5 are erased this was successfull\n");
        System.out.println( "_____________________________________________________________________________________________\n\n" );
        ///////////////////////////////////////////////////////////////

        /////////////////// Test insertList ////////////////////////////////////////
        System.out.println( "_________________________    Testing insert list   ___________________________________________\n\n" );
        List<Integer> insertlst = new ArrayList<>( ); 
        insertlst.add(0);
        insertlst.add(1);
        insertlst.add(2);
        insertlst.add(3);
        System.out.println("Original Linked list: "+ reversetst );
        reversetst.insertList(insertlst, 4);
        System.out.println("Inserted @ index 4 (0-3) Linked list: "+ reversetst + "\n");
        System.out.println( "_____________________________________________________________________________________________\n\n" );
        ///////////////////////////////////////////////////////////////////////////////////

        /////////////////// Test shift ///////////////////////////////////////////////////
        System.out.println( "_________________________    Testing shift       ___________________________________________\n\n" );
        System.out.println("Original Linked list: "+ reversetst );
        reversetst.shift(4);
        System.out.println("Shift 4 (first 4 should be moved to last 4) Linked list: "+ reversetst + "\n");

        System.out.println("Original Linked list: "+ reversetst );
        reversetst.shift(-7);
        System.out.println("Shift -7 (last 7 should be moved to first 7) Linked list: "+ reversetst + "\n");
        System.out.println( "_____________________________________________________________________________________________\n\n" );

        ////////////////////////////////////////////////////////////////////////////////////

        System.out.println( "----------------ALL TESTS SEEM TO BE SUCCESSFULL!-------------\n\n" );


        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );

        System.out.println( lst );

        java.util.Iterator<Integer> itr = lst.iterator( );
        while( itr.hasNext( ) )
        {
                itr.next( );
                itr.remove( );
                System.out.println( lst );
        }
    }
}

/* Run output
_________________________    Testing Item count   ___________________________________________


[ 29 28 27 26 25 24 23 22 21 20 0 1 2 3 4 5 6 7 8 9 7 ]
Testing Item count function for 7 should be 2. Itemcount return value: 2
ItemCount is working properly!

_____________________________________________________________________________________________


_________________________    Testing swap        ___________________________________________


[ 29 28 8 26 25 24 23 22 21 20 0 1 2 3 4 5 6 7 27 9 7 ]
If index value 2 and 18 were swapped then then the swap function was successfull!

_____________________________________________________________________________________________


_________________________    Testing sublist       ___________________________________________


Linked list: [ 29 28 8 26 25 24 23 22 21 20 0 1 2 3 4 5 6 7 27 9 7 ]
Array sublist index 2-18: [8, 26, 25, 24, 23, 22, 21, 20, 0, 1, 2, 3, 4, 5, 6, 7, 27]
If 2-18 was printed sublist function was successfull!

_____________________________________________________________________________________________


_________________________    Testing select      ___________________________________________


Linked list: [ 29 28 8 26 25 24 23 22 21 20 0 1 2 3 4 5 6 7 27 9 7 ]
Array select index 2, 18, 0, 19, 10: [8, 27, 29, 9, 0]
If 2, 18, 0, 19, 10 was printed sublist function was successfull!

_____________________________________________________________________________________________


_________________________    Testing reverse     ___________________________________________


Linked list: [ 29 28 8 26 25 24 23 22 21 20 0 1 2 3 4 5 6 7 27 9 7 ]
Reversed Linked list: [ 7 9 27 7 6 5 4 3 2 1 0 20 21 22 23 24 25 26 8 28 29 ]

_____________________________________________________________________________________________


_________________________    Testing erase       ___________________________________________


Original Linked list: [ 7 9 27 7 6 5 4 3 2 1 0 20 21 22 23 24 25 26 8 28 29 ]
Erased Linked list:   [ 7 4 3 2 1 0 20 21 22 23 24 25 26 8 28 29 ]
If index 1-5 are erased this was successfull

_____________________________________________________________________________________________


_________________________    Testing insert list   ___________________________________________


Original Linked list: [ 7 4 3 2 1 0 20 21 22 23 24 25 26 8 28 29 ]
Inserted @ index 4 (0-3) Linked list: [ 7 4 3 2 0 1 2 3 1 0 20 21 22 23 24 25 26 8 28 29 ]

_____________________________________________________________________________________________


_________________________    Testing shift       ___________________________________________


Original Linked list: [ 7 4 3 2 0 1 2 3 1 0 20 21 22 23 24 25 26 8 28 29 ]
Shift 4 (first 4 should be moved to last 4) Linked list: [ 0 1 2 3 1 0 20 21 22 23 24 25 26 8 28 29 7 4 3 2 ]

Original Linked list: [ 0 1 2 3 1 0 20 21 22 23 24 25 26 8 28 29 7 4 3 2 ]
Shift -7 (last 7 should be moved to first 7) Linked list: [ 8 28 29 7 4 3 2 0 1 2 3 1 0 20 21 22 23 24 25 26 ]

_____________________________________________________________________________________________


----------------ALL TESTS SEEM TO BE SUCCESSFULL!-------------


[ 28 8 26 25 24 23 22 21 20 0 1 2 3 4 5 6 7 27 9 ]
[ 8 26 25 24 23 22 21 20 0 1 2 3 4 5 6 7 27 9 ]
[ 26 25 24 23 22 21 20 0 1 2 3 4 5 6 7 27 9 ]
[ 25 24 23 22 21 20 0 1 2 3 4 5 6 7 27 9 ]
[ 24 23 22 21 20 0 1 2 3 4 5 6 7 27 9 ]
[ 23 22 21 20 0 1 2 3 4 5 6 7 27 9 ]
[ 22 21 20 0 1 2 3 4 5 6 7 27 9 ]
[ 21 20 0 1 2 3 4 5 6 7 27 9 ]
[ 20 0 1 2 3 4 5 6 7 27 9 ]
[ 0 1 2 3 4 5 6 7 27 9 ]
[ 1 2 3 4 5 6 7 27 9 ]
[ 2 3 4 5 6 7 27 9 ]
[ 3 4 5 6 7 27 9 ]
[ 4 5 6 7 27 9 ]
[ 5 6 7 27 9 ]
[ 6 7 27 9 ]
[ 7 27 9 ]
[ 27 9 ]
[ 9 ]
[ ]
*/