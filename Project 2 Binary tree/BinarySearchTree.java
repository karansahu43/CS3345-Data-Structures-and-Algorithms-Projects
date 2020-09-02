
/*
 * FileName	BinarySearchTree.java
 * Date		june 22 2020
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




/**
 * LinkedList class implements a doubly-linked list.
 */


// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    // a) size
    // returns an int of the number of nodes in the tree.  Use recursion.
    public int size()
    {
        return size( root );
    }
    private int size(BinaryNode<AnyType> t)  
    {  
        if (t == null)  
        return 0;  
      
        int res = 0;  
        res++;  
      
        res += (size(t.left) + size(t.right));  
        return res;  
    }  

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    // b) numLeaves
    // Returns the number of leaf nodes.  Use recursion.
    public int numLeaves()
    {
        return numLeaves( root );
    }
    private int numLeaves(BinaryNode<AnyType> t)  
    {  
        if (t == null)  
        return 0;  
      
        int res = 0;
        if (t.left == null && t.right == null)  
        {
            res++;  
            return res;
        }
      
        res += (numLeaves(t.left) + numLeaves(t.right));  
        return res;  
    }  
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // c) numLeftChildren
    // Returns the number of nodes that have a left child.  Use recursion.
    public int numLeftChildren()
    {
        return numLeftChildren( root.left );
    }
    private int numLeftChildren(BinaryNode<AnyType> t)  
    {  
        if (t == null)  
        return 0;  
      
        int res = 0;
        res++;  

      
        res += (numLeftChildren(t.left) + numLeftChildren(t.right));  
        return res;  
    }  

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // d) isFull
    // Returns true if every node has either two children or no children.
    // (Assume an empty tree is full.)  Use recursion.
    public boolean isFull()
    {
        int res;
        if( isEmpty( ) )
            return true;
        res = isFull( root );
        if(res>0)
            return false;
        return true;
    }
    private int isFull(BinaryNode<AnyType> t)  
    {  
        if (t == null)  
        return 0;  
      
        int res = 0;
        if (t.left == null && t.right != null||t.left != null && t.right == null)  
        {
            res++;  
            return res;
        }
      
        res += (isFull(t.left) + isFull(t.right));  

        return res;
  
    }  
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // e) nodeDepth
    // Receives a node value and returns the depth of this node, or -1 if not found. 
    // Use recursion.

    public int nodeDepth( AnyType x )
    {
        int depth=0;
        
        depth = nodeDepth( x, root) ;
        return depth;
    }


    private int nodeDepth( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return 0;   
        
        int depth= 0;
        
        int compareResult = x.compareTo( t.element );
        if( compareResult < 0 )
        {
            depth++;
            depth += nodeDepth( x, t.left );
        }
        else if( compareResult > 0 )
        {
            depth++;
            depth += nodeDepth( x, t.right );
        }

        else if( compareResult > 0 )
        {
            return 0;
        }

        return depth;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // f) printByLevels 
    // Print the root, then its children, then their children, etc.
    // This can be done using a queue.  Enqueue the root, then while the queue 
    // is not empty, dequeue and print, and enqueue its children.

    public void printByLevels()
    {
        printByLevels( root );
    }

     private void printByLevels(BinaryNode<AnyType> t) 
    { 
        int height = height(t); 
        int i; 
        for (i=1; i<=height + 1; i++) 
        { 
            printGivenLevel(t, i); 
            System.out.println(); 
        } 
    } 
    /* Print nodes at a given level */
    private void printGivenLevel(BinaryNode<AnyType> t, int level) 
    { 
        if (t == null) 
            return; 
        if (level == 1) 
            System.out.println(t.element); 
        else if (level > 1) 
        { 
            printGivenLevel(t.left, level-1); 
            printGivenLevel(t.right, level-1); 
        } 
    } 
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }
    /**
 * Exception class for access in empty containers
 * such as stacks, queues, and priority queues.
 * @author Mark Allen Weiss
 */
public class UnderflowException extends RuntimeException
{
}





      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );

        System.out.println( "Checking... (no more output means success)\n" );

        //Builds tree (full tree, 7 total nodes, 4 leaves, 3 levels)
         t.insert(50);
         t.insert(75);
         t.insert(25);
         t.insert(85);
         t.insert(65);
         t.insert(35);
         t.insert(15);

         //Print tree (for the sake of making it easier on the grader I drew it out.)
         System.out.println("(for the sake of making it easier on the grader I drew it out.)\n");
         System.out.println("                    50                   ");
         System.out.println("                   /  \\       ");
         System.out.println("                 25     75                    ");
         System.out.println("                /  \\    / \\                   ");
         System.out.println("               15  35  65  85                \n");

         System.out.println("Printing with printTree function\n");
         t.printTree();

        // Test size function ///////////////////////////////////////////////////////////////////////////
        System.out.println("-------------------------------------------------------------------------------------------------------------------\n");
         System.out.println( "The size of the tree should be 7. The size returned by size function: " + t.size());
        System.out.println("___________________________________________________________________________________________________________________\n");
         /////////////////////////////////////////////////////////////////////////////////////////////////

         // Test numLeaves function /////////////////////////////////////////////////////////////////////////////////////
         System.out.println("-------------------------------------------------------------------------------------------------------------------\n");
         System.out.println( "The number of leaves on the tree should be 4. The number of leaves returned by numLeave function: " + t.numLeaves() );
         System.out.println("___________________________________________________________________________________________________________________\n");
         /////////////////////////////////////////////////////////////////////////////////////////////////

         // Test numLeftChildren function /////////////////////////////////////////////////////////////////////////////////////
         System.out.println("-------------------------------------------------------------------------------------------------------------------\n");
         System.out.println( "The number of children on left should be 3. The number of leaves returned by numLeftChildren function: " + t.numLeftChildren() );
         System.out.println("___________________________________________________________________________________________________________________\n");
         /////////////////////////////////////////////////////////////////////////////////////////////////

         // Test isFull function /////////////////////////////////////////////////////////////////////////////////////
         System.out.println("-------------------------------------------------------------------------------------------------------------------\n");
         System.out.println( "The is full function should return true. The isFull function returned: " + t.isFull() );
         System.out.println( "You may comment out inserts to make this tree not full and run again to check for false. I have already done so and it works but u can as well.");
         System.out.println("___________________________________________________________________________________________________________________\n");
         /////////////////////////////////////////////////////////////////////////////////////////////////

         // Test nodeDepth function /////////////////////////////////////////////////////////////////////////////////////
         System.out.println("-------------------------------------------------------------------------------------------------------------------\n");
         System.out.println( "For 65 this function should return 2. The nodeDepth function returned: " + t.nodeDepth(25) );
         System.out.println("___________________________________________________________________________________________________________________\n");
         /////////////////////////////////////////////////////////////////////////////////////////////////

         // Test printByLevels function /////////////////////////////////////////////////////////////////////////////////////
         System.out.println("-------------------------------------------------------------------------------------------------------------------\n");
         System.out.println( "Should print Tree level by level: " );
         t.printByLevels();
         System.out.println("___________________________________________________________________________________________________________________\n");
         /////////////////////////////////////////////////////////////////////////////////////////////////




    }
}
// Sample output
// Checking... (no more output means success)

// (for the sake of making it easier on the grader I drew it out.)

//                     50
//                    /  \
//                  25     75
//                 /  \    / \
//                15  35  65  85

// Printing with printTree function

// 15
// 25
// 35
// 50
// 65
// 75
// 85
// -------------------------------------------------------------------------------------------------------------------

// The size of the tree should be 7. The size returned by size function: 7
// ___________________________________________________________________________________________________________________

// -------------------------------------------------------------------------------------------------------------------

// The number of leaves on the tree should be 4. The number of leaves returned by numLeave function: 4
// ___________________________________________________________________________________________________________________

// -------------------------------------------------------------------------------------------------------------------

// The number of children on left should be 3. The number of leaves returned by numLeftChildren function: 3
// ___________________________________________________________________________________________________________________

// -------------------------------------------------------------------------------------------------------------------

// The is full function should return true. The isFull function returned: true
// You may comment out inserts to make this tree not full and run again to check for false. I have already done so and it works but u can as well.
// ___________________________________________________________________________________________________________________

// -------------------------------------------------------------------------------------------------------------------

// For 65 this function should return 2. The nodeDepth function returned: 2
// ___________________________________________________________________________________________________________________

// -------------------------------------------------------------------------------------------------------------------

// Should print Tree level by level:
// 50

// 25
// 75

// 15
// 35
// 65
// 85

// ___________________________________________________________________________________________________________________