package etc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [위클리 챌린지] 2주차_상호평가
 * https://programmers.co.kr/learn/courses/30/lessons/83201
 */
public class Solution001 {
    public static String solution(int[][] scores) {
        String answer = "";
        // temp array to caculate average score
        List<Integer> tempScore = new ArrayList<Integer>();
        double score = 0;

        for (int i=0; i<scores.length; i++) {
            // i 번째 학생의 score array
            for (int j=0; j<scores.length; j++) {
                tempScore.add(scores[j][i]);
            }

            // average score 계산
            Collections.sort(tempScore);
            if(tempScore.get(0)!=tempScore.get(1) && tempScore.get(0) == scores[i][i]) {
                tempScore.remove(0);
            }
            if(tempScore.size()>1 && tempScore.get(tempScore.size()- 1) != tempScore.get(tempScore.size()- 2) && tempScore.get(tempScore.size()- 1) == scores[i][i]){
                tempScore.remove(scores.length- 1);
            }
            score = tempScore.stream().mapToDouble(Integer::doubleValue).average().getAsDouble();
            tempScore.removeAll(tempScore);

            // grade
            if (score >= 90) {
                answer = answer + "A";
            } else if (score >= 80) {
                answer = answer + "B";
            } else if (score >= 70) {
                answer = answer + "C";
            } else if (score >= 50) {
                answer = answer + "D";
            } else {
                answer = answer + "F";
            }
        }
        return answer;
    }

    /**
     * 실행 속도가 너무 느려서 다시 작성
     */
    public static String solutionFaster(int[][] scores) {
        String answer = "";
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<scores.length; i++) {
            int max = scores[0][i];
            int min = scores[0][i];
            int cnt = 0;
            int sum = 0;
            for (int j=0; j<scores.length; j++) {
                sum = sum + scores[j][i];
                max = Math.max(max, scores[j][i]);
                min = Math.min(min, scores[j][i]);

                if(scores[i][i] == scores[j][i]){
                    cnt++;
                }

            }
            // caculate average score
            int num = scores.length;
            if(cnt == 1 && (scores[i][i] == max || scores[i][i] == min)) {
                sum = sum - scores[i][i];
                num --;
            }
            double score = (double) sum / num;
            // grade
            if (score >= 90) {
                sb.append('A');
            } else if (score >= 80) {
                sb.append('B');
            } else if (score >= 70) {
                sb.append('C');
            } else if (score >= 50) {
                sb.append('D');
            } else {
                sb.append('F');
            }
        }
        answer = sb.toString();
        return answer;
    }

    public static void main(String[] args) {
        // String answer = solution(new int[][]{{100,90,98,88,65},{50,45,99,85,77},{47,88,95,80,67},{61,57,100,80,65},{24,90,94,75,65}});
        String answer = solutionFaster(new int[][]{{50,90},{50,87}});
        System.out.println(answer);
    }
}
