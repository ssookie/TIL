package chapter0101;

import java.util.Arrays;
import java.util.List;

/**
 * 선언형 프로그래밍
 * List에 있는 숫자들 중에서 6보다 큰 홀수들의 합계를 구하세요.
 */
public class DeclarativePrograming {
    public static void main(String[] args){
        List<Integer> numbers = Arrays.asList(1, 3, 21, 10, 8, 11);

        // stream api 사용
        int sum = numbers.stream()
                .filter(number -> number > 6 && (number % 2 != 0))  // filtering 선언만 할 뿐, 구체적인 알고리즘 명시하지 않음.
                .mapToInt(number -> number)
                .sum();

        System.out.println("# 선언형 프로그래밍 사용: " + sum);
    }
}
