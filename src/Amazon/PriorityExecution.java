package Amazon;

import java.util.*;

public class PriorityExecution { //time: O(nlogn)
    public static List<Integer> getPrioritiesAfterExecution(List<Integer> priorities) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        Map<Integer, Integer> counter = new HashMap<>();
        Map<Integer, Deque<Integer>> priorityIndicesMap = new HashMap<>();

        for (int idx = 0; idx < priorities.size(); idx++) {
            int priority = priorities.get(idx);
            counter.put(priority, counter.getOrDefault(priority, 0) + 1);
            priorityIndicesMap.computeIfAbsent(priority, k -> new ArrayDeque<>()).addLast(idx);
        }

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getValue() > 1) {
                maxHeap.add(entry.getKey());
            }
        }

        while (!maxHeap.isEmpty()) {
            int maxSharedPriority = maxHeap.poll();
            if (maxSharedPriority == 0) {
                break;
            }

            if (counter.get(maxSharedPriority) < 2) {
                break;
            }
            int first = priorityIndicesMap.get(maxSharedPriority).pollFirst();
            int second = priorityIndicesMap.get(maxSharedPriority).pollFirst();
            counter.put(maxSharedPriority, counter.get(maxSharedPriority) - 2);

            priorities.set(first, -1);

            int newPriority = maxSharedPriority / 2;
            priorities.set(second, newPriority);
            counter.put(newPriority, counter.getOrDefault(newPriority, 0) + 1);
            priorityIndicesMap.computeIfAbsent(newPriority, k -> new ArrayDeque<>()).addLast(second);

            if (counter.get(newPriority) > 1) {
                maxHeap.add(newPriority);
            }

            if (counter.getOrDefault(maxSharedPriority, 0) > 1) {
                maxHeap.add(maxSharedPriority);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int priority : priorities) {
            if (priority != -1) {
                result.add(priority);
            }
        }
        return  result;
    }

    public static void main(String[] args) {
        List<Integer> priorities = Arrays.asList(6, 6, 6, 1, 2, 2);
        List<Integer> result = getPrioritiesAfterExecution(priorities);
        System.out.println(result);
    }
}
