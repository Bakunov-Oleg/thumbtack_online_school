package net.thumbtack.school.colors;

public interface Colored {
    void setColor(Color color) throws ColorException;

    Color getColor();

    public default void setColor(String colorString) throws ColorException {
        if (colorString == null) throw new ColorException(ColorErrorCode.NULL_COLOR);
        try {
            setColor(Color.valueOf(colorString));
        } catch (NullPointerException ex) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        } catch (IllegalArgumentException ex) {
            throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING);
        }
    }
}
