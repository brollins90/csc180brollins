package exercise9;

import java.util.Date;

/**
 * This program show us some simple Lambda expressions in Java 8
 * 
 * @author Blake Rollins
 *
 */
public class Exercise9 {
    interface Single {
        void takesAString(String s);
    }
    interface Double {
        String takesTwoParams(long l, int i);
    }

    public static void main(String[] args) {
        // I dont want to make up a number, so I will use the long that represents right now.
        Date today = new Date();

        Single s1 = (s) -> System.out.println(s);
        Double d1 = (l, i) -> new Date(l).toString() + i;
        Thread t1 = new Thread(() -> {
            s1.takesAString("number 1");
            System.out.println(d1.takesTwoParams(today.getTime(), 15));
        });
        t1.start();
    }
}
