
/**
 * @author REYYAN OFLAZ
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.HashMap;
public class SelectionSort<T extends Comparable<T>> 
{ 	
	protected <T extends Comparable<T>> void selectionSort(ArrayList<T> featureidxArray,HashMap<Integer,ArrayList<String>> flowData) throws NumberFormatException
	{
	     int n = featureidxArray.size();
	        for (int i = 0; i < n; i++) {						// One by one move boundary of unsorted subarray
	            int min = i;
	            for (int j = i+1; j < n; j++) {
	            	if((featureidxArray.get(j).compareTo(featureidxArray.get(min))<0)==true) min=j;
	            }
	            T item = featureidxArray.get(i);
	        	featureidxArray.set(i, featureidxArray.get(min));
	        	featureidxArray.set(min, item);
	        	ArrayList<String> itemArrList=flowData.get(i);
	        	flowData.put(i, flowData.get(min));
	        	flowData.put(min, itemArrList);
	        }
	        assert isSorted(featureidxArray,0,featureidxArray.size()-1);
	}
    private  <T extends Comparable<T>> boolean isSorted(ArrayList<T> isSortedArray, int lo, int hi) throws NumberFormatException {	 // is the array sorted from a[lo] to a[hi]
        for (int i = lo + 1; i <= hi; i++){
        	if((isSortedArray.get(i).compareTo(isSortedArray.get(i-1))<0)==true) return false;
        }
        return true;
    }
}
