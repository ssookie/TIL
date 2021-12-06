package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/roman-to-integer/
 */
public class romanToInt {
    public int solution(String s) {

        int result = 0;
        boolean flag = true;

        // Map 에 Symbol, Value 저장
        Map<Character, Integer> symbols = new HashMap<>();
        symbols.put('I', 1);
        symbols.put('V', 5);
        symbols.put('X', 10);
        symbols.put('L', 50);
        symbols.put('C', 100);
        symbols.put('D', 500);
        symbols.put('M', 1000);

        // 다음 문자열까지 비교
        for (int i = 0; i < s.length()-1; i++) {
            char roman = s.charAt(i);
            char romanNext = s.charAt(i + 1);

            if ((roman == 'I' && (romanNext == 'V' || romanNext == 'X'))
                    || (roman == 'X' && (romanNext == 'L' || romanNext == 'C'))
                    || (roman == 'C' && (romanNext == 'D' || romanNext == 'M'))) {
                result = result + symbols.get(romanNext) - symbols.get(roman);
                i++;
                if (i == s.length() - 1) {
                    flag = false;
                }
            } else {
                result = result + symbols.get(roman);
            }
        }

        // 마지막 문자열
        if (flag == true) {
            result = result + symbols.get(s.charAt(s.length() - 1));
        }

        return result;

    }

    public static void main(String[] args) {
        System.out.println(new romanToInt().solution("IV"));
        System.out.println(new romanToInt().solution("III"));
    }
}
