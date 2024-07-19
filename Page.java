import java.util.LinkedList;
import java.util.Queue;

public class Page implements Comparable<Page> {
  public int pageNumber;
  public Queue<Integer> nextIndex = new LinkedList<Integer>();

  public Page(int p) {
    pageNumber = p;
  }

  @Override
  public int compareTo(Page o) {
    if (!this.nextIndex.isEmpty() && !o.nextIndex.isEmpty()) {
      return this.nextIndex.peek() - o.nextIndex.peek();
    } else if (!this.nextIndex.isEmpty() && o.nextIndex.isEmpty()) {
      return -1;
    } else if (this.nextIndex.isEmpty() && !o.nextIndex.isEmpty()) {
      return 1;
    } else {
      return this.pageNumber - o.pageNumber;
    }
  }

  public String toString() {
    return pageNumber + ":" + nextIndex.toString();
  }

}
