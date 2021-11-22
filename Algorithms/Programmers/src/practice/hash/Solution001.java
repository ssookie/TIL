package practice.hash;

import java.util.*;

/**
 * [해시] 완주하지 못한 선수
 * https://programmers.co.kr/learn/courses/30/lessons/42576
 */
public class Solution001 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

//        List<String> filteredList = Arrays.stream(participant)
//                .filter(it -> Arrays.stream(completion).noneMatch(Predicate.isEqual(it)))
//                .collect(Collectors.toList());


        // hashmap 통해서 중복 찾기

        HashMap<String, Integer> filteredList = new HashMap<>();

        for (String player : participant) {
            filteredList.put(player, filteredList.getOrDefault(player, 0) + 1);
        }

//        for (String parti : participant) {
//            if (!filteredList.containsKey(parti)) {
//                filteredList.put(parti, 1);
//            } else {
//                filteredList.put(parti, filteredList.get(parti) + 1);
//            }
//        }

        for (String compl : completion) {
            if (filteredList.get(compl) == 1) {
                filteredList.remove(compl);
            } else {
                filteredList.put(compl, filteredList.get(compl) - 1);
            }
        }

        // hashmap 의 key 값 가져오기 (1개)
        for (String key : filteredList.keySet()) {
            answer = answer + key;
        }

        return answer;
    }
}
