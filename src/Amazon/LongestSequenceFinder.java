package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

public class LongestSequenceFinder {
    public static int findLongestSequence(int[] arr) {
        // A set to store unique elements since only unique elements can form a perfect set
        Set<Long> uniqueElements = new HashSet<>();
        for (int num : arr) {
            uniqueElements.add((long) num); // Cast to long to handle large values
        }

        int maxSize = 0; // Track the maximum size of the perfect set

        // Iterate over each unique element
        for (long num : uniqueElements) {
            int size = 0;
            // Check if we can form a sequence starting with the current element
            while (uniqueElements.contains(num)) {
                size++;
                // Check for overflow before squaring the number
                if (num <= Math.sqrt(Long.MAX_VALUE)) {
                    num *= num;
                } else {
                    break;
                }
            }
            // Update the maximum size if necessary
            maxSize = Math.max(maxSize, size);
        }

        // If we haven't found any sequence, return -1
        return maxSize >= 2 ? maxSize : -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 9, 4, 2, 16};
        int sequence = findLongestSequence(arr);
        System.out.println("Longest sequence: " + sequence);
    }
}
