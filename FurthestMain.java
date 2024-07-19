import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;


public class FurthestMain {
  public static void main(String[] args) {
    runner();
  }

  /*
   * 3 2 7 1 2 3 2 3 1 2
   * 
   */
  public static void runner() {
    Scanner kb = new Scanner(System.in);
    int numOfInstances = Integer.parseInt(kb.nextLine());
    for (int i = 0; i < numOfInstances; i++) {
      int cacheSize = Integer.parseInt(kb.nextLine());
      kb.nextLine(); // this gets the num of requests, but that value isn't super
                     // needed because the length of the line array is enough
      HashMap<Integer, Page> map = new HashMap<Integer, Page>();
      TreeMap<Page, Page> cache = new TreeMap<Page, Page>();
      Integer[] line = Arrays.stream(kb.nextLine().split(" ")).map(e -> Integer.parseInt(e))
          .toArray(size -> new Integer[size]);
      for (int j = 0; j < line.length; j++) {
        if (map.get(line[j]) == null) {
          map.put(line[j], new Page(line[j]));
        }
        map.get(line[j]).nextIndex.add(j);
      }
      // System.out.println(map);
      int numOfPageFaults = 0;
      // this is the main loop that will iterate through the line and check for page faults
      for (int j = 0; j < line.length; j++) {
        // if the page is already in the cache then the only thing that should happen is that the
        // pages next index should be incremented but that changes its ordering, so its removed from
        // the tree and added back with its new key
        if (cache.containsKey(map.get(line[j]))) {
          Page page = cache.remove(map.get(line[j]));
          page.nextIndex.poll(); // increments the page
          cache.put(page, page);
        }
        // this is for when there is a page fault
        else {
          numOfPageFaults++;
          // if the cache is not full yet, then we can just add the page to the cache without having
          // to deal with removing an item in FF but we do still have to increment the nextIndex
          // before its added
          if (cache.size() < cacheSize) {
            Page page = map.get(line[j]);
            page.nextIndex.poll();
            cache.put(page, page);
          }
          // this part is the one where we have to remove the item that is FF, if the items are both
          // null for their next index, then the tie is broken by pageNumber
          // the steps are to increment the next index of the page, then remove the last key in the
          // tree, and then add the page were currently on
          else {
            Page page = map.get(line[j]);
            page.nextIndex.poll();
            cache.remove(cache.lastKey());
            cache.put(page, page);
          }
        }
      }
      System.out.println(numOfPageFaults);

    }
    kb.close();
  }


}
