package Amazon;

import java.util.Stack;

public class StockPrice {
    public static int maxProfitGroup(int[] nums) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;

        // Right to left pass
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            int res = stack.isEmpty() ? n : stack.peek();
            count += Math.max(0, res - 1 - i);
            stack.push(i);
        }

        // Clear the stack for the next pass
        stack.clear();

        // Left to right pass
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            int res = stack.isEmpty() ? -1 : stack.peek();
            count += Math.max(0, i - (res + 1));
            stack.push(i);
        }

        // Add n to the count as per the original Python function
        return count + n;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 2}; // Example input
        int result = maxProfitGroup(nums);
        System.out.println(result);
    }
}
