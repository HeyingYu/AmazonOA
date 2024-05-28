package Amazon;

public class RegexMatch {
    public static int getLongestMatch(String text, String regex) {
        String[] parts = regex.split("\\*", -1);
        String start = parts[0];
        String end = parts[1];
        int longestMatch = -1;

        for (int i = 0; i < text.length(); i++) {
            for (int j = i + start.length(); j <= text.length() - end.length(); j++) {
                if (text.substring(i, i + start.length()).equals(start) &&
                        text.substring(j, j + end.length()).equals(end)) {
                    longestMatch = Math.max(longestMatch, j + end.length() - i);
                }
            }
        }
        return longestMatch;
    }

    public static void main(String[] args) {
        String text = "hackerrank";
        String regex = "ack*r";
        System.out.println("Longest match length: " + getLongestMatch(text, regex));
    }
}


