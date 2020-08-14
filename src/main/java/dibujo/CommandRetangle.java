package dibujo;

import java.util.regex.Matcher;

public class CommandRetangle implements CommandRule {

    @Override
    public void execute(Matcher matcher, Canvas canvas) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before creating a new rectangle.");
        }
        int upperLeftCornerX = Integer.parseInt(matcher.group(1));
        int upperLeftCornerY = Integer.parseInt(matcher.group(2));
        int lowerRightCornerX = Integer.parseInt(matcher.group(3));
        int lowerRightCornerY = Integer.parseInt(matcher.group(4));
        canvas.createNewRectangle(upperLeftCornerX, upperLeftCornerY, lowerRightCornerX, lowerRightCornerY);
    }
}
