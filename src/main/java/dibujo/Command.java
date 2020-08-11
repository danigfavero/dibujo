package dibujo;

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
}
