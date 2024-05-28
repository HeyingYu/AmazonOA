package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Store {
    /**
     * 只需要从b里面找出最小值，然后把a里面小于等于b最小值的按从便宜到贵都买了，如果等于m，那就结束了，如果还不够m件，剩下的只需要全买b里最小值就可以了
     */


    static class Item implements Comparable<Item> {
        int cost;
        int increment;
        public Item(int cost, int increment) {
            this.cost = cost;
            this.increment = increment;
        }
        @Override
        public int compareTo(Item other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
    public static int getMinCost(int[] a, int[] b, int m) {
        if (a.length == 0) {
            return 0;
        }
        PriorityQueue<Item> minHeap = new PriorityQueue<>();
        for (int i = 0; i < a.length; i++) {
            minHeap.add(new Item(a[i], b[i]));
        }
        int result = 0;
        for (int i = 0; i < m; i++) {
            Item item = minHeap.poll();
            if (item != null) {
                result += item.cost;
                minHeap.add(new Item(item.cost + item.increment, item.increment));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] basePrices = {2, 1, 1}; // Example base prices
        int[] priceIncreases = {1, 2, 3}; // Corresponding price increases
        int itemsCount = 4; // Number of items to purchase //7

        int minCost = getMinCost(basePrices, priceIncreases, itemsCount);
        System.out.println("The minimum cost is: " + minCost);
    }
}


