package dibujo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public enum Command {
    CANVAS("C", Pattern.compile("^C (\\d+) (\\d+)$").matcher("C")),
    RECTANGLE("R", Pattern.compile("^R (\\d+) (\\d+) (\\d+) (\\d+)$").matcher("R")),
    LINE("L",Pattern.compile("^L (\\d+) (\\d+) (\\d+) (\\d+)$").matcher("L")),
    BACKGROUND("B",Pattern.compile("^B (\\d+) (\\d+) (\\w+)$").matcher("B")),
    QUIT("Q");

    final String character;
    final Matcher matcher;

    Command(String character, Matcher matcher) {
        this.character = character;
        this.matcher = matcher;
    }

    Command(String character){
        this(character,null);
    }

    public String getCharacter(){
        return character;
    }

    public Matcher getMatcher(){
        return matcher;
    }

    public static boolean containsCharacter(String character){
        return Stream.of(Command.values()).anyMatch(c -> c.getCharacter().startsWith(character));
    }

}
