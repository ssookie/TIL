package level01;

import java.util.Arrays;

/**
 * [정렬-K번째수] 배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.
 * https://programmers.co.kr/learn/courses/30/lessons/42748
 */
public class Solution001 {

    public static int[] solution(int[] array, int[][] commands) {
        // commands와 같은 크기의 배열 생성
        int[] answer = new int[commands.length];

        for (int i=0; i<commands.length; i++) {
            // 배열 자르기 - Arrays.copyOfRange() 이용
            int[] tempArray = new int[commands[i][1]-commands[i][0]+1];
            tempArray = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
            // System.out.println(Arrays.toString(tempArray));

            // 오름차순 정렬 - Arrays.sort() 이용
            Arrays.sort(tempArray);
            answer[i] = tempArray[commands[i][2]-1];
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] answer = solution(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}});
        System.out.println(Arrays.toString(answer));
    }
}
