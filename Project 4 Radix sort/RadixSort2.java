
/*
 * FileName	RadixSort.java
 * Date		july 13 2020
 * Author	Karan Sahu
 * Email	kxs190007@utdallas.edu
 * Course	CS 3345.0U2 summer 2020
 * Version	1.0
 * Copyright 2020, all rights reserved
 *
 * Description
 *
 *     Project 4 radix sort implementation
 * 	
 * 	*/




/**
 * LinkedList class implements a doubly-linked list.
 */

 
// Project #4

// Due Dates:  Tuesday, July 14 at 11:59pm 

// Submit:    eLearning

// Late Policy:  -10 points per hour late

// Instructions: This is an individual assignment.  All work should be your own.



// Objective:

//      Implement a sorting algorithm.


// Description:

//      Implement a radix sort in a Java class named RadixSort.java.
//      This should be your own work based on the example in the chapter 7 slides.
//      You may not use code from the textbook or other sources for this project.

//      Your program should receive its input from a file named "input.txt",
//      which contains one integer per line.

//      It should produce a sorted output file named "output.txt".

//      Include a main method which demonstrates that your algorithm works.


// Submit to eLearning:
//      RadixSort.java

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;  
import java.util.Scanner;

public class RadixSort {


    public static void main( String [ ] args ) throws FileNotFoundException
    {

        // Set up. Read file. Create array
        File fil = new File("/Users/karan/Documents/UTD summer 2021 classes/CS 3345/Projects/Project 4 Radix sort/input.txt");
        Scanner filereader = new Scanner(fil);
        Scanner numlinereader = new Scanner(fil);
        int i = 0;

        while (numlinereader.hasNext())
		{
            numlinereader.nextLine();
            i++;
		}
        numlinereader.close();
        int[] input = new int[i];
        i = 0;


        while (filereader.hasNext())
		{
            input[i++] = Integer.parseInt(filereader.nextLine());

		}
        
        filereader.close();
        // SET up done


        //Check if array was created properly
        System.out.println("Unsorted Array:");
        System.out.println(Arrays.toString(input));

        //Sort Array.
        radixSort(input);


        System.out.println("Sorted Array");
        System.out.println(Arrays.toString(input));

        // Create output file with sorted array
        System.out.println("Text file containing sorted array should be made under the name |output.txt|");
        Integer b;
        
        try {
            FileWriter w = new FileWriter("/Users/karan/Documents/UTD summer 2021 classes/CS 3345/Projects/Project 4 Radix sort/output.txt");
            for (Integer a : input) {

                w.write(a.toString() + "\n");
                
            }
            w.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }



    }



    //Radix sort

    public static void radixSort(int[] array) {
        int radixValue = 10;
        
        // Create 2d array bucket
        List<Integer>[] bucket = new ArrayList[radixValue];
        
        for (int i = 0; i < bucket.length; i++) {
          bucket[i] = new ArrayList<Integer>();
        }
    
        // Set up 
        
        int arrayValueHolder;
        int digitPointer = 1;
        int digitValueLimit = 1;

        // Radix Sort
        while (digitValueLimit != 0) {
          digitValueLimit = 0;      // reset digit checker
        
          // Start filling out each bucket based on the current digit its pointing at
          for (Integer i : array) {
            arrayValueHolder = i / digitPointer;
            bucket[arrayValueHolder % radixValue].add(i);
            if (arrayValueHolder > 0) {   //check if the last digit for all values has been reached
                digitValueLimit++;          // Increment each time a new digit is found
            }
          }
          
          // Put buckets into the same array after each digit evaluation
          int arrayIncrementer = 0;
          for (int j = 0; j < radixValue; j++) {
            for (Integer i : bucket[j]) {
              array[arrayIncrementer++] = i;
            }
            bucket[j].clear();
          }
          
          // move to next digit
          digitPointer = digitPointer *  radixValue;
        }
      }


    
}

// Sample output
// Unsorted Array:
// [40, 20, 80, 30, 10, 90, 60, 70, 50]
// Sorted Array
// [10, 20, 30, 40, 50, 60, 70, 80, 90]
// Text file containing sorted array should be made under the name |output.txt|
// Successfully wrote to the file.