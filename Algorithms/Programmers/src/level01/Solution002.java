package level01;

/**
 * [2021 카카오 채용연계형 인턴십-숫자 문자열과 영단어] 숫자의 일부 영단어를 숫자로 바꾸기
 * https://programmers.co.kr/learn/courses/30/lessons/81301
 */
public class Solution002 {

    public static int solution(String s) {
        int answer = 0;

        String[][] array = {    // 2차원 배열 말고 1차원 배열을 써도 됨, index가 즉 숫자이므로
             {"0", "zero"}
            ,{"1", "one"}
            ,{"2", "two"}
            ,{"3", "three"}
            ,{"4", "four"}
            ,{"5", "five"}
            ,{"6", "six"}
            ,{"7", "seven"}
            ,{"8", "eight"}
            ,{"9", "nine"}
        };

        for (int i=0; i<array.length; i++) {
            s = s.replaceAll(array[i][1], array[i][0]);
        }

        answer = Integer.parseInt(s);
        return answer;
    }

    public static void main(String[] args) {
        int answer = solution("one4seveneight");
        System.out.println(answer);
    }
}

