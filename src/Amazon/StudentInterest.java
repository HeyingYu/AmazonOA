package Amazon;

public class StudentInterest {
    public static int maxSubarrayLength(int[] badges) {
        int maxLength = 0;
        int negativeCount = 0;
        int firstNegativeIndex = -1;
        int zeroIndex = -1;

        for (int i = 0, start = 0; i < badges.length; i++) {
            if (badges[i] == 0) {
                negativeCount = 0;
                firstNegativeIndex = -1;
                start = i + 1;
                zeroIndex = i;
            } else if (badges[i] == -1) {
                negativeCount++;
                if (firstNegativeIndex == -1) {
                    firstNegativeIndex = i;
                }
            }

            // If the number of negative numbers is even, then the subarray product is 1
            if (negativeCount % 2 == 0) {
                maxLength = Math.max(maxLength, i - start + 1);
            } else {
                // If the number of negative numbers is odd, exclude the first negative number
                if (firstNegativeIndex > -1) {
                    maxLength = Math.max(maxLength, i - firstNegativeIndex);
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] badges = {1, -1, -1, -1, 1, 1};
        System.out.println("Maximum subarray length with product of 1: " + maxSubarrayLength(badges));
    }
}
