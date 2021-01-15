package net.thumbtack.school.file;

import com.google.gson.Gson;
import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.figures.v3.Point2D;
import net.thumbtack.school.figures.v3.Rectangle;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileService {

    public static void writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException {
        //Записывает массив байтов в двоичный файл, имя файла задается текстовой строкой.
        try (FileOutputStream pFile = new FileOutputStream(new File(fileName))) {
            pFile.write(array);
        }
    }

    public static void writeByteArrayToBinaryFile(File file, byte[] array) throws IOException {
        //Записывает массив байтов в двоичный файл, имя файла задается  экземпляром класса File.
        try (FileOutputStream pFile = new FileOutputStream(file)) {
            pFile.write(array);
        }
    }

    public static byte[] readByteArrayFromBinaryFile(String fileName) throws IOException {
        //Читает массив байтов из двоичного файла, имя файла задается текстовой строкой.
        byte[] array = null;
        try (FileInputStream pFile = new FileInputStream((new File(fileName)))) {
            array = new byte[pFile.available()];
            pFile.read(array);
        }
        return array;
    }

    public static byte[] readByteArrayFromBinaryFile(File file) throws IOException {
        //Читает массив байтов из двоичного файла, имя файла задается экземпляром класса File.
        byte[] array = null;
        try (FileInputStream pFile = new FileInputStream((new File(file.getName())))) {
            array = new byte[pFile.available()];
            pFile.read(array);
        }
        return array;
    }

    public static byte[] writeAndReadByteArrayUsingByteStream(byte[] array) throws IOException {
        //Записывает массив байтов в ByteArrayOutputStream, создает на основе данных
        // в полученном  ByteArrayOutputStream экземпляр ByteArrayInputStream и читает из ByteArrayInputStream байты с четными номерами.
        // Возвращает массив прочитанных байтов.
        try (ByteArrayOutputStream outBuf = new ByteArrayOutputStream()) {
            outBuf.write(array);
            try (ByteArrayInputStream inBuf = new ByteArrayInputStream(outBuf.toByteArray())) {
                byte[] inArray = new byte[inBuf.available() / 2];
                int inArrayPos = 0;
                while (inBuf.available() > 0) {
                    inBuf.read(inArray, inArrayPos, 1);
                    inBuf.skip(1);
                    inArrayPos++;
                }
                return inArray;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static void writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        //Записывает массив байтов в двоичный файл, используя буферизованный вывод, имя файла задается текстовой строкой.
        try (BufferedOutputStream pFile = new BufferedOutputStream(new FileOutputStream(new File(fileName)))) {
            pFile.write(array);
        }
    }

    public static void writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        //Записывает массив байтов в двоичный файл, используя буферизованный вывод, имя файла задается экземпляром класса File.
        try (BufferedOutputStream pFile = new BufferedOutputStream(new FileOutputStream(file))) {
            pFile.write(array);
        }
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        //Читает массив байтов из двоичного файла, используя буферизованный ввод, имя файла задается текстовой строкой.
        try (BufferedInputStream pFile = new BufferedInputStream(new FileInputStream(new File(fileName)))) {
            byte[] array = new byte[pFile.available()];
            pFile.read(array);
            return array;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        //Читает массив байтов из двоичного файла, используя буферизованный ввод, имя файла задается экземпляром класса File.
        try (BufferedInputStream pFile = new BufferedInputStream(new FileInputStream(file))) {
            byte[] array = new byte[pFile.available()];
            pFile.read(array);
            return array;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static void writeRectangleToBinaryFile(File file, Rectangle rect) throws IOException {
        //Записывает Rectangle в двоичный файл, имя файла задается экземпляром класса File. Поле цвета не записывать.
        try (DataOutputStream pDos = new DataOutputStream(new FileOutputStream(file))) {
            pDos.writeInt(rect.getTopLeft().getX());
            pDos.writeInt(rect.getTopLeft().getY());
            pDos.writeInt(rect.getBottomRight().getX());
            pDos.writeInt(rect.getBottomRight().getY());
        }
    }

    public static Rectangle readRectangleFromBinaryFile(File file) throws IOException {
        //Читает данные для Rectangle из двоичного файла и создает на их основе экземпляр Rectangle,
        // имя файла задается экземпляром класса File. Предполагается, что данные в файл записаны в формате предыдущего упражнения.
        // Поскольку файл не содержит цвета, установить в Rectangle цвет “RED”.
        try (DataInputStream pDis = new DataInputStream(new FileInputStream(file))) {
            Rectangle pRectangle = new Rectangle(pDis.readInt(), pDis.readInt(), pDis.readInt(), pDis.readInt(), "RED");
            return pRectangle;
        } catch (ColorException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeRectangleArrayToBinaryFile(File file, Rectangle[] rects) throws IOException {
        //Записывает массив из Rectangle в двоичный файл, имя файла задается экземпляром класса File. Поле цвета не записывать.
        try (DataOutputStream pDos = new DataOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < rects.length; i++) {
                pDos.writeInt(rects[i].getTopLeft().getX());
                pDos.writeInt(rects[i].getTopLeft().getY());
                pDos.writeInt(rects[i].getBottomRight().getX());
                pDos.writeInt(rects[i].getBottomRight().getY());
            }
        }
    }

    public static Rectangle[] readRectangleArrayFromBinaryFileReverse(File file) throws IOException {
        //Предполагается, что данные в файл записаны в формате предыдущего упражнения.
        // Метод читает вначале данные о последнем записанном в файл Rectangle и создает на их основе экземпляр Rectangle,
        // затем читает данные следующего с конца Rectangle и создает на их основе экземпляр Rectangle и т.д.
        // вплоть до данных для самого первого записанного в файл Rectangle.
        // Из созданных таким образом экземпляров Rectangle создается массив, который и возвращает метод.
        // Поскольку файл не содержит цвета, установить во всех Rectangle цвет “RED”.
        try (RandomAccessFile pFile = new RandomAccessFile(file, "r")) {
            int countRect = (int) (pFile.length() / 16);
            Rectangle[] rectArray = new Rectangle[countRect];
            for (int i = 1; i <= (int) (pFile.length() / 16); i++) {
                pFile.seek(pFile.length() - i * 16);
                rectArray[i - 1] = new Rectangle(pFile.readInt(), pFile.readInt(), pFile.readInt(), pFile.readInt(), "RED");
            }
            return rectArray;
        } catch (ColorException e) {
            e.printStackTrace();
        }
        return new Rectangle[0];
    }

    public static void writeRectangleToTextFileOneLine(File file, Rectangle rect) throws IOException {
        //Записывает Rectangle в текстовый файл в одну строку, имя файла задается экземпляром класса File.
        // Поля в файле разделяются пробелами. Поле цвета не записывать.
        try (RandomAccessFile pFile = new RandomAccessFile(file, "rw")) {
            String rectangleStr = String.valueOf(rect.getTopLeft().getX()) + " " + String.valueOf(rect.getTopLeft().getY()) + " " +
                    String.valueOf(rect.getBottomRight().getX()) + " " + String.valueOf(rect.getBottomRight().getY());
            pFile.writeBytes(rectangleStr);
        }
    }

    public static Rectangle readRectangleFromTextFileOneLine(File file) throws IOException {
        //Читает данные для Rectangle из текстового файла и создает на их основе экземпляр Rectangle, имя файла задается экземпляром класса File.
        // Предполагается, что данные в файл записаны в формате предыдущего упражнения. Поскольку файл не содержит цвета, установить в Rectangle цвет “RED”
        try (RandomAccessFile pFile = new RandomAccessFile(file, "r")) {
            Point2D topLeft = new Point2D();
            Point2D bottomRight = new Point2D();
            String rectangleStr = pFile.readLine();
            String[] rectParam = rectangleStr.split(" ");
            topLeft.setX(Integer.parseInt(rectParam[0]));
            topLeft.setY(Integer.parseInt(rectParam[1]));
            bottomRight.setX(Integer.parseInt(rectParam[2]));
            bottomRight.setY(Integer.parseInt(rectParam[3]));
            return new Rectangle(topLeft, bottomRight, "RED");
        } catch (ColorException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeRectangleToTextFileFourLines(File file, Rectangle rect) throws IOException {
        //Записывает Rectangle в текстовый файл, каждое число в отдельной строке , имя файла задается экземпляром класса File. Поле цвета не записывать.
        try (RandomAccessFile pFile = new RandomAccessFile(file, "rw")) {
            String rectangleStr = rect.getTopLeft().getX() + "\r\n" + rect.getTopLeft().getY() + "\r\n" +
                    rect.getBottomRight().getX() + "\r\n" + rect.getBottomRight().getY();
            pFile.writeBytes(rectangleStr);
        }
    }

    public static Rectangle readRectangleFromTextFileFourLines(File file) throws IOException {
        //Читает данные для Rectangle из текстового файла и создает на их основе экземпляр Rectangle, имя файла задается экземпляром класса File.
        // Предполагается, что данные в файл записаны в формате предыдущего упражнения. Поскольку файл не содержит цвета, установить в Rectangle цвет “RED”
        try (RandomAccessFile pFile = new RandomAccessFile(file, "r")) {
            Point2D topLeft = new Point2D();
            Point2D bottomRight = new Point2D();
            topLeft.setX(Integer.parseInt(pFile.readLine()));
            topLeft.setY(Integer.parseInt(pFile.readLine()));
            bottomRight.setX(Integer.parseInt(pFile.readLine()));
            bottomRight.setY(Integer.parseInt(pFile.readLine()));
            return new Rectangle(topLeft, bottomRight, "RED");
        } catch (ColorException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException {
        //Записывает Trainee в текстовый файл в одну строку в кодировке UTF-8, имя файла задается экземпляром класса File.
        // Имя, фамилия и оценка в файле разделяются пробелами.
        try (OutputStreamWriter pFile = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
            String traineeStr = trainee.getFirstName() + " " + trainee.getLastName() + " " +
                    trainee.getRating();
            pFile.write(traineeStr);
        }
    }

    public static Trainee readTraineeFromTextFileOneLine(File file) throws IOException {
        //Читает данные для Trainee из текстового файла и создает на их основе экземпляр Trainee, имя файла задается экземпляром класса File.
        // Предполагается, что данные в файл записаны в формате предыдущего упражнения.
        try (BufferedReader pFile = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String traineeStr = pFile.readLine();
            String[] traineeParam = traineeStr.split(" ");
            return new Trainee(traineeParam[0], traineeParam[1], Integer.parseInt(traineeParam[2]));
        } catch (TrainingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException {
        //Записывает Trainee в текстовый файл в кодировке UTF-8, каждое поле в отдельной строке, имя файла задается экземпляром класса File.
        try (OutputStreamWriter pFile = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) { //FIXME обратите внимание на исправления в методе. Посмотрите схожие моменты далее по коду.
            String traineeStr = trainee.getFirstName() + "\r\n" + trainee.getLastName() + "\r\n" +
                    trainee.getRating();
            pFile.write(traineeStr);
        }
    }

    public static Trainee readTraineeFromTextFileThreeLines(File file) throws IOException {
        //Читает данные для Trainee из текстового файла и создает на их основе экземпляр Trainee, имя файла задается экземпляром класса File.
        // Предполагается, что данные в файл записаны в формате предыдущего упражнения.
        try (BufferedReader pFile = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            return new Trainee(pFile.readLine(), pFile.readLine(), Integer.parseInt(pFile.readLine()));
        } catch (TrainingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException {
        //Сериализует Trainee в двоичный файл, имя файла задается экземпляром класса File.
        try (ObjectOutputStream pObj = new ObjectOutputStream(new FileOutputStream(file))) {
            pObj.writeObject(trainee);
        }
    }

    public static Trainee deserializeTraineeFromBinaryFile(File file) throws IOException {
        //Десериализует Trainee из двоичного файла, имя файла задается экземпляром класса File.
        // Предполагается, что данные в файл записаны в формате предыдущего упражнения.
        try (ObjectInputStream pObj = new ObjectInputStream(new FileInputStream(file))) {
            Trainee pTrainee = (Trainee) pObj.readObject();
            return pTrainee;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String serializeTraineeToJsonString(Trainee trainee) {
        //Сериализует Trainee в формате Json в текстовую строку.
        return new String(new Gson().toJson(trainee)); //FIXME заведите статический экземпляр Gson для всего класса.
    }

    public static Trainee deserializeTraineeFromJsonString(String json) {
        //Десериализует Trainee из текстовой строки с Json-представлением Trainee.
        return (Trainee) new Gson().fromJson(json, Trainee.class);
    }

    public static void serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
        //Сериализует Trainee в формате Json в файл, имя файла задается экземпляром класса File.
        try (BufferedWriter pBW = new BufferedWriter(new FileWriter(file))) {
            new Gson().toJson(trainee, pBW);
        }
    }

    public static Trainee deserializeTraineeFromJsonFile(File file) throws IOException {
        //Десериализует Trainee из файла с Json-представлением Trainee, имя файла задается экземпляром класса File.
        try (BufferedReader pBR = new BufferedReader(new FileReader(file))) {
            return (Trainee) new Gson().fromJson(pBR, Trainee.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
