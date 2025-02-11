package chapter01;

import java.util.LinkedList;
import java.util.List;

public class ListClientExample {
    // 인스턴스 변수를 List interface로 선언
    private List list;

    // 새로운 LinkedList 객체를 만들어 리스트를 초기화
    public ListClientExample() {
        list = new LinkedList();
    }

    // List 형을 반환하지만, 구체적인 클래스는 언급하지 않음
    public List getList() {
        return list;
    }

    public static void main(String[] args) {
        ListClientExample lce = new ListClientExample();
        List list = lce.getList();
        System.out.println(list);
    }
}

