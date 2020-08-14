package dibujo;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public enum Command {
    CANVAS("C", new CommandCanvas(), "^C (\\d+) (\\d+)$"),
    RECTANGLE("R", new CommandRetangle(), "^R (\\d+) (\\d+) (\\d+) (\\d+)$"),
    LINE("L", new CommandLine(), "^L (\\d+) (\\d+) (\\d+) (\\d+)$"),
    BACKGROUND("B",new CommandBackground(), "^B (\\d+) (\\d+) (\\w+)$"),
    QUIT("Q");

    final String character;
    final String pattern;
    private CommandRule commandRule;

    Command(String character, CommandRule commandRule, String pattern) {
        this.commandRule = commandRule;
        this.character = character;
        this.pattern = pattern;
    }

    Command(String character){
        this(character,null, null);
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

    public CommandRule getCommandRule() {
        return commandRule;
    }

    public static Optional<Command> findByCommand(String line){
        return Stream.of(Command.values()).filter(c -> line.startsWith(c.getCharacter())).findFirst();
    }

    public Canvas execute(String line){
        return commandRule.recebidoDaLinhaDeComando(line);
    }
}
