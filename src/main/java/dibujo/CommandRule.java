package dibujo;

import java.util.regex.Matcher;

public interface CommandRule {
    void execute(Matcher matcher, Canvas canvas) throws RuntimeException;
}
