package Amazon;

import java.util.List;

/**
 * find longest substring that matches a given regex
 */
public class StringMatchingLibrary {
    /**
     * @param text
     * @param regex
     * @return integer
     */
    public static int getLongestMatch(String text, String regex) {
        // Split the regex around the wildcard character '*'
        String[] parts = regex.split("\\*", -1);
        String start = parts[0];
        String end = parts[1];

        int longestMatch = -1;

        for (int i = 0; i <= text.length() - start.length(); i++) {
            if (text.startsWith(start, i)) {
                for (int j = text.length(); j >= i + start.length() + end.length(); j--) {
                    if (text.startsWith(end, j - end.length())) {
                        longestMatch = Math.max(longestMatch, j - i);
                        break; // Found the longest match for this start, no need to check shorter substrings
                    }
                }
            }
        }

        return longestMatch;
    }

    public static int getLongestMatch2(String text, String pattern) {
        if (pattern.length() == 1) { // pattern == "*"
            return text.length();
        }
        int startIndex = pattern.indexOf("*");
        if (startIndex < 0) {
            System.out.println("there is no * in the pattern");
            return -1;
        }
        String prePattern = pattern.substring(0, startIndex);
        String postPattern = pattern.substring(startIndex + 1);
        int index = -1;
        if (prePattern != null && prePattern.length() > 0) {
            index = text.indexOf(prePattern);
        } else {
            index = 0;
        }
        int lastIndex = -1;
        if (postPattern != null && postPattern.length() > 0) {
            lastIndex = text.lastIndexOf(postPattern);
        } else {
            lastIndex = text.length();
        }
        if (index < 0 || lastIndex < 0) {
            return -1;
        }
        if (index + prePattern.length() > lastIndex) { // a*a => a lastIndex = 0, index=0 len =1; aa lastIndex = 1, index=0, len = 1
            return -1;
        }
        return lastIndex + postPattern.length() - index;
    }

    public static void main(String[] args) {
        String text = "hackerrank";
        String regex = "ack*r";
        System.out.println(getLongestMatch2(text, regex));
    }
}
