package Amazon;

public class HashApproach {
    public static int numberOfWaysToReverse(String s, int k) {
        int count = 0;
        for (int i = 0; i <= s.length() - k; i++) {
            int left = i;
            int right = i + k - 1;
            boolean canReverse = false;

            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    // If the characters at the left and right pointers are different,
                    // check if reversing would make the substring lexicographically smaller.
                    if (s.charAt(left) > s.charAt(right)) {
                        canReverse = true;
                    }
                    break; // No need to check the rest of the characters.
                }
                left++;
                right--;
            }

            if (canReverse) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "amazon";
        int k = 3;
        int ways = numberOfWaysToReverse(s, k);
        System.out.println("Number of ways to reverse a substring of length " + k +
                " to make it lexicographically smaller: " + ways);
    }
}
