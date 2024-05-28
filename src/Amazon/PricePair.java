package Amazon;

import java.util.HashMap;
import java.util.Map;

public class PricePair {
    public static int getDiscountPairs(int x, int[] prices) {
        Map<Integer, Integer> remainderCount = new HashMap<>();
        int count = 0;

        for (int price : prices) {
            int remainder = price % x;
            int complement = (x - remainder) % x; // To handle the case when remainder is 0

            // Check if there's a complement that forms a pair with current price
            count += remainderCount.getOrDefault(complement, 0);

            // Update the count for the current remainder
            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }

        return count;
    }
    public static void main(String[] args) {
        int x = 60;
        int[] prices = {31, 25, 85, 29, 35};
        System.out.println("Number of Discount Pairs: " + getDiscountPairs(x, prices));
    }
}
