package chapter02;

import java.util.Arrays;

public class SelectionSort {
    /**
     * 선택 정렬(Selection Sort)
     */
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int j = indexLowest(array, i);
            swapElements(array, i, j);
        }
    }

    /**
     * start 로부터 시작하는 최솟값의 위치를 찾아서 리턴한다.
     */
    public static int indexLowest(int[] array, int start) {
        int lowIndex = start;
        for (int i = start; i < array.length; i++) {
            if (array[i] < array[lowIndex]) {
                lowIndex = i;
            }
        }
        return lowIndex;
    }

    /**
     * i 와 j의 값을 바꾼다.
     */
    public static void swapElements(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {2, 5, 6, 1, 3};
        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }
}
