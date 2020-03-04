/*
Olivia Ringhiser
1/31/2020
FindDuplicates.java
Five different array duplicate counting algorithms
*/

package greenriver.dev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Five different methods for counting the number of
 * duplicate elements in integer arrays
 *
 * @author Olivia Ringhiser
 * @version 1.0
 */
public class FindDuplicates {
    /**
     * Utilizes Arrays.sort(), checks adjacent elements for equal values
     *
     * @param input a randomly generated integer array
     * @return the number of duplicates found
     */
    public int findDuplicatesA(int[] input) {
        int duplicates = 0;
        Arrays.sort(input);
        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] == input[i + 1]) {
                duplicates++;
            }
        }
        return duplicates;
    }

    /**
     * If not already there, adds array elements to an ArrayList
     * Compares list size to original array length to find the
     * number of duplicates
     *
     * @param input a randomly generated integer array
     * @return the number of duplicates found
     */
    public int findDuplicatesB(int[] input) {
        ArrayList<Integer> nonDuplicates = new ArrayList<>();
        for (int value : input) {
            if (!nonDuplicates.contains(value)) {
                nonDuplicates.add(value);
            }
        }
        return input.length - nonDuplicates.size();
    }

    /**
     * Adds array elements to a TreeSet
     * Compares set size to input array length to find
     * the number of duplicates
     *
     * @param input a randomly generated integer array
     * @return the number of duplicates found
     */
    public int findDuplicatesC(int[] input) {
        TreeSet<Integer> nonDuplicates = new TreeSet<>();
        for (int value : input) {
            nonDuplicates.add(value);
        }
        return input.length - nonDuplicates.size();
    }

    /**
     * Adds array elements to a HashSet
     * Compares set size to input array length to find
     * the number of duplicates
     *
     * @param input a randomly generated integer array
     * @return the number of duplicates found
     */
    public int findDuplicatesD(int[] input) {
        HashSet<Integer> nonDuplicates = new HashSet<>();
        for (int value : input) {
            nonDuplicates.add(value);
        }
        return input.length - nonDuplicates.size();
    }

    /**
     * Utilizes a nested loop to find duplicates
     * The outer loop inspects each index
     * while the inner checks each index higher than
     * the current one to look for matching elements
     *
     * @param input a randomly generated integer array
     * @return the number of duplicates found
     */
    public int findDuplicatesE(int[] input) {
        int duplicates = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] == input[j]) {
                    duplicates++;
                    break;
                }
            }
        }
        return duplicates;
    }

    /**
     * Runs all five algorithms on the same input
     * to ensure that they all find the same number
     * of duplicates
     *
     * @return true if the integer value for all
     * methods is the same
     */
    public boolean allDuplicatesEqual() {
        int[] input = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5};
        int dupA = findDuplicatesA(input);
        int dupB = findDuplicatesB(input);
        int dupC = findDuplicatesC(input);
        int dupD = findDuplicatesD(input);
        int dupE = findDuplicatesE(input);
        return dupA == dupB && dupA == dupC && dupA == dupD && dupA == dupE;
    }
}
