package Amazon;

public class HowManyFlips {

    public static int minFlips(String target) {
        int flips = 0;
        char currentState = '0';

        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) != currentState) {
                flips++;
                currentState = currentState == '0' ? '1' : '0';
            }
        }

        return flips;
    }

    public static void main(String[] args) {
        String target = "1010"; //4
        System.out.println("Minimum number of flips: " + minFlips(target));
    }


}
