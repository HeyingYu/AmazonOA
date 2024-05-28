package Amazon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class ErasePair {
    public static String bestPairElim(String s) {
        HashMap<Character, Integer> counts = new HashMap<>();
        HashMap<Character, Integer> lastPos = new HashMap<>();
        Stack<Character> stack = new Stack<>();
        HashSet<Character> seen = new HashSet<>();
        // Count occurrences of each character
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        // Record the last position of each character
        for (int i = 0; i < s.length(); i++) {
            lastPos.put(s.charAt(i), i);
        }
        // Process characters to form the result string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (seen.contains(c)) {
                continue;
            }
            // Need to keep this character only if it occurs an odd number of times
            if (counts.get(c) % 2 != 0) {
                // Try to pop previous characters if possible and if it creates a lexicographically smaller result
                while (!stack.isEmpty() && stack.peek() > c && lastPos.get(stack.peek()) > i) {
                    seen.remove(stack.pop());
                }
                stack.push(c);
                seen.add(c);
            }
        }
        // Build the result string from the stack
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "AKFKFMOGKFB"; // Example input
        String result = bestPairElim(s);
        System.out.println("Resulting string: " + result);
    }
}
