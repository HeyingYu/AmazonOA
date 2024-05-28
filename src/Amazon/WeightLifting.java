package Amazon;

import java.util.List;

public class WeightLifting {

    public static int countInversion(int[] arr) {
        return countInversionHelper(arr, 0, arr.length - 1);
    }
    public static int countInversionHelper(int[] arr, int l, int r) {
        int invCount = 0;
        if (l < r) {
            int m = l + (r - l) / 2;
            invCount += countInversionHelper(arr, l, m);
            invCount += countInversionHelper(arr, m + 1, r);
            invCount += mergeAndCount(arr, l, m, r);
        }
        return invCount;
    }
    private static int mergeAndCount(int[] arr, int l, int m, int r) {
        int[] left = new int[m - l + 1];
        int[] right = new int[r - m];

        for (int i = 0; i < left.length; i++)
            left[i] = arr[l + i];
        for (int j = 0; j < right.length; j++)
            right[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l, invCount = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                invCount += (m + 1) - (l + i);
                j++;
            }
            k++;
        }

        // Copy remaining elements of left, if any
        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }

        // Copy remaining elements of right, if any
        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }

        return invCount;
    }

    public static void main(String[] args) {
        // Example usage
        int[] plates = new int[]{3,2,1}; // Example list of plates
        int moves = countInversion(plates);
        System.out.println("Minimum moves required: " + moves);
    }
}
