package Amazon;

import java.util.*;
public class FruitCrush {
    public static int getMinimumFruits(int[] fruits) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int fruit : fruits) {
            pq.offer(fruit);
        }

        // While there are at least two fruits, try to crush them
        while (pq.size() > 1) {
            // Take out two fruits
            int fruit1 = pq.poll();
            int fruit2 = pq.poll();

            // If they are different, they get crushed and we do not add anything back to the queue
            // If they are the same, we need to add one of them back to the queue
            if (fruit1 == fruit2) {
                pq.offer(fruit1);
            }
        }

        // If there's one fruit left that couldn't be crushed, the size will be 1; otherwise, it will be 0.
        return pq.size();
    }

    public static void main(String[] args) {
        int[] fruits = {3, 3, 1, 1, 2}; //1
        System.out.println("Minimum number of fruits left: " + getMinimumFruits(fruits));
    }
}
