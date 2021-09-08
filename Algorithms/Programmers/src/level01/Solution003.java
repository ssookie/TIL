package level01;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * [2021 KAKAO BLIND RECRUITMENT] 신규 아이디 추천
 * https://programmers.co.kr/learn/courses/30/lessons/72410
 * regExp
 *
 * 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
 * 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
 * 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
 * 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
 * 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
 * 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
 *      만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
 * 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
 */
public class Solution003 {
    public static String solution(String new_id) {
        String answer = "";

        // Stream 생성해보고 싶었음... phase1,2 까지만 stream으로 처리해봄
        answer = Stream.of(new_id.split(""))
                .map(s -> s.toLowerCase())  // phase 1
                .filter(s -> s.matches("^[a-z]*$")|| s.matches("^[0-9]") || s.matches("-") || s.matches("_") || s.matches("[.]")) // phase2
                .collect(Collectors.joining());
        ;

        // phase 3
        answer = answer.replaceAll("[.]++", ".");

        // phase 4
        if (answer.startsWith(".")) {
            answer = answer.substring(1);
        }
        if (answer.endsWith(".")) {
            answer = answer.substring(0, answer.length() - 1);
        }

        // phase 5
        if (answer.equals("")) {
            answer = "a";
        }

        // phase 6
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
        }
        if (answer.endsWith(".")) {
            answer = answer.substring(0, answer.length() - 1);
        }

        // phase 7
        if (answer.length() <= 2) {
            answer = answer + answer.substring(answer.length() - 1).repeat(3-answer.length());
        }
        return answer;
    }

    /**
     * 정규식을 통한 solution
     */
    public static String regSolution(String new_id) {
        String answer = "";
        // phase 1
        answer = new_id.toLowerCase();

        // phase 2
        answer = answer.replaceAll("[^-_.a-z0-9]", "");

        // phase 3
        answer = answer.replaceAll("[.]{2,}",".");

        // phase 4
        answer = answer.replaceAll("^[.]|[.]$","");

        // phase 5
        if(answer.equals(""))
            answer = "a";

        // phase 6
        if(answer.length() >=16){
            answer = answer.substring(0,15);
            answer = answer.replaceAll("^[.]|[.]$","");
        }

        // phase 7
        if(answer.length()<=2)
            while(answer.length()<3)
                answer += answer.charAt(answer.length()-1);

        return answer;
    }

    public static void main(String[] args) {
        String answer = solution("...!@BaT#*..y.abcdefghijklm11111..");
        System.out.println(answer);

        String regAnswer = regSolution("...!@BaT#*..y.abcdefghijklm11111..");
        System.out.println(regAnswer);
    }
}
