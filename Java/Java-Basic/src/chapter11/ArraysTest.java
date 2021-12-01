package chapter11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysTest {
    public static void main(String[] args) {
        // asList()가 반환한 배열의 크기는 변경할 수 없음.
        List list = Arrays.asList(new Integer[]{1, 2, 3, 4, 5});
        // list.add(6);    // UnsupportedOperationException 발생

        // 배열의 크기를 변경하기 위해서는 ArrayList로 생성
        List list2 = new ArrayList(Arrays.asList(new Integer[]{1, 2, 3, 4, 5}));
        list2.add(6);
        System.out.println(list2.toString());   // [1, 2, 3, 4, 5, 6]
    }
}
