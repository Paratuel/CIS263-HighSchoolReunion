
package package1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  
  /*****************************************************************
   * This program takes user input of a number of classmates, number 
   * of pairs that do not get along with each other, what those 
   * pairs are, and determines the number of tables needed to seat
   * all classmates while avoiding seating any pairs together. This
   * program also assigns which students sit at what tables
   * 
   * @author Patrick Howard Dishaw
   * @version 3rd April 2015
   *****************************************************************/
  public static void main(String[] args) {

    /**
    * Scanner for reading user input.
    */
    final Scanner input = new Scanner(System.in);

    /**
    * Integer for storing the number of classmates.
    */
    final int classmates;

    /**
    * Integer for storing the number of pairs.
    */
    final int pairs;

    /**
    * Integer for storing the first number in the pair.
    */
    int firstpair;

    /**
    * Integer for storing the second number in the pair.
    */
    int secondpair;

    /**
    * Integer array for storing all the pairs for output
    * for reference purposes and to double check user input.
    */
    final int[][] pairsArray;

    /**
    * Integer matrix making a truth table for the pairs.
    */
    final int[][] pairsMatrix;

    /**
    * Integer for storing the number of tables needed.
    */
    int tables;

    /**
    * Integer that counts classmates added to tableArrayList.
    */
    int classmateCount;

    /**
    * Integer ArrayList for storing who sits at what table to 
    * later be converted to a String and added to stringArray.
    */
    ArrayList<Integer> tableArrayList;

    /**
    * Integer ArrayList for storing who has already been assigned a seat.
    */
    final ArrayList<Integer> assignedClassmates;

    /**
    * String array for storing who sits at what table to print later.
    */
    final String[] stringArray;

    // initializes variables
    tables = 0;
    classmateCount = 0;
    tableArrayList = new ArrayList<Integer>();
    assignedClassmates = new ArrayList<Integer>();
    
    
    
    // takes user input for classmates, 
    // initializes pairsMatrix, stringArray
    System.out.print("Number of classmates: ");
    classmates = input.nextInt();
    pairsMatrix = new int[classmates][classmates];
    stringArray = new String[classmates];


    // takes user input for number of pairs
    // initializes pairsArray
    System.out.print("Number of pairs of persons that do not get along: ");
    pairs = input.nextInt();
    pairsArray = new int[2][pairs];

     // takes user input for pairs
     // enters data into pairsArray and pairsMatirx
    for (int a = 0; a < pairs; a++) {
      System.out.print("Pairs (x y): ");
      firstpair = input.nextInt();
      pairsArray[0][a] = firstpair;
      secondpair = input.nextInt();
      pairsArray[1][a] = secondpair;
      pairsMatrix[firstpair][secondpair] = 1;
      pairsMatrix[secondpair][firstpair] = 1;
    }

    input.close();

    // prints out number of classmates, number of pairs, and all pairs entered,
    // for reference purposes and to double check user input
    System.out.println("\nClassmates: " + classmates 
        + "\nPairs: " + pairs
        + "\nTHE HATE:");
    for (int a = 0; a < pairs; a++) {
      System.out.println(pairsArray[0][a] + " " + pairsArray[1][a]);
    }

    // prints out pairsMatrix, for reference purposes
    System.out.println("\nMatrix: ");
    for (int a = 0; a < classmates; a++) { 
      for (int b = 0; b < classmates; b++) {
        System.out.print(pairsMatrix[a][b] + " ");
      } 
      System.out.print("\n");
    }

    // prints out one half of pairsMatrix, for reference purposes
    System.out.println("\nHalf Matrix: ");
    for (int a = 0; a < (classmates - 1); a++) {
      for (int b = (a + 1); b < (classmates); b++) {
        System.out.print(pairsMatrix[b][a] + " ");
      }
      System.out.print("\n");
    }

    // initializing tableArrayList, assignedClassmates, and classmateCount
    

    // assigns classmates to tables based on user input
    for (int a = 0; a < (classmates); a++) {
      tableArrayList.add(a);
      assignedClassmates.add(a);
      classmateCount++;

     // catches if last classmate was added here
      if (classmateCount == classmates) {
        stringArray[a] = tableArrayList.toString();
        tableArrayList.clear();
        break;
      }

      
      for (int b = (a + 1); b < classmates; b++) {

        // case 0: classmate already has been assigned, loop continues
        if (assignedClassmates.contains(b)) {
          continue;
        }

        // case 1: classmates are not a pair
        if (pairsMatrix[b][a] == 0) {

        // case 0: only one other classmate at table, no need to compare
          if (tableArrayList.size() == 1) {
            tableArrayList.add(b);
            assignedClassmates.add(b);
            classmateCount++;
          } else {

            // case 1: compares current classmate the classmates assigned to the table
            for (int c = tableArrayList.size(); c > 1; c--) {

              // case 0: current classmate is paired with another classmate sitting, loop continues
              if (pairsMatrix[tableArrayList.get(c - 1)][b] == 1) {
                continue;
              } else {

                // case 1: no pairs found, classmate is added to the table
                tableArrayList.add(b);
                classmateCount++;
              }
            }
          }
        }
      }

      // converts ArrayList to String for stringArray, clears ArrayList
      stringArray[a] = tableArrayList.toString();
      tableArrayList.clear();

      // if all students are accounted for, breaks loop
      if (classmateCount == classmates) {
        break;
      }
    }

    // Determines the number of tables
    for (int a = 0; a < stringArray.length; a++) {
      if (stringArray[a] == null) {
        break;
      } else {
        tables++;
      }
    }

    // Final output of tables required and assigned seats
    System.out.print("\nTables required: " + tables);
    for (int a = 0; a < tables; a++) {
      System.out.print("\nTable " + a + " seats classmates: " + stringArray[a]);
    }
  }

}

