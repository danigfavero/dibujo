package dibujo;

import java.util.regex.Matcher;

public class CommandBackground implements CommandRule {
    @Override
    public void execute(Matcher matcher, Canvas canvas) {
        int startingX = Integer.parseInt(matcher.group(1));
        int startingY = Integer.parseInt(matcher.group(2));
        String colorCharacter = matcher.group(3);

        Position startingPositionToFill = new Position(startingX, startingY);
        canvas.fill(startingPositionToFill, colorCharacter);
    }
}
