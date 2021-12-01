package chapter11;

import java.util.*;

public class ComparatorTest {
    public static void main(String[] args) {
        String[] strArr = {"cat", "Dog", "lion", "tiger"};

        // String 의 Comparable 구현에 의한 정렬
        Arrays.sort(strArr);
        System.out.println(Arrays.toString(strArr));    // [Dog, cat, lion, tiger]

        // 대소문자 구분 안함.
        Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(strArr));    // [cat, Dog, lion, tiger]

        // 역순 정렬 구현
        Arrays.sort(strArr, new Descending());
        System.out.println(Arrays.toString(strArr));    // [tiger, lion, cat, Dog]
    }
}

class Descending implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        // compare()의 매개변수가 Object 타입이기 때문에 compareTo를 바로 호출할 수 없고, 형변환 필요
        Comparable c1 = (Comparable) o1;
        Comparable c2 = (Comparable) o2;
        return c1.compareTo(c2) * -1;   // -1을 곱해서, 기본 정렬방식 역으로 변경.
        // return c2.compareT0(c1); 도 동일한 결과
    }
}
