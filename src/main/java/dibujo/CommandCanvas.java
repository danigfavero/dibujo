package dibujo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandCanvas implements CommandRule {
    @Override
    public Canvas recebidoDaLinhaDeComando(String line) {
        if (line.startsWith("C")) {
            Matcher matcher = Pattern.compile("^C (\\d+) (\\d+)$").matcher(line);
            if (matcher.find()) {
                int width = Integer.parseInt(matcher.group(1));
                int height = Integer.parseInt(matcher.group(2));

                return new Canvas(width, height);
            }
        }
        throw new RuntimeException("Invalid parameters for the create new canvas command. Should be: C <width> <height>");
    }

}
