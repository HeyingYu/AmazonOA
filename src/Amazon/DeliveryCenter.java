package Amazon;

import java.util.Arrays;

public class DeliveryCenter {
    // Method to calculate the total distance from a given point to all centers
    // binary search - otherwise TLE, make sure find correct boundary, code below not right 100%
    public static int totalDistance(int x, int[] centers) {
        int total = 0;
        for (int center : centers) {
            total += 2 * Math.abs(center - x);
        }
        return total;
    }
    public static int findSuitableLocations(int[] centers, int d) {
        Arrays.sort(centers);
        int n = centers.length;

        // Find the median of the centers
        int medianIndex = n / 2;
        int median = centers[medianIndex];

        // Expand outwards from the median to find suitable locations
        int left = median, right = median;
        while (totalDistance(left, centers) <= d) {
            left--;
        }
        while (totalDistance(right, centers) <= d) {
            right++;
        }

        // The suitable locations are between left+1 and right-1 (inclusive)
        return Math.max(0, right - left - 1);
    }

    public static void main(String[] args) {
        int[] centers = {-253230108}; // Example centers
        int d = 1837848; // Example total distance constraint
        int result = findSuitableLocations(centers, d);
        System.out.println("Number of suitable locations: " + result);
    }
}
