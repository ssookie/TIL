package practice.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * [해시] 전화번호 접두어 확인
 * https://programmers.co.kr/learn/courses/30/lessons/42577
 *
 * === Reference ===
 * https://hj-bank.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%A0%84%ED%99%94%EB%B2%88%ED%98%B8-%EB%AA%A9%EB%A1%9D-JAVA
 * https://velog.io/@dev-wanted/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%95%B4%EC%8B%9C-%EC%A0%84%ED%99%94%EB%B2%88%ED%98%B8-%EB%AA%A9%EB%A1%9D-Java
 */
public class Solution002 {
    public boolean solution(String[] phone_book) {

        // 방법 1) 효율성 테스트 실패함 (시간 초과)

//        for (String phone_num : phone_book) {
//
//            Stream filteredList = Arrays.stream(phone_book).filter(it -> it.startsWith(phone_num));
//            System.out.println(filteredList.collect(Collectors.toList()).toString());
//
//            if (Arrays.stream(phone_book).filter(it -> it.startsWith(phone_num)).count() > 1) {
//                return false;
//            }
//        }
//        return true;

        // 방법 2 ) hash 코드 - 탐색시간 줄이기

        // 중복 제거하여 해시 맵에 저장
        Map<String, String> phoneMap = new HashMap<>();
        for (String str : phone_book) {
            phoneMap.put(str, str);
        }

        // 탐색
        for (String str : phone_book) {
            for (int i = 0; i < str.length(); i++) {
                String tmp = str.substring(0, i);
                if (phoneMap.containsKey(tmp)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution002().solution(new String[]{"119", "97674223", "1195524421"}));
    }
}
