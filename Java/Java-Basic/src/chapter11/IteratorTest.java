package chapter11;

import java.util.*;

public class IteratorTest {
    public static void main(String[] args) {
        // 참조 변수의 type을 ArrayList가 아닌, List로 한 이유 - 다른 컬렉션으로 변경할 때에는 이 부분만 고치면 된다.
        // 그러나 이 경우에는, List에 정의된 메서드만 사용 가능하다.
        List list = new ArrayList();
        list.add(1);
        list.add(2);

        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // Set 클래스들은 각 요소간의 순서가 유지 되지 않기 때문에 순서가 보장되지 않음.
        Map map = new HashMap();
        map.put(1, "hi");
        map.put(2, "hola");
        Set entrySet = map.entrySet();
        Set keySet = map.keySet();
        Iterator mapList = map.entrySet().iterator();
        Iterator keyList = map.keySet().iterator();
        while (mapList.hasNext()) {
            System.out.println(mapList.next());
        }
        // iterator는 재사용이 안됨 -> 아래 결과는 null
        while (mapList.hasNext()) {
            System.out.println(mapList.next());
        }
        while (keyList.hasNext()) {
            Object obj = keyList.next();
            System.out.println(obj);
        }
    }
}
