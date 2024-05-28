package Amazon;

public class ReviewScore {

    public static void main(String[] args) {
        String review = "GoodProductButScrapAfterWash";
        String[] prohibitedWords = {"crap", "odpro"};
        System.out.println(findReviewScore(review, prohibitedWords));
    }

    public static int findReviewScore(String review, String[] prohibitedWords) {
        // Normalize the review to lower case for case-insensitive matching
        String normalizedReview = review.toLowerCase();

        // Initialize the maximum score to zero
        int maxScore = 0;

        // Start from each character in the review to find the longest valid substring
        for (int i = 0; i < review.length(); i++) {
            // For each start position, try to extend the end position as far as possible
            int end = review.length();
            while (end > i) {
                String substring = normalizedReview.substring(i, end);
                boolean containsProhibited = false;
                for (String word : prohibitedWords) {
                    if (substring.contains(word.toLowerCase())) {
                        containsProhibited = true;
                        break;
                    }
                }
                if (!containsProhibited) {
                    maxScore = Math.max(maxScore, end - i);
                    break; // Found the longest substring from this start, break to try next start position
                }
                end--; // Reduce the end position to exclude the prohibited word
            }
        }
        return maxScore;
    }
}

