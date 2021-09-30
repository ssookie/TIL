package etc;

import java.util.*;

/**
 * [2019 KAKAO BLIND RECRUITMENT] 오픈채팅방
 * https://programmers.co.kr/learn/courses/30/lessons/42888
 */
public class Solution003 {
    public String[] solution(String[] record) {
        String[] answer = {};
        Map<String, String> map = new HashMap<>();
        List<String> answerList = new ArrayList<String>();

        // 마지막 닉네임만 기억하면 됨
        for (int i=0; i<record.length; i++) {
            String[] sentence = record[i].split(" ");
            if (sentence[0].equals("Enter") || sentence[0].equals("Change")) {
                map.put(sentence[1], sentence[2]);
            }
        }

        // 출력
        for (int i=0; i<record.length; i++) {
            String[] sentence = record[i].split(" ");
            String end = "";
            if (sentence[0].equals("Enter")) {
                answerList.add(map.get(sentence[1]) + "님이 들어왔습니다.");
            } else if (sentence[0].equals("Leave")) {
                answerList.add(map.get(sentence[1]) + "님이 나갔습니다.");
            }
        }

        answer = answerList.stream().toArray(String[]::new);
        return answer;
    }
}
