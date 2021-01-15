package net.thumbtack.school.cars;

import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorErrorCode;
import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.colors.Colored;

import java.util.Objects;

public class Car implements Colored {
    private Color color;
    private String model;
    private int weight;
    private int maxSpeed;

    public Car(String model, int weight, int maxSpeed, Color color) throws ColorException {
        //Создает автомобиль указанной модели, веса (в килограммах),максимальной скорости и цвета.
        setModel(model);
        setWeight(weight);
        setMaxSpeed(maxSpeed);
        setColor(color);
    }

    public Car(String model, int weight, int maxSpeed, String colorString) throws ColorException {
        //Создает автомобиль указанной модели, веса (в килограммах),максимальной скорости и цвета.
        this(model, weight, maxSpeed, Color.colorFromString(colorString));
    }

    public String getModel() {
        //Возвращает модель автомобиля.
        return this.model;
    }

    public void setModel(String model) {
        //Устанавливает модель автомобиля.
        this.model = model;
    }

    public int getWeight() {
        //Возвращает вес автомобиля.
        return this.weight;
    }

    public void setWeight(int weight) {
        //Устанавливает вес автомобиля.
        this.weight = weight;
    }

    public int getMaxSpeed() {
        //Возвращает максимальную скорость автомобиля.
        return this.maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        //Устанавливает максимальную скорость автомобиля.
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void setColor(Color color) throws ColorException {
        //Устанавливает цвет фигуры
        if (color == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        try {
            this.color = color;
        } catch (NullPointerException ex) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        } catch (IllegalArgumentException ex) {
            throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
        }
    }

    @Override
    public Color getColor() {
        //Возвращает цвет фигуры
        return this.color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {
        //Устанавливает цвет фигуры
        setColor(Color.colorFromString(colorString));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return weight == car.weight &&
                maxSpeed == car.maxSpeed &&
                color == car.color &&
                Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, model, weight, maxSpeed);
    }
}

