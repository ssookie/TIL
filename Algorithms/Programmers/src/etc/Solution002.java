package etc;

import java.util.HashMap;
import java.util.Map;

/**
 * [찾아라 프로그래밍 마에스터] 폰켓몬
 * https://programmers.co.kr/learn/courses/30/lessons/1845
 */
public class Solution002 {
    public int solution(int[] nums) {
        int answer = 0;
        int num = nums.length / 2;

        // 포켓몬의 종류와 갯수를 Map 에 담기
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0));
        }

        if (map.size() >= num) {
            answer = num;
        } else {
            answer = map.size();
        }

        return answer;
    }
}
