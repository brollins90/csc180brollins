package exercise5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Exercise 5 : Talking about the different Collections
 * @author Blake Rollins
 *  2014-04-28
 *
 */

public class Exercise5 {

    String[] keys = {"One", "Two", "Three", "Three", "Five", "Seven", "seven", "Eight"};
    Integer[] values = {0, 10, Integer.MAX_VALUE, Integer.MIN_VALUE, -1, 1, 5, 100};

    public static void main(String[] args) {
        
        Exercise5 ex5 = new Exercise5();
        ex5.doIt();       
    }
    
    private void doIt() {
        /* An ArrayList and a LinkedList are both very similar in how they function. Both allow the storage of duplicates and null values.
         * The biggest difference is how fast they are when accessing data.  If you are going to access the data linearly both Lists are
         * very similar in speed, but if you are going to randomly be grabbing data from the middle of the list, and ArrayList is a better choice.
         * However if you are going to add data to the center of the list, the LinkedList is better. */
        ArrayList<String> al = new ArrayList<String>();
        LinkedList<String> ll = new LinkedList<String>();
        
        /* A Hashset and treeset are very similar in nature.  Neither will allow duplicates.  A hashset does not sort the elements, or guarantee
         * that the elements will stay ordered the way that they previously have been.  A treeset has an order that is specified by the constructor. */
        HashSet<String> hs = new HashSet<String>();
        TreeSet<String> ts = new TreeSet<String>();
        
        /* A priorityqueue is a collection that is always sorted the way that you specify in the constructor.  It will allow duplicates */
        PriorityQueue<String> pq = new PriorityQueue<String>();
        
        /* Similar to the HashSet and TreeSet, the hashmap has no guaranteed order, but is generally faster than a treemap.  Neither will allow duplicates
         * since they both extend the Map class */
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
        
        addKeys(al, keys);
        displayCollection(al);
        
        addKeys(ll, keys);
        displayCollection(ll);
        
        addKeys(hs, keys);
        displayCollection(hs);
        
        addKeys(ts, keys);
        displayCollection(ts);
        
        addKeys(pq, keys);
        displayCollection(pq);
        
        addKeysValues(hm, keys, values);
        displayMap(hm);
        
        addKeysValues(tm, keys, values);
        displayMap(tm);
    }
    
    /**
     * Adds the specified Strings to the specified Collection
     * @param c The Collection to store the Strings
     * @param ss    The Strings to add
     */
    private void addKeys(Collection<String> c, String[] ss) {
        
        for( String s : ss) {
            c.add(s);
        }
    }
    
    /**
     * Adds the specified Strings/Ints to the specified Map
     * @param m THe Map to store the values
     * @param ss    The Strings to use as keys
     * @param ii    The Ints to store as values
     */
    private void addKeysValues(Map<String, Integer> m, String[] ss, Integer[] ii) {
        
        if (ss.length == ii.length) {
            for (int i = 0; i < ss.length; i++) {
                m.put(ss[i], ii[i]);
            }
        } else {
            // cannot do it
        }
    }
    
    /**
     * Displays all the elements in the Collection
     * @param c The Collection to display
     */
    private void displayCollection(Collection<String> c) {
        
        System.out.println("Printing out " + c.getClass());
        System.out.println("Contains " + c.size() + " elements.");
        
        for (Iterator<String> it = c.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
        System.out.println("");
    }
    
    /**
     * Displays all the elements in the Map
     * @param m The Map to display
     */
    private void displayMap(Map<String, Integer> m) {
        
        System.out.println("Printing out " + m.getClass());
        System.out.println("Contains " + m.size() + " elements.");
        for(Map.Entry<String, Integer> e : m.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
        System.out.println("");
    }
}
