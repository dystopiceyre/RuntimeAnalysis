/*
Olivia Ringhiser
1/31/2020
EmpiricalAnalysis.java
Finds the average time it takes for each sorting algorithm to run
for various input sizes and writes the results to an output file
*/

package greenriver.dev.runtimes;

import greenriver.dev.FindDuplicates;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

/**
 * Creates pseudo-random arrays of various sizes,
 * finds the average time to run various algorithms,
 * and writes the output to a file
 *
 * @author Olivia Ringhiser
 * @version 1.0
 */
public class EmpiricalAnalysis {
    private static final int NANOS_IN_SECOND = 1000000000;
    /*I changed this number as the algorithms demanded
     * For the relatively quicker algorithms (A, C, D) I ran
     * them 100 times for all sizes, but with E I ran
     * the algorithms 100 times for the smaller inputs, and 50 times
     * for the larger inputs, while with B I ran 100 times for the
     * smallest inputs, 50 times for medium inputs, and 25 times
     * for the largest inputs due to time concerns*/
    private static final int AVG = 100;

    /**
     * Fills an integer array of specified size with pseudo-randomly
     * generated integers
     *
     * @param numElements the size of the array
     * @return an array filled with pseudo-randomly generated integers
     */
    public int[] createRandomArray(int numElements) {
        int[] randArray = new int[numElements];
        //generates pseudo-random numbers between 0 and half of the arrays length
        Random rand = new Random(numElements / 2);
        for (int i = 0; i < randArray.length; i++) {
            //random int plus one, to ensure numbers are greater than 0
            randArray[i] = rand.nextInt() + 1;
        }
        return randArray;
    }

    /**
     * Runs the specified algorithm AVG times
     * Finds the average number of nanoseconds
     * it took to run, then converts it to seconds
     *
     * @param randArray array of random integers
     * @param algLetter Algorithm A, B, C, D, or E
     * @return average seconds elapsed since the method began running
     */
    public double avgSecondsElapsed(int[] randArray, String algLetter) {
        FindDuplicates findDuplicates = new FindDuplicates();
        double nanosElapsed = 0;
        for (int i = 1; i <= AVG; i++) {
            //finds current time
            LocalTime before = LocalTime.now();
            //chooses the specified algorithm to run
            switch (algLetter) {
                case "A":
                    findDuplicates.findDuplicatesA(randArray);
                    break;
                case "B":
                    findDuplicates.findDuplicatesB(randArray);
                    break;
                case "C":
                    findDuplicates.findDuplicatesC(randArray);
                    break;
                case "D":
                    findDuplicates.findDuplicatesD(randArray);
                    break;
                case "E":
                    findDuplicates.findDuplicatesE(randArray);
                    break;
            }
            //totals up the nano seconds passed since the algoritm was run
            nanosElapsed += Math.abs(ChronoUnit.NANOS.between(LocalTime.now(), before));
        }
        //finds the average nanoseconds
        nanosElapsed /= AVG;
        //returns as average seconds
        return nanosElapsed / NANOS_IN_SECOND;
    }

    /**
     * Writes to an outfile data pairs of the input (in # of elements)
     * and output (in average seconds)
     *
     * @param algLetter   file A, B, C, D, or E
     * @param numElements number of elements ranging from 10 to 20,000, in increments of 10,000
     * @param secsElapsed average seconds elapsed
     */
    public void writeToTextFile(String algLetter, int numElements, double secsElapsed) {
        try {
            FileWriter file = new FileWriter("out/production/RuntimeAnalysis/greenriver/dev/runtimes/" +
                    "results_" + algLetter + ".test", true);
            file.write(numElements + ", " + secsElapsed + "\n");
            System.out.println("writing to file " + algLetter);
            file.close();
        } catch (IOException err) {
            System.out.println("An error occurred while trying to print");
        }
    }
}
