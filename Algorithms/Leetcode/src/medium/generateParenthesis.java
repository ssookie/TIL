package medium;

import java.util.ArrayList;
import java.util.List;

public class generateParenthesis {
    public List<String> solution(int n) {
        List<String> answer = new ArrayList<>();
        backtrack(answer, "", 0, 0, n);
        return answer;
    }

    public void backtrack(List<String> answer, String s, int open, int close, int max){
        if(s.length() == max * 2)
        {
            answer.add(s);
            return;
        }

        if(open < max)
        {
            backtrack(answer, s + "(", open + 1, close, max);
        }

        if(close < open)
        {
            backtrack(answer, s + ")", open, close + 1, max);
        }
    }
}
