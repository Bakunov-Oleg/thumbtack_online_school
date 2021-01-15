package net.thumbtack.school.colors;

public enum Color {
    RED, GREEN, BLUE;

    public static Color colorFromString(String colorString) throws ColorException{
        if (colorString == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        try {
            return Color.valueOf(colorString);
        } catch (NullPointerException ex){
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        } catch (IllegalArgumentException ex){
            throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
        }
    }
}
