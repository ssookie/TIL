package chapter11;

import java.util.HashSet;
import java.util.Objects;

public class HashSetEqualsTest {
    public static void main(String[] args) {
        // HashSet의 equals()와 hashCode() 확인
        HashSet set = new HashSet();

        set.add("abc");
        set.add(new String("abc"));
        set.add(new Person("hola", 20));
        set.add(new Person("hola", 20));

        System.out.println(set.toString()); // [abc, hola : 20]

        // hashCode()의 조건 확인
        Person p = new Person("David", 10);
        int hashCode1 = p.hashCode();
        int hashCode2 = p.hashCode();
        p.age = 20;
        int hashCode3 = p.hashCode();
        System.out.println("hashCode1 === " + hashCode1);   // 동일
        System.out.println("hashCode2 === " + hashCode2);   // 동일
        System.out.println("hashCode3 === " + hashCode3);   // 다름

        // equals()로 비교해서 true를 얻은 두 객체의 hashCode() 값은 일치해야 한다.
        Person p2 = new Person("David", 20);
        System.out.println(p.equals(p2));   // true
        System.out.println(p.hashCode());   // 동일
        System.out.println(p2.hashCode());  // 동일
    }
}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 기존에 저장된 요소와 같은 것인지 판별
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person tmp = (Person) obj;
            return name.equals(tmp.name) && age == tmp.age;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // return (name + age).hashCode(); // String 클래스의 hashCode() 사용
        return Objects.hash(name, age); // java.util.Objects 클래스의 hash() 사용, int hash(Object... values)
    }

    @Override
    public String toString() {
        return name + " : " + age;
    }

}