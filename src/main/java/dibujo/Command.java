package dibujo;
import java.util.stream.Stream;

public enum Command {
    CANVAS("C"),
    RECTANGLE("R"),
    LINE("L"),
    BACKGROUND("B"),
    QUIT("Q");

    final String character;
    
    Command(String character) {
        this.character = character;
    }

    public String getCharacter(){
        return character;
    }

    public static boolean containsCharacter(String character){
        return Stream.of(Command.values()).anyMatch(c -> c.getCharacter().startsWith(character));
    }

}
