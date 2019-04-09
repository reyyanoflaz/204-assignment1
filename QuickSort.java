//TO-DO not sorted properly 
import java.util.ArrayList;
import java.util.HashMap;
public class QuickSort <T extends Comparable<T>> {																 // This class should not be instantiated.
    public <T extends Comparable<T>> void sort(ArrayList<T> sortArrList,HashMap<Integer,ArrayList<String>> flowData) {
   //     StdRandom.shuffle(a);
        sort(sortArrList, 0, sortArrList.size() - 1,flowData);
        assert isSorted(sortArrList,0,sortArrList.size());
    }
    private <T extends Comparable<T>>void sort(ArrayList<T> featureidxArrList, int lo, int hi,HashMap <Integer,ArrayList<String>> flowData) { 
        if (hi <= lo) return;		
    	// quicksort the subarray from a[lo] to a[hi]
        int j = partition(featureidxArrList, lo, hi,flowData);
        sort(featureidxArrList, lo, j-1,flowData);
        sort(featureidxArrList, j+1, hi,flowData);
        assert isSorted(featureidxArrList, lo, hi);
    }
    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi] and return the index j
    private  <T extends Comparable<T>> int partition(ArrayList<T> partitionArrList, int lo, int hi,HashMap<Integer, ArrayList<String>> flowData) throws ClassCastException{
        int i = lo;
        int j = hi + 1;
        T v = partitionArrList.get(lo);
        while (true) { 
        	while((partitionArrList.get(++i).compareTo(v)<0)==true){
        		if(i==hi) break;
        	}											  					 // find item on lo to swap
        	while((v.compareTo(partitionArrList.get(--j))<0)==true){
                if (j == lo) break;    									   	 // redundant since a[lo] acts as sentinel
            }											 					 // check if pointers cross
            if (i >= j) break;
            T swap = partitionArrList.get(i);
            partitionArrList.set(i, partitionArrList.get(j));
            partitionArrList.set(j, swap);
        	ArrayList<String> itemArrList=flowData.get(i);
        	flowData.put(i, flowData.get(j));
        	flowData.put(j, itemArrList);
           // exch(partitionArrList, i, j,flowData);
        }	
        T swap = partitionArrList.get(lo);
        partitionArrList.set(lo, partitionArrList.get(j));
        partitionArrList.set(j, swap);
    	ArrayList<String> itemArrList=flowData.get(lo);
    	flowData.put(lo, flowData.get(j));
    	flowData.put(j, itemArrList);// put partitioning item v at a[j]
      //  exch(partitionArrList, lo, j,flowData);				    // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }
  /*  public  <T extends Comparable<T>> T select(ArrayList<T> selectArrList, int k,HashMap<Integer, ArrayList<String>> flowData) {
        if (k < 0 || k >= selectArrList.size()){
            throw new IllegalArgumentException("index is not between 0 and " + selectArrList.size()+ ": " + k);
        }
      //  StdRandom.shuffle(a);
        int lo = 0, hi = selectArrList.size()- 1;
        while (hi > lo) {
            int i = partition(selectArrList, lo, hi,flowData);
            if      (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return selectArrList.get(i);
        }
        return selectArrList.get(lo);
    }   */ 
    private  <T extends Comparable<T>> boolean isSorted(ArrayList<T> isSortedArrList, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
        	if((isSortedArrList.get(i).compareTo(isSortedArrList.get(i-1))<0)==true)	return false;
        return true;
    }
    
}


