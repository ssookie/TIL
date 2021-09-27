package level01;

import java.util.ArrayList;
import java.util.Stack;

/**
 * [2019 카카오 개발자 겨울 인턴십] 크레인 인형뽑기 게임
 * https://programmers.co.kr/learn/courses/30/lessons/64061
 * Array/ArrayList/Stack
 */
public class Solution007 {
    /**
     * ArrayList 이용
     */
    static int solution(int[][] board, int[] moves) {
        int answer = 0;
        ArrayList<Integer> basket = new ArrayList<Integer>();

        for (int i=0; i<moves.length; i++) {
            for (int j=0; j< board.length; j++) {
                if (board[j][moves[i]-1] != 0) {
                    basket.add(board[j][moves[i]-1]);   // add to basket
                    board[j][moves[i]-1] = 0; // pop
                    answer = answer + basketPop(basket);    // check basket
                    break;
                }
            }
        }

        return answer;
    }

    static int basketPop(ArrayList<Integer> basket) {
        int result = 0;
        for (int i=0; i<basket.toArray().length-1; i++) {
            if (basket.get(i) == basket.get(i+1)) {
                result = 2;
                basket.remove(i+1);
                basket.remove(i);
                break;
            }
        }
        return result;
    }

    /**
     * Stack 이용
     */
    static int solutionStack(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> basket = new Stack<>(); // declare int type stack

        for (int i=0; i<moves.length; i++) {
            for (int j=0; j< board.length; j++) {
                if (board[j][moves[i]-1] != 0) {
                    // check basket
                    if (!basket.isEmpty() && basket.peek() == board[j][moves[i]-1]) {
                        basket.pop();
                        answer = answer + 2;
                    } else {
                        basket.push(board[j][moves[i]-1]);
                    }
                    // remove from board
                    board[j][moves[i]-1] = 0;
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        // int answer = solution(new int[][]{{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}}, new int[]{1,5,3,5,1,2,1,4});
        int answer = solutionStack(new int[][]{{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}}, new int[]{1,5,3,5,1,2,1,4});
        System.out.println(answer);
    }
}
