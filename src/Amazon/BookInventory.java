package Amazon;

import java.util.HashMap;
import java.util.*;

public class BookInventory {
    /** time: O(n)
     * idea: If we record the current maximum copies of any book, then when adding a book, update the maximum copies if the book's copies are greater.
     *
     * It's more difficult when removing a book. How do we know the maximum copies will decrease or stay the same after removing a book? If the removed book doesn't have maximum copies, i.e. other books have maximum copies, or if the removed book has maximum copies, but other books also have maximum copies, then maximum copies will not change. If the removed book has maximum copies, and it is the only book that has maximum copies, then maximum copies will decrease.
     *
     * So the idea is to use two hash maps, one map the book id with copies, and the other one map copies to a set of book ids.
     *
     * When adding a book, update the current maximum copies if necessary. Also change the two maps accordingly.
     *
     * When removing a book, if the book is the only one has maximum copies, decrease maximum copies. Then update the two maps accordingly.
     */

    public static void main(String[] args) {
        int[] portalUpdate = {1, 2, -1, 2};
        for (int maxCopy : getMaxCopies(portalUpdate)) {
            System.out.println(maxCopy);
        }
    }

    public static List<Integer> getMaxCopies(int[] portalUpdate) { //2
        HashMap<Integer, Integer> bookIdCopiesMap = new HashMap<>();
        HashMap<Integer, Set<Integer>> copiesBookIdsMaps = new HashMap<>();
        int maxCopies = 0;
        List<Integer> res = new ArrayList<>();
        for (int update : portalUpdate) {
            int bookId;
            int currentCopies;
            if (update > 0) {
                bookId = update;
                currentCopies = bookIdCopiesMap.getOrDefault(bookId, 0);
                if (copiesBookIdsMaps.getOrDefault(currentCopies, new HashSet<>()).contains(bookId)) {
                    copiesBookIdsMaps.get(currentCopies).remove(bookId);
                }
                currentCopies ++;
                maxCopies = Math.max(maxCopies, currentCopies);
                bookIdCopiesMap.put(bookId, currentCopies);
                copiesBookIdsMaps.computeIfAbsent(currentCopies, k -> new HashSet<>()).add(bookId);
            } else {
                bookId = -update;
                currentCopies = bookIdCopiesMap.getOrDefault(bookId, 0);
                if (currentCopies == maxCopies && copiesBookIdsMaps.get(currentCopies).size() == 1) {
                    maxCopies --;
                }
                copiesBookIdsMaps.get(currentCopies).remove(bookId);
                currentCopies--;
                bookIdCopiesMap.put(bookId, currentCopies);
                copiesBookIdsMaps.computeIfAbsent(currentCopies, k -> new HashSet<>()).add(bookId);
            }
            res.add(maxCopies);
        }
        return res;
    }

    public static int[] getMaxCopies_2(int[] updates) { // provided
        Map<Integer, Integer> bookIdCopiesMap = new HashMap<>();
        Map<Integer, Set<Integer>> copiesBookIdsMap = new HashMap<>();
        int maxCopies = 0;
        int[] result = new int[updates.length];
        int i = 0;

        for (int update : updates) {
            int bookId = Math.abs(update);
            int currentCopies = bookIdCopiesMap.getOrDefault(bookId, 0);

            if (update > 0) {
                bookIdCopiesMap.put(bookId, currentCopies + 1);
                copiesBookIdsMap.computeIfAbsent(currentCopies + 1, k -> new HashSet<>()).add(bookId);
                maxCopies = Math.max(maxCopies, currentCopies + 1);
            } else {
                copiesBookIdsMap.get(currentCopies).remove(bookId);
                if (currentCopies == maxCopies && copiesBookIdsMap.get(currentCopies).isEmpty()) {
                    maxCopies--;
                }
                bookIdCopiesMap.put(bookId, currentCopies - 1);
                if (currentCopies - 1 > 0) {
                    copiesBookIdsMap.computeIfAbsent(currentCopies - 1, k -> new HashSet<>()).add(bookId);
                }
            }

            result[i++] = maxCopies;
        }

        return result;
    }
}
