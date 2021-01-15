package net.thumbtack.school.introduction;

public class FirstSteps {

    public int sum(int x, int y) {
        return x + y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    public int div(int x, int y) {
        return x / y;
    }

    public int mod(int x, int y) {
        return x % y;
    }

    public boolean isEqual(int x, int y) {
        return x == y;
    }

    public boolean isGreater(int x, int y) {
        return x > y;
    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {
        return x >= xLeft && x <= xRight && y >= yTop && y <= yBottom;
    }

    public int sum(int[] array) {
        int result = 0;
        if (array.length == 0) return 0;
        for (int i = 0; i < array.length; i++) {
            result += array[i];
        }
        return result;

    }

    public int mul(int[] array) {
        int result = 1;
        if (array.length == 0) return 0;
        for (int i = 0; i < array.length; i++) {
            result = result * array[i];
        }
        return result;
    }

    public int min(int[] array) {
        int result = Integer.MAX_VALUE;
        if (array.length == 0) return result;
        for (int i = 0; i < array.length; i++) {
            if (result > array[i]) result = array[i];
        }
        return result;
    }

    public int max(int[] array) {
        int result = Integer.MIN_VALUE;
        if (array.length == 0) return result;
        for (int i = 0; i < array.length; i++) {
            if (result < array[i]) result = array[i];
        }
        return result;
    }

    public double average(int[] array) {
        double result = 0;
        if (array.length == 0) return 0;
        for (int i = 0; i < array.length; i++) {
            result += array[i];
        }
        result = result / array.length;
        return result;
    }

    public boolean isSortedDescendant(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] <= array[i]) {
                return false;
            }
        }
        return true;
    }

    public void cube(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.pow(array[i], 3);
        }
    }

    public boolean find(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) return true;
        }
        return false;
    }

    public void reverse(int[] array) {
        int currentEndElementIndex = array.length - 1;
        int tempVal;
        for (int i = 0; i < array.length / 2; i++) {
            tempVal = array[i];
            array[i] = array[currentEndElementIndex - i];
            array[currentEndElementIndex - i] = tempVal;
        }
    }

    public boolean isPalindrome(int[] array) {
        int currentEndElementIndex = array.length - 1;
        for (int i = 0; i < array.length / 2; i++) {
            if (array[i] != array[currentEndElementIndex - i]) return false; //FIXME можно сразу вернуть результат
        }
        return true;
    }

    public int sum(int[][] matrix) {
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result += matrix[i][j];
            }
        }
        return result;
    }

    public int max(int[][] matrix) {
        int result = Integer.MIN_VALUE;

        if (matrix.length == 0) return Integer.MIN_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (result < matrix[i][j]) result = matrix[i][j];
            }
        }
        return result;
    }

    public int diagonalMax(int[][] matrix) {
        int result = Integer.MIN_VALUE;

        if (matrix.length == 0) return Integer.MIN_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            if (result < matrix[i][i]) result = matrix[i][i];

        }
        return result;
    }

    public boolean isSortedDescendant(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (isSortedDescendant(matrix[i]) != true) {
                return false;
            }
        }
        return true;
    }

}

