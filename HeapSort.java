import java.util.ArrayList;
import java.util.HashMap;

/**
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class HeapSort < T extends Comparable<T>> {

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param pq the array to be sorted
     */
    public <T extends Comparable<T>> void sort(ArrayList<T> pq,HashMap<Integer, ArrayList<String>> flowData) {
        int n = pq.size();
        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n,flowData);
        while (n > 1) {
            T swap = pq.get(0);
            pq.set(0, pq.get(n-1));
            pq.set(n-1, swap);
            ArrayList<String> itemArrList=flowData.get(0);
            flowData.put(0,flowData.get(n-1));
            flowData.put(n-1, itemArrList);
            n--;
            sink(pq, 1, n,flowData);
        }
    }

   /***************************************************************************
    * Helper functions to restore the heap invariant.
    ***************************************************************************/
    private <T extends Comparable<T>> void sink(ArrayList<T> pq, int k, int n,HashMap<Integer, ArrayList<String>> flowData) {
        while (2*k <= n) {
            int j = 2*k;
            if(j < n && ((pq.get(j-1).compareTo(pq.get(j))<0)==true)) j++;
            if((pq.get(k-1).compareTo(pq.get(j-1))<0)==false) break;
          //  exch(pq, k, j);
            T swap = pq.get(k-1);
            pq.set(k-1, pq.get(j-1));
            pq.set(j-1, swap);
            ArrayList<String> itemArrList=flowData.get(k-1);
            flowData.put(k-1,flowData.get(j-1));
            flowData.put(j-1, itemArrList);
            k = j;    
        }
    }
    private <T extends Comparable<T>> boolean less(ArrayList<T> pq, int i, int j) {
        return pq.get(i-1).compareTo(pq.get(j-1)) < 0;
    }

}
