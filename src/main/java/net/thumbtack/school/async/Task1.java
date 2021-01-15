package net.thumbtack.school.async;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class Task1 {
    //Реализовать pipeline для параллельного вычисления коэффициента корреляции двух числовых массивов
    //http://cito-web.yspu.org/link1/metod/met125/node35.html с помощью CompletableFuture

    public static void main(String[] args) {

        int[] x = {19, 32, 33, 44, 28, 35, 39, 39, 44, 44, 24, 37, 29, 40, 42, 32, 48, 42, 33, 47};
        int[] y = {17, 7, 17, 28, 27, 31, 20, 17, 35, 43, 10, 28, 13, 43, 45, 24, 45, 26, 16, 26};
        int n = 20;

        getCorrelationCoefficient(x, y, n);
    }

    public static void getCorrelationCoefficient(int[] x, int[] y, int n) {
        CompletableFuture<Integer> sumX = CompletableFuture.supplyAsync(() -> Arrays.stream(x).sum());
        CompletableFuture<Integer> sumY = CompletableFuture.supplyAsync(() -> Arrays.stream(y).sum());

        CompletableFuture<Integer> sumXX = CompletableFuture.supplyAsync(() -> {
            int[] xx = Arrays.copyOf(x, x.length);
            for (int i = 0; i < x.length; i++) {
                xx[i] = x[i] * x[i];
            }
            return Arrays.stream(xx).sum() * n;
        });
        CompletableFuture<Integer> sumYY = CompletableFuture.supplyAsync(() -> {
            int[] yy = Arrays.copyOf(y, y.length);
            for (int i = 0; i < y.length; i++) {
                yy[i] = y[i] * y[i];
            }
            return Arrays.stream(yy).sum() * n;
        });
        CompletableFuture<Integer> sumXY = CompletableFuture.supplyAsync(() -> {
            int[] xy = new int[n];
            for (int i = 0; i < n; i++) {
                xy[i] = x[i] * y[i];
            }
            return Arrays.stream(xy).sum() * n;
        });


        CompletableFuture<Integer> numerator = sumX.thenCombineAsync(sumY, (q, w) -> q * w).thenCombineAsync(sumXY, (r, t) -> t - r);
        CompletableFuture<Double> denominator = sumX.thenCombineAsync(sumXX, (a, z) -> Math.sqrt(z - (a * a)));
        CompletableFuture<Double> k = sumY.thenCombineAsync(sumYY, (a, z) -> z - (a * a))
                .thenCombineAsync(denominator, (q, w) -> q * w)
                .thenCombineAsync(numerator, (r, t) -> t / r)
                .handle((result, e) -> {
                    System.out.println(result);
                    return result;
                });
    }
}
