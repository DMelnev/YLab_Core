/**
 * class Main
 *
 * @author Melnev Dmitry
 * @version 2022/08
 */
package Task1_1;

public class Main {
    private static final int MIN_RANDOM_RANGE = -100;
    private static final int MAX_RANDOM_RANGE = 900;
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {

        int[][] array = new int[10][20];

        randomFillArray(array);

        printArray(array);

        System.out.printf("""
                                        
                        Minimum value is %d
                        Middle value is %d
                        Weighted average value is %f
                        Maximum value is %d
                                        
                        """,
                findMin(array),
                findMid(array),
                findArithmeticMean(array),
                findMax(array)
        );

        intRandomGeneratorTest();
        findMinTest();
        findMaxTest();
        findMidTest();
        randomFillArrayTest();
    }

    private static void randomFillArray(int[][] array) {
        int lengthX = array.length;
        for (int x = 0; x < lengthX; x++) {
            int lengthY = array[x].length;
            for (int y = 0; y < lengthY; y++) {
                array[x][y] = intRandomGenerator(MIN_RANDOM_RANGE, MAX_RANDOM_RANGE);
            }
        }
    }

    private static int[] keysGenerator() {
        int[] array = new int[18];
        int counter = 0;
        long key = (long) (Math.PI * 1e17D);
        while (key > 0) {
            array[counter++] = (int) (key % 10);
            key /= 10;
        }
        return array;
    }

    public static int intRandomGenerator(int rangeMin, int rangeMax) {
        if (rangeMax < rangeMin) throw new IllegalArgumentException("MIN can't be greater than MAX");
        int[] keys = keysGenerator();
        int firstRandom = keys[(int) (System.nanoTime() / 100 % keys.length)];
        int range = Math.abs(rangeMax - rangeMin) + 1;
        return (int) (((System.nanoTime() / 10) << firstRandom) % range) + rangeMin;
    }

    public static void printArray(int[][] array) {
        int maxSpaces = (int) Math.log10(Math.max(Math.abs(MAX_RANDOM_RANGE), Math.abs(MIN_RANDOM_RANGE))) + 3;
        String formatSpaces = "%" + maxSpaces + "d";
        int mid = findMid(array);
        for (int[] line : array) {
            for (int cell : line) {
                if (cell > mid) System.out.print(ANSI_RED);
                else if (cell < mid) System.out.print(ANSI_BLUE);
                else System.out.print(ANSI_RESET);
                System.out.printf(formatSpaces, cell);
            }
            System.out.println(ANSI_RESET);
        }
    }

    public static int findMin(int[][] array) {
        int min = Integer.MAX_VALUE;
        boolean arrayEmpty = true;
        for (int[] line : array) {
            for (int cell : line) {
                min = Math.min(cell, min);
                arrayEmpty = false;
            }
        }
        if (arrayEmpty) return 0;
        return min;
    }

    public static int findMax(int[][] array) {
        int max = Integer.MIN_VALUE;
        boolean arrayEmpty = true;
        for (int[] line : array) {
            for (int cell : line) {
                max = Math.max(cell, max);
                arrayEmpty = false;
            }
        }
        if (arrayEmpty) return 0;
        return max;
    }

    public static int findMid(int[][] array) {
        int mid = (findMax(array) + findMin(array)) / 2;
        int span = Integer.MAX_VALUE;
        int midResult = mid;
        for (int[] line : array) {
            for (int cell : line) {
                if (Math.abs(cell - mid) < span) {
                    span = Math.abs(cell - mid);
                    midResult = cell;
                }
            }
        }
        return midResult;
    }

    public static float findArithmeticMean(int[][] array) {
        int sum = 0, counter = 0;
        for (int[] line : array) {
            for (int cell : line) {
                sum += cell;
                counter++;
            }
        }
        if (counter == 0) throw new NullPointerException("Array have to be fill");
        return (float) sum / (float) counter;
    }


    //@Test
    static void intRandomGeneratorTest() {
        int[] buffer = new int[]{0, 2, 4, 7};
        for (int rangeMin = -1000; rangeMin < 1001; rangeMin += 200) {
            for (int rangeMax = -1000; rangeMax < 1001; rangeMax += 100) {
                if (rangeMax > rangeMin) {
                    for (int x = 0; x < 4; x++) {
                        buffer[x] = intRandomGenerator(rangeMin, rangeMax);
                        assert buffer[x] >= rangeMin && buffer[x] <= rangeMax : "generated digit out of range";
                    }
                    assert buffer[0] != buffer[2] || buffer[0] != buffer[1] || buffer[0] != buffer[3] : "four digits in a row are the same";
                }
            }
        }

        assert intRandomGenerator(Integer.MIN_VALUE, Integer.MIN_VALUE) == Integer.MIN_VALUE : "generated min (-2147483648) is not correct";
        assert intRandomGenerator(Integer.MAX_VALUE, Integer.MAX_VALUE) == Integer.MAX_VALUE : "generated max (2147483647) is not correct";
        assert intRandomGenerator(0, 0) == 0 : "generated zero is not correct";
        System.out.println(ANSI_GREEN + "intRandomGeneratorTest passed successful!" + ANSI_RESET);
    }

    //@Test
    static void findMinTest() {
        int[][] array = new int[][]{{1, 2, 3}, {1, -2, 5}, {1, 4}};
        assert findMin(array) == -2 : "minimum (-2) was found not correct";
        array = new int[][]{{1, 2, Integer.MIN_VALUE}, {1, -2, 5}, {1, 4}};
        assert findMin(array) == Integer.MIN_VALUE : "minimum (-2147483648) was found not correct";
        array = new int[][]{{1, 2, Integer.MAX_VALUE}, {1, 2, 0}, {1, 0}};
        assert findMin(array) == 0 : "minimum (0) was found not correct";
        array = new int[][]{{-1, -2, -3}, {-1, -2, -5}, {-1, 4}};
        assert findMin(array) == -5 : "minimum (-5) was found not correct";
        array = new int[][]{{}, {5}, {}};
        assert findMin(array) == 5 : "minimum (5) was found not correct";
        array = new int[][]{{}, {}, {}};
        assert findMin(array) == 0 : "minimum (0) was found not correct";
        System.out.println(ANSI_GREEN + "findMinTest passed successful!" + ANSI_RESET);
    }

    //@Test
    static void findMaxTest() {
        int[][] array = new int[][]{{1, 2, 3}, {1, -2, 5}, {1, 4}};
        assert findMax(array) == 5 : "maximum (5) was found not correct";
        array = new int[][]{{1, 2, Integer.MIN_VALUE}, {1, -2, 18}, {18, 4}};
        assert findMax(array) == 18 : "maximum (18) was found not correct";
        array = new int[][]{{1, 2, Integer.MAX_VALUE}, {1, 2, 5}, {1, 0}};
        assert findMax(array) == Integer.MAX_VALUE : "maximum (2147483647) was found not correct";
        array = new int[][]{{-1, -2, -3}, {-1, -2, -5}, {-1, -4}};
        assert findMax(array) == -1 : "maximum (-1) was found not correct";
        array = new int[][]{{}, {5}, {}};
        assert findMax(array) == 5 : "maximum (5) was found not correct";
        array = new int[][]{{}, {}, {}};
        assert findMax(array) == 0 : "maximum (0) was found not correct";
        System.out.println(ANSI_GREEN + "findMaxTest passed successful!" + ANSI_RESET);
    }

    //@Test
    static void findMidTest() {
        int[][] array = new int[][]{{1, 2, 3}, {1, -2, 5}, {1, 4}};
        assert findMid(array) == 1 : "middle (1) was found not correct";
        array = new int[][]{{1, 2, Integer.MIN_VALUE}, {1, -2, 18}, {18, 4}};
        assert findMid(array) == -2 : "middle (-2) was found not correct";
        array = new int[][]{{1, 2, Integer.MAX_VALUE}, {1, 2, 5}, {1, 0}};
        assert findMid(array) == 5 : "middle (5) was found not correct";
        array = new int[][]{{-1, -2, -3}, {-1, -2, -5}, {-1, -4}};
        assert findMid(array) == -3 : "middle (-3) was found not correct";
        array = new int[][]{{}, {5}, {}};
        assert findMid(array) == 5 : "middle (5) was found not correct";
        array = new int[][]{{}, {}, {}};
        assert findMid(array) == 0 : "middle (0) was found not correct";
        System.out.println(ANSI_GREEN + "findMidTest passed successful!" + ANSI_RESET);
    }

    //@Test
    static void randomFillArrayTest() {
        int[][] array = new int[5][5];
        randomFillArray(array);
        assert array[0][0] != array[0][1] || array[0][0] != array[1][0] : "three digits are the same";
        assert array[1][3] != array[2][1] || array[4][0] != array[1][2] : "four digits are the same";
        assert array[2][2] >= MIN_RANDOM_RANGE : "filling out of range";
        assert array[4][4] >= MIN_RANDOM_RANGE : "filling out of range";
        assert array[4][4] <= MAX_RANDOM_RANGE : "filling out of range";
        assert array[3][4] <= MAX_RANDOM_RANGE : "filling out of range";
        System.out.println(ANSI_GREEN + "randomFillArrayTest passed successful!" + ANSI_RESET);

    }

}

