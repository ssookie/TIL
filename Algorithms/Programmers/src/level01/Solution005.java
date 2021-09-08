package level01;

import java.util.Arrays;

/**
 * [2018 KAKAO BLIND RECRUITMENT] [1차] 비밀지도
 * https://programmers.co.kr/learn/courses/30/lessons/17681
 * java bit operation
 */
public class Solution005 {
    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        // 2진수 지도 Array
        String[] map1 = new String[n];
        String[] map2 = new String[n];

        // 10진수 -> 2진수 변환
        String formatter = "%0" + n +"d";
        for (int i=0; i<n; i++) {
            // Runtime Error, int 범위를 넘어감
            // Integer.parseInt -> Long.parseLong
            map1[i] = String.format(formatter, Long.parseLong(Integer.toBinaryString(arr1[i])));
            map2[i] = String.format(formatter, Long.parseLong(Integer.toBinaryString(arr2[i])));
        }

        // map의 AND/OR 연산
        String[] answerMap1;
        String[] answerMap2;
        String answerString = "";
        for (int i=0; i<n; i++) {
            answerMap1 = map1[i].split("");
            answerMap2 = map2[i].split("");

            for (int j=0; j<n; j++) {
                if (answerMap1[j].equals("0") && answerMap2[j].equals("0")) {
                    answerString += " ";
                } else {
                    answerString += "#";
                }
            }

            answer[i] = answerString;
            answerString = "";
        }

        return answer;
    }

    public static String[] solution2(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String formatter = "%0" + n +"d";

        for(int i=0; i<n; i++) {
            String temp = Integer.toBinaryString(arr1[i] | arr2[i]);
            answer[i] =  String.format(formatter, Long.parseLong(Integer.toBinaryString(arr1[i] | arr2[i])))// binary OR operation
                    .replace("0", " ")
                    .replace("1", "#");
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] answer = solution2(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28});
        System.out.println(Arrays.toString(answer));

        String[] answer2 = solution2(6, new int[]{46, 33, 33 ,22, 31, 50}, new int[]{27 ,56, 19, 14, 14, 10});
        System.out.println(Arrays.toString(answer2));
    }
}
