package dibujo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLine implements CommandRule {

    @Override
    public void execute(Matcher matcher, Canvas canvas) {
        if (canvas == null) {
            throw new RuntimeException("No canvas. You should create a canvas before creating a new line.");
        }
        int startingX = Integer.parseInt(matcher.group(1));
        int startingY = Integer.parseInt(matcher.group(2));
        int endingX = Integer.parseInt(matcher.group(3));
        int endingY = Integer.parseInt(matcher.group(4));
        canvas.createNewLine(startingX, startingY, endingX, endingY);
    }
}
