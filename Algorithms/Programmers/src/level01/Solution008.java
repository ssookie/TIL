package level01;

import java.util.Arrays;

/**
 * [ 2021 Dev-Matching: 웹 백엔드 개발자(상반기)] 로또의 최고 순위와 최저 순위
 * https://programmers.co.kr/learn/courses/30/lessons/77484
 */
public class Solution008 {

    public static int i = 0;

    static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {0, 0};
        int[] lottoRanking = {6, 6, 5, 4, 3, 2, 1, 0};

        // 로또 당첨 번호 갯수 확인
        int match = 0;
        int unknown = 0;
        for (i=0; i<6; i++) {
//            if (Arrays.asList(lottos).contains(win_nums[i])) {
//                match = match + 1;
//            }
            if (Arrays.stream(lottos).anyMatch(x -> x == win_nums[i])) {
                match ++;
            }
            if (lottos[i] == 0) {
                unknown ++;
            }
        }

        // 로또 최저 순위
        answer[1] = lottoRanking[match];
        // 로또 최고 순위
        answer[0] = lottoRanking[match + unknown];

        return answer;
    }

    public static void main(String[] args) {
        int[] answer = solution(new int[]{0, 0, 0, 0, 0, 0}, new int[]{38, 19, 20, 40, 15, 25});
        System.out.println(Arrays.toString(answer));
    }
}
