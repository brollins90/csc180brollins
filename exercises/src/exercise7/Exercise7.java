package exercise7;

import java.util.ArrayList;

/**
 * This class is to help us practice using Generics in java.
 * 
 * @author Blake Rollins
 * 
 * @param <T>
 */
public class Exercise7<T extends Number> {

    /**
     * Iterates though the items and retuns the SUM of the values as doubles.
     * @param collection    An iterable collection of T items
     * @return  The sum of the items in the collection
     */
    public double sum(Iterable<T> collection) {
        double sumValue = 0.0d;

        if (collection != null) {
            for (T e : collection) {
                sumValue += e.doubleValue();
            }
        }
        return sumValue;
    }
    
    /**
     * Iterates through the collection and returns the first item that is greater than the second parameter
     * @param collection    The collection of items to search through
     * @param other The Item to compare against
     * @return  The first value in the collection that is greater that other, or other if nothing greater is found
     */
    public T getGreaterThan(Iterable<T> collection, T other) {
        if (collection != null && other != null) {
            T greatest = other;

            for (T e : collection) {
                if (e.doubleValue() > other.doubleValue()) {
                    return e;
                }
            }
            return greatest;
        }
        return null;
    }

    /**
     * returns the item that is the highest in the collection  
     * @param c The collection of objects
     * @return  The object in the collection with the highest value
     */
    public <S extends Comparable<? super S>> S max(Iterable<S> c) {
        // public Comparable<?> max(Iterable<? extends Comparable> c) {

        S greatest = null;
        // Comparable greatest = null;

        if (c != null) {
            for (S e : c) {
                // for (Comparable e : c) {

                if (greatest == null) {
                    greatest = e;
                }
                greatest = (greatest.compareTo(e) > 0) ? greatest : e;

            }
        }
        return greatest;
    }

    /**
     * reverses the input array of items
     * @param items The array of items to reverse
     */
    public static <R> void reverse(R[] items) {
        if (items != null) {
            for (int i = 0; i < (items.length / 2); i++) {
                int aIndex = i;
                int bIndex = items.length - i - 1;
                R a = items[aIndex];
                R b = items[bIndex];

                items[aIndex] = b;
                items[bIndex] = a;
            }
        }
    }


    static Integer[] ints1 = {1, 2, 3, 4, 5, 6, 7, 8};

    public static void main(String[] args) {

        Exercise7 ex = new Exercise7<>();

        ArrayList<Integer> ints2 = new ArrayList<Integer>();
        ints2.add(1);
        ints2.add(10);
        ints2.add(9);
        ints2.add(5);
        ints2.add(6);
        ints2.add(2);
        System.out.println("MAX: " + ex.max(ints2));
        System.out.println("GGT: " + ex.getGreaterThan(ints2, 7));
        System.out.println("GGT: " + ex.getGreaterThan(ints2, 17));
        System.out.println("");

        ArrayList<Integer> nums1 = new ArrayList<Integer>();
        nums1.add(2);
        nums1.add(5);
        nums1.add(9);

        System.out.println("SUM: " + ex.sum(nums1));
        System.out.println("");


        System.out.println("Org ints1:");
        for (Integer i : ints1) {
            System.out.print(i + " ");
        }

        System.out.println("");
        reverse(ints1);
        System.out.println("Reversed ints1:");
        for (Integer i : ints1) {
            System.out.print(i + " ");
        }
        System.out.println("\n");

        
        System.out.println("SUM with null: " + ex.sum(null));

        System.out.println("GGT with null: " + ex.getGreaterThan(null, null));
        System.out.println("GGT with null: " + ex.getGreaterThan(ints2, null));
        System.out.println("GGT with null: " + ex.getGreaterThan(null, 7));

        System.out.println("MAX with null: " + ex.max(null));

        System.out.println("REV with null: ");
        ex.reverse(null);



        // Integer greatestIntIn2 = getGreaterThan(ints2, 5);
    }
}
