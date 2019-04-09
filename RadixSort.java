
import java.util.ArrayList;
import java.util.Collections;

public class RadixSort {
    private static final int R             = 256;   // extended ASCII alphabet size
    private static final int CUTOFF        =  15;   // cutoff to insertion sort

    public static void sort(ArrayList<String> a) {
        int n = a.size();
        ArrayList<String> aux = new ArrayList<String>((Collections.nCopies(a.size(),"")));			//N
        sort(a, 0, n-1, 0, aux);
    }
    // return dth character of s, -1 if d = length of string
    private static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        return s.charAt(d);
    }
    // sort from a[lo] to a[hi], starting at the dth character
    private static void sort(ArrayList<String> a, int lo, int hi, int d, ArrayList<String> aux) {
        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }
        // compute frequency counts
        int[] count = new int[R+2];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a.get(i), d);
            count[c+2]++;
        }

        // transform counts to indicies
        for (int r = 0; r < R+1; r++)
            count[r+1] += count[r];

        // distribute
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a.get(i), d);
            aux.set(count[c+1]++, a.get(i));
        }

        // copy back
        for (int i = lo; i <= hi; i++) 
        	a.set(i, aux.get(i-lo));
        	


        // recursively sort for each character (excludes sentinel -1)
        for (int r = 0; r < R; r++)
            sort(a, lo + count[r], lo + count[r+1] - 1, d+1, aux);
    }


    // insertion sort a[lo..hi], starting at dth character
    private static void insertion(ArrayList<String> a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a.get(j), a.get(j-1), d); j--)
                exch(a, j, j-1);
    }

    // exchange a[i] and a[j]
    private static void exch(ArrayList<String> a, int i, int j) {
        String temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // is v less than w, starting at character d
    private static boolean less(String v, String w, int d) {
        // assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }
}