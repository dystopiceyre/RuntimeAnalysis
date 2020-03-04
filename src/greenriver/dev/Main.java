/*
Olivia Ringhiser
1/31/2020
Main.java
Generates large integer arrays of pseudo-random input
and writes how long it takes to sort the array using
different algorithms to output files
*/

package greenriver.dev;

import greenriver.dev.runtimes.EmpiricalAnalysis;

import java.io.IOException;

/**
 * @author Olivia Ringhiser
 * @version 1.0
 * The main method which runs the program
 */
public class Main {

    public static final int LOWER_BOUND = 10000;
    public static final int UPPER_BOUND = 200000;

    /**
     * @param args command line arguments
     * @throws IOException in case there are issues with the file
     *
     * First assures all 5 algorithms find the same number of duplicates
     * Then for each of the algorithms, creates integer arrays of varying
     * sizes and finds the average amount of time it takes to run
     * The output is written to an out file
     */
    public static void main(String[] args) throws IOException {
        FindDuplicates findDup = new FindDuplicates();
        if (!findDup.allDuplicatesEqual()) {
            System.out.println("Not all duplicate methods return the same answer!");
            return;
        }
        EmpiricalAnalysis empAn = new EmpiricalAnalysis();
        //Can put any combo of the following letters in here
        String[] letters = {"A", "B", "C", "D", "E"};
        try {
            for (String letter : letters) {
                //create pseudo-random arrays in increments of 10000 elements
                for (int numElements = LOWER_BOUND; numElements <= UPPER_BOUND; numElements += LOWER_BOUND) {
                    //fill the array
                    int[] randArr = empAn.createRandomArray(numElements);
                    //calculate the average time to run
                    double secsElapsed = empAn.avgSecondsElapsed(randArr, letter);
                    //write to an output file
                    empAn.writeToTextFile(letter, numElements, secsElapsed);
                }
            }
            System.out.println("Completed successfully!");
        } catch (Exception err) {
            //catch any potential file errors
            System.out.println("File issues!");
        }
    }
}
