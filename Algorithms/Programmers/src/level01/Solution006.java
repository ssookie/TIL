package level01;

/**
 * [2020 카카오 인턴십] 키패드 누르기
 * https://programmers.co.kr/learn/courses/30/lessons/67256
 */
class Solution006 {
    public static String solution(int[] numbers, String hand) {
        String answer = "";

        // 엄지손가락 위치
        int[][] left = {{0,0}};
        int[][] right = {{2,0}};
        int[][] coordinates = {{1,0}, {0, 3}, {1,3}, {2, 3}, {0,2}, {1,2}, {2,2}, {0,1}, {1,1}, {2,1}};

        // 계산을 위한 변수
        double leftDistance = 0;
        double rightDistance = 0;

        for (int i=0; i<numbers.length; i++) {
            if( numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7 ) {
                answer = answer + "L";
                left[0] = coordinates[numbers[i]];
            } else if ( numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9 ) {
                answer = answer + "R";
                right[0] = coordinates[numbers[i]];
            } else {    // 가운데 열 계산 시에는 더 가까운 엄지손가락 사용, 키패드 이동 한 칸은 거리로 1에 해당
                leftDistance = Math.abs(left[0][0] - coordinates[numbers[i]][0]) + Math.abs(left[0][1] - coordinates[numbers[i]][1]);
                rightDistance = Math.abs(right[0][0] - coordinates[numbers[i]][0]) + Math.abs(right[0][1] - coordinates[numbers[i]][1]);
                if ( leftDistance == rightDistance ) {
                    if (hand.equals("right")) {
                        answer = answer + "R";
                        right[0] = coordinates[numbers[i]];
                    } else {
                        answer = answer + "L";
                        left[0] = coordinates[numbers[i]];
                    }
                } else if (leftDistance < rightDistance) {
                    answer = answer + "L";
                    left[0] = coordinates[numbers[i]];
                } else {
                    answer = answer + "R";
                    right[0] = coordinates[numbers[i]];
                }
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        String answer = solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, "right");
        System.out.println(answer);
    }
}
