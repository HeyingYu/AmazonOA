package Amazon;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

//28 //2 + 2 + 3  == 7
public class AmazonShopping {
    public static long getMaxRewardPoints(List<Integer> rewards) {
        long maxRewardPoints = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add all rewards to the max heap
        for (int reward : rewards) {
            maxHeap.add(reward);
        }

        // Process the heap until it's empty
        while (!maxHeap.isEmpty()) {
            int currentReward = maxHeap.poll();
            if (currentReward > 0) {
                maxRewardPoints += currentReward;
                // Add the reduced reward back to the heap if it's greater than 0
                if (currentReward - 1 > 0) {
                    maxHeap.add(currentReward - 1);
                }
            }
        }

        return maxRewardPoints;
    }
    public static void main(String[] args) {
        List<Integer> rewards = List.of(5, 2, 2, 3, 1);
        System.out.println("Maximum Reward Points: " + getMaxRewardPoints(rewards));
    }
}
