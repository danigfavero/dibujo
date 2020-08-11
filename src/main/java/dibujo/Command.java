package dibujo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public enum Command {
    CANVAS("C", "^C (\\d+) (\\d+)$"),
    RECTANGLE("R", "^R (\\d+) (\\d+) (\\d+) (\\d+)$"),
    LINE("L","^L (\\d+) (\\d+) (\\d+) (\\d+)$"),
    BACKGROUND("B","^B (\\d+) (\\d+) (\\w+)$"),
    QUIT("Q");

    final String character;
    final String pattern;

    Command(String character, String pattern) {
        this.character = character;
        this.pattern = pattern;
    }

    Command(String character){
        this(character,null);
    }

    public String getCharacter(){
        return character;
    }

    public String getPattern(){
        return pattern;
    }
    
    public Matcher find(String input) {
        return Pattern.compile(getPattern()).matcher(input);
    }

    public static boolean containsCharacter(String character){
        return Stream.of(Command.values()).anyMatch(c -> c.getCharacter().startsWith(character));
    }

}
