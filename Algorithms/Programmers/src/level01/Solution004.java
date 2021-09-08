package level01;

import java.util.*;
/**
 * [2019 KAKAO BLIND RECRUITMENT] 실패율
 * https://programmers.co.kr/learn/courses/30/lessons/42889
 *
 * 실패율은 다음과 같이 정의한다.
 * 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
 */
public class Solution004 {
    public static int[] solution(int N, int[] stages) {

        // 실패율 계산을 위한 변수 설정
        int person = 0; // 스테이지에 머물러있는 사람수
        int n = stages.length;  // 남은 인원
        double rate = 0; // 실패율

        // 스테이지와 실패율을 저장할 Map 선언
        Map<Integer, Double> answerMap = new HashMap<Integer, Double>();

        // 실패율 구하기, i는 스테이지를 의미함.
        for (int i=0; i<N; i++) {
            n = n - person;
            person = 0;
            // 스테이지에 머물러 있는 사람 수 구하기
            for (int j=0; j<stages.length; j++) {
                if(stages[j] == i+1) {
                    person += 1;
                }
            }
            // 실패율, 스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0 으로 정의한다.
            if(person == 0) {
                rate = 0;
            } else {
                rate  = ((double) person) / n;
            }
            answerMap.put(i+1, rate);
        }

        // 정렬, 만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 하면 된다.
        // List<Integer> keySetList = new ArrayList<>(answerMap.keySet());
        // Collections.sort(keySetList, (o1, o2) -> (answerMap.get(o2).compareTo(answerMap.get(o1))));

        List<Map.Entry<Integer, Double>> answerList = new LinkedList<>(answerMap.entrySet());

        // value 내림차순으로 정렬하고, value가 같으면 key 오름차순으로 정렬
        Collections.sort(answerList, (o1, o2) -> {
            if (o1.getValue() > o2.getValue()) {
                return -1;
            } else if (o1.getValue() < o2.getValue()) {
                return 1;
            }
            return o1.getKey().compareTo(o2.getKey());
        });

        int[] answer = answerList.stream().mapToInt(i->i.getKey()).toArray();
        return answer;
    }

    public static void main(String[] args) {
        int[] answer = solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3});
        System.out.println(Arrays.toString(answer));

        int[] answer2 = solution(4, new int[]{4, 4, 4, 4, 4});
        System.out.println(Arrays.toString(answer2));
    }
}
