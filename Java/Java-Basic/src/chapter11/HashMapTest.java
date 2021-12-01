package chapter11;

import java.util.*;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("김자바", new Integer(90));
        map.put("김자바", new Integer(100));
        map.put("이자바", new Integer(100));
        map.put("강자바", new Integer(80));
        map.put("안자바", new Integer(90));

        Set set = map.entrySet();
        Iterator it = set.iterator();
        System.out.println(set.toString()); // [안자바=90, 김자바=100, 강자바=80, 이자바=100]
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            System.out.println("이름: " + e.getKey() + ", 점수: " + e.getValue());
        }

        set = map.keySet();
        System.out.println(set.toString()); // [안자바, 김자바, 강자바, 이자바]

        Collection values = map.values();
        System.out.println(values.toString());  // [90, 100, 80, 100]
        it = values.iterator();
        int total = values.stream()
                .mapToInt(e -> (int) e)
                .sum();
        System.out.println("total: " + total);
    }
}
