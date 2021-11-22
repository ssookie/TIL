package practice.hash;

import java.util.Arrays;

/**
 * [해시] 전화번호 접두어 확인
 * https://programmers.co.kr/learn/courses/30/lessons/42577
 */
public class Solution002 {
    public boolean solution(String[] phone_book) {
        for (String phone_num : phone_book) {

//            Stream filteredList = Arrays.stream(phone_book).filter(it -> it.startsWith(phone_num));
//            System.out.println(filteredList.collect(Collectors.toList()).toString());

            // 효율성 테스트 실패함 (시간 초과)
//             if (Arrays.stream(phone_book).filter(it -> it.startsWith(phone_num)).count() > 1) {
//                 return false;
//             }

            for (String target : phone_book) {
                if(!phone_num.equals(target) && target.startsWith(phone_num)) {
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
