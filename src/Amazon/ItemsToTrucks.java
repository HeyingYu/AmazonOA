package Amazon;

import java.util.*;


public class ItemsToTrucks {

    public static void main(String[] args) {
        int[] trucks1 = {4, 5, 7, 2};
        int[] items1 = {1, 2, 5};
        int[] trucks = {1, 3, 5, 2, 3, 2};
        int[] items = {1, 2, 3};
        int[] result = getTrucksForItems(trucks, items);

        System.out.println(Arrays.toString(result));
    }

    public static int[] getTrucksForItems(int[] trucks, int[] items) {
        // TreeMap to keep track of trucks, with the capacity as the value and index as the key
        TreeMap<Integer, NavigableSet<Integer>> truckIndicesByCapacity = new TreeMap<>();

        // Initialize the TreeMap
        for (int i = 0; i < trucks.length; i++) {
            truckIndicesByCapacity.putIfAbsent(trucks[i], new TreeSet<>());
            truckIndicesByCapacity.get(trucks[i]).add(i);
        }

        int[] result = new int[items.length];

        // Assign trucks to items
        for (int i = 0; i < items.length; i++) {
            // Find the set of truck indices with the next higher capacity than the current item
            Map.Entry<Integer, NavigableSet<Integer>> entry = truckIndicesByCapacity.higherEntry(items[i]);

            // If a suitable set of trucks is found, take the truck with the smallest index
            result[i] = (entry != null && !entry.getValue().isEmpty()) ? entry.getValue().first() : -1;
        }

        return result;
    }
}

