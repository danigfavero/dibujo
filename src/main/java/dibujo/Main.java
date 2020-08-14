package dibujo;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private Canvas canvas;

    public void run(InputStream in, PrintStream out, PrintStream err) {
        try (Scanner scanner = new Scanner(in)) {
            out.print("\nenter command: ");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                try {
                    if (line.startsWith("C")) {
                        Matcher matcher = Command.CANVAS.find(line);
                        if (matcher.find()) {
                            canvas = Command.findByCommand(line).get().execute(line);
                        } else {
                            throw new RuntimeException("Invalid parameters for the create new canvas command. Should be: C <width> <height>");
                        }
                    } else if (line.startsWith("L")) {

                        Matcher matcher = Command.LINE.find(line);
                        if (matcher.find()) {
                            Command.LINE.execute(matcher, canvas);

                        } else {
                            throw new RuntimeException("Invalid parameters for the create new line command. Should be: L <starting x> <starting y> <ending x> <ending y>");
                        }

                    } else if (line.startsWith("R")) {
                        Matcher matcher = Command.RECTANGLE.find(line);
                        if (matcher.find()) {
                            Command.RECTANGLE.execute(matcher, canvas);
                        } else {
                            throw new RuntimeException("Invalid parameters for the create new rectangle command. Should be: L <upper left corner x> <upper left corner y> <lower right corner x> <lower right corner y>");
                        }

                    } else if (line.startsWith("B")) {
                        Matcher matcher = Command.BACKGROUND.find(line);
                        if (matcher.find()) {
                            Command.BACKGROUND.execute(matcher, canvas);
                        } else {
                            throw new RuntimeException("Invalid parameters for the bucket fill command. Should be: B <starting x> <starting y> <color>");
                        }

                    } else if (line.startsWith("Q")) {
                        System.out.println("Bye bye!");
                        System.exit(0);
                    } else {
                        err.println("Invalid command: " + line + "\n");
                    }

                    Draw.draw(out, canvas);

                } catch (Exception ex) {
                    err.println(ex.getMessage()+"\n");
                    out.print("\nenter command: ");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Main().run(System.in, System.out, System.err);
    }
}

