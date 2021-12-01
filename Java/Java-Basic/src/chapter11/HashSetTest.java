package chapter11;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {
    public static void main(String[] args) {
        Object[] objArr = {"1", new Integer(1), "2", "2", "3", "3", "4", "4", "4"};
        Set set = new HashSet();

        for (Object i : objArr) {
            set.add(i);
        }

        System.out.println(set);    // [1, 1, 2, 3, 4]
    }
}
