/**
 * class Main
 *
 * @author Melnev Dmitry
 * @version 2022/08
 */
package Task1_2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] testArray = new int[]{5, 6, 3, 2, 5, 1, 4, 9};
        int[] testArray2 = new int[]{5, 6, 3, 2, 5, 1, 4, 9};

        System.out.print("Origin:\t\t\t\t\t\t");
        System.out.println(Arrays.toString(testArray));

        System.out.print("Sorted by 2WayBubbleSort:\t");
        twoWayBubbleSort(testArray);
        System.out.println(Arrays.toString(testArray));

        System.out.print("Sorted by quickSort:\t\t");
        quickSort(testArray2);
        System.out.println(Arrays.toString(testArray2));

//Tests
        int[] array = new int[]{89, 88, 3, 87, 86, 85, 84, 83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 56, 55, 54, 53, 52, 51, 50, 39, 38, 37, 36, 35, 34, 32, 31, 1};
        int[] arraySorted = new int[]{1, 3, 31, 32, 34, 35, 36, 37, 38, 39, 50, 51, 52, 53, 54, 55, 56, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89};
        int[] array2 = new int[]{89, 88, 4, 87, 86, 85, 84, 83, 82, -81, 80, 79, 78, 77, 76, 75, -74, 56, 55, 54, 53, 52, 51, 50, -39, 38, 37, 36, 37, 34, 32, 31, 2};
        int[] array2Sorted = new int[]{-81, -74, -39, 2, 4, 31, 32, 34, 36, 37, 37, 38, 50, 51, 52, 53, 54, 55, 56, 75, 76, 77, 78, 79, 80, 82, 83, 84, 85, 86, 87, 88, 89};
        int[] array3 = new int[]{-2, -1, 0, 1, 2, 3};
        int[] array3Sorted = new int[]{-2, -1, 0, 1, 2, 3};
        int[] array4 = new int[]{2, 2, 2};
        int[] array4Sorted = new int[]{2, 2, 2};
        int[] array5 = new int[]{1};
        int[] array5Sorted = new int[]{1};
        int[] array6 = new int[5];
        int[] array6Sorted = new int[5];

        twoWayBubbleSort(array);
        assert Arrays.equals(array, arraySorted) : "array was sorted by twoWayBubbleSort not correct";
        twoWayBubbleSort(array2);
        assert Arrays.equals(array2, array2Sorted) : "array2 was sorted by twoWayBubbleSort not correct";
        twoWayBubbleSort(array3);
        assert Arrays.equals(array3, array3Sorted) : "array3 was sorted by twoWayBubbleSort not correct";
        twoWayBubbleSort(array4);
        assert Arrays.equals(array4, array4Sorted) : "array4 was sorted by twoWayBubbleSort not correct";
        twoWayBubbleSort(array5);
        assert Arrays.equals(array5, array5Sorted) : "array5 was sorted by twoWayBubbleSort not correct";
        twoWayBubbleSort(array6);
        assert Arrays.equals(array6, array6Sorted) : "array6 was sorted by twoWayBubbleSort not correct";

        array = new int[]{89, 88, 3, 87, 86, 85, 84, 83, 82, 81, 80, 79, 78, 77, 76, 75, 74, 56, 55, 54, 53, 52, 51, 50, 39, 38, 37, 36, 35, 34, 32, 31, 1};
        array2 = new int[]{89, 88, 4, 87, 86, 85, 84, 83, 82, -81, 80, 79, 78, 77, 76, 75, -74, 56, 55, 54, 53, 52, 51, 50, -39, 38, 37, 36, 37, 34, 32, 31, 2};
        array3 = new int[]{-2, -1, 0, 1, 2, 3};
        array4 = new int[]{2, 2, 2};
        array5 = new int[]{1};
        array6 = new int[5];

        quickSort(array);
        assert Arrays.equals(array, arraySorted) : "array was sorted by quickSort not correct";
        quickSort(array2);
        assert Arrays.equals(array2, array2Sorted) : "array2 was sorted by quickSort not correct";
        quickSort(array3);
        assert Arrays.equals(array3, array3Sorted) : "array3 was sorted by quickSort not correct";
        quickSort(array4);
        assert Arrays.equals(array4, array4Sorted) : "array4 was sorted by quickSort not correct";
        quickSort(array5);
        assert Arrays.equals(array5, array5Sorted) : "array5 was sorted by quickSort not correct";
        quickSort(array6);
        assert Arrays.equals(array6, array6Sorted) : "array6 was sorted by quickSort not correct";
        System.out.println("\n\u001B[32mAll tests are passed!\u001B[0m");
    }

    static void twoWayBubbleSort(int[] array) {
        int length = array.length - 1,
                i = 0;
        while (i < length) {
            if (array[i] > array[i + 1]) {
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                if (i > 0) {
                    i--;
                    continue;
                }
            }
            i++;
        }
    }

    static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int leftStart, int rightStart) {
        if (array.length <= 1 || leftStart >= rightStart) return;
        int leftPos = leftStart;
        int rightPos = rightStart;
        int target = array[((rightStart - leftStart) / 2) + leftStart];

        while (leftPos <= rightPos) {
            while (array[leftPos] < target) {
                leftPos++;
            }
            while (array[rightPos] > target) {
                rightPos--;
            }
            if (leftPos <= rightPos) {
                int temp = array[leftPos];
                array[leftPos] = array[rightPos];
                array[rightPos] = temp;
                leftPos++;
                rightPos--;
            }
        }
        if (leftStart < rightPos)
            quickSort(array, leftStart, rightPos);
        if (rightStart > leftPos)
            quickSort(array, leftPos, rightStart);
    }
}
