package net.thumbtack.school.matrix;

import java.util.*;
import java.util.stream.Collectors;

public class MatrixNonSimilarRows {

//    Дана целочисленная матрица, в которой имеется N строк, а число элементов в строке для каждой строки может быть любым, в том числе нулевым.
//    Строки назовем похожими, если совпадают множества чисел, встречающихся в этих строках.
//    Найти множество строк этой матрицы максимальной размерности, в котором все строки попарно непохожи друг на друга.
//    Из похожих строк в множество включить строку с наименьшим номером.
//
//    Пример. Матрица содержит 3 строки:
//
//            1 2 2 4 4
//            4 2 1 4
//            3 2 4 1 5 8
//
//    Первые 2 строки похожи друг на друга и непохожи на 3 строку. Ответом будет множество из 1 и 3 строк.
//
//    Создайте класс MatrixNonSimilarRows (в пакете net.thumbtack.school.matrix), в котором должны быть следующие конструктор и метод

    private int[][] matrix;
    private Set<int[]> nonSimilarRows;


    public MatrixNonSimilarRows(int[][] matrix) {
        //Создает MatrixNonSimilarRows по заданной матрице.
        this.matrix = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            this.matrix[i] = new int[matrix[i].length];
        }
        System.arraycopy(matrix, 0, this.matrix, 0, matrix.length);
    }

    public Set<int[]> getNonSimilarRows() {
        //Возвращает набор непохожих строк.
        nonSimilarRows = new HashSet<>();
        HashMap<String, int[]> hashMap = new HashMap<>();
        for (int i = 0; i < matrix.length; i++){ //FIXME логика верная и подход тоже. НО использование строк в данном случае излишне. В качестве ключей можно использовать Set.
            if(!hashMap.containsKey(Arrays.toString(Arrays.stream(matrix[i]).distinct().sorted().toArray()))){
                hashMap.put(Arrays.toString(Arrays.stream(matrix[i]).distinct().sorted().toArray()), matrix[i]);
            }
        }
        nonSimilarRows.addAll(hashMap.values());

        return nonSimilarRows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatrixNonSimilarRows that = (MatrixNonSimilarRows) o;
        return Arrays.equals(matrix, that.matrix) &&
                Objects.equals(nonSimilarRows, that.nonSimilarRows);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nonSimilarRows);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }
}
