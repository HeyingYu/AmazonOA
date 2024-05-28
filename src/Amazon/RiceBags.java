package Amazon;

import java.util.*;

public class RiceBags {

    public static int maxSetSize2(int[] riceBags) {
        Arrays.sort(riceBags);
        int maxLen = -1;

        for (int i = riceBags.length - 1; i >= 0; i--) {
            int number = riceBags[i];
            List<Integer> riceSet = new ArrayList<>();
            riceSet.add(number);

            for (int j = i - 1; j >= 0; j--) {
                if (Math.sqrt(number) == riceBags[j]) {
                    riceSet.add(riceBags[j]);
                    number = riceBags[j];
                }
            }

            if (riceSet.size() < 2) {
                continue;
            }
            if (riceSet.size() > maxLen) {
                maxLen = riceSet.size();
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] riceBags = {625, 4, 2, 5, 25};
        System.out.println(maxSetSize2(riceBags));
    }
}

