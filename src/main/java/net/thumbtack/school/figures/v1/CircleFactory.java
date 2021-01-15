package net.thumbtack.school.figures.v1;

public class CircleFactory {

    private static int countCircle = 0;

    public static Circle createCircle(Point2D center, int radius) {
        //Создает Circle по координатам центра и значению радиуса.
        Circle pCircle = new Circle(center, radius);
        countCircle ++;
        return pCircle;
    }

    public static int getCircleCount() {
        //Возвращает количество Circle, созданных с помощью метода createCircle     .
        return  countCircle;
    }

    public static void reset() {
        //Устанавливает количество Circle, созданных с помощью метода createCircle, равным 0 (иными
        //словами, реинициализирует фабрику).
        countCircle = 0;
    }

}
