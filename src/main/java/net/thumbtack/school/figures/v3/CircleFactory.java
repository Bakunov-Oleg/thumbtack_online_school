package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;

public class CircleFactory {

    private static int countCircle = 0;

    public static Circle createCircle(Point2D center, int radius, Color color) throws ColorException {
        //Создает Circle по координатам центра и значению радиуса.
        Circle pCircle = new Circle(center, radius, color);
        countCircle++;
        return pCircle;
    }

    public static Circle createCircle(Point2D center, int radius, String colorString) throws ColorException {
        //Создает Circle по координатам центра и значению радиуса.
        return createCircle(center, radius, Color.colorFromString(colorString));
    }

    public static int getCircleCount() {
        //Возвращает количество Circle, созданных с помощью метода createCircle.
        return countCircle;
    }

    public static void reset() {
        //Устанавливает количество Circle, созданных с помощью метода createCircle, равным 0 (иными
        //словами, реинициализирует фабрику).
        countCircle = 0;
    }

}
