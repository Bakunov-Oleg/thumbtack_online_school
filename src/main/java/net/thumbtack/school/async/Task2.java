package net.thumbtack.school.async;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Task2 {
    //Есть два файла
    //file1.txt
    //file2.txt
    //С помощью CompletableFuture реализовать параллельное чтение, затем записать два файла с суммой и произведением этих чисел, т.е.
    public static void main(String[] args) {
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");

        CompletableFuture<List<Integer>> fReader1 = fReader(file1);
        CompletableFuture<List<Integer>> fReader2 = fReader(file2);

        fReader1.thenCombineAsync(fReader2, (a, b) -> {
            List<Integer> summ = new ArrayList<>();
            for (int i = 0; i < a.size(); i++) {
                summ.add(a.get(i) + b.get(i));
            }
            return summ;
        }).handle((r, e) -> {
            System.out.println("result " + r + "\nexception " + e);
            return r;
        });

        fReader1.thenCombineAsync(fReader2, (a, b) -> {
            List<Integer> comp = new ArrayList<>();
            for (int i = 0; i < a.size(); i++) {
                comp.add(a.get(i) * b.get(i));
            }
            return comp;
        }).handle((r, e) -> {
            System.out.println("result " + r + "\nexception " + e);
            return r;
        });
    }

    private static CompletableFuture<List<Integer>> fReader(File file) {
        List<Integer> fileArray = new ArrayList<>();
        return CompletableFuture.supplyAsync(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                String str;
                while ((str = reader.readLine()) != null) {
                    fileArray.add(Integer.parseInt(str));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return fileArray;
        });
    }

    private static File fWriter(List<Integer> values, String fileName) {
        File file = new File(fileName);
        CompletableFuture.supplyAsync(() -> {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
                for (Integer integer : values) {
                    writer.write(integer);
                    writer.newLine();
                    writer.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return file;
        }).handle((r, e) -> {
            System.out.println(r + "\nexception " + e);
            return r;
        });
        return file;
    }

}
