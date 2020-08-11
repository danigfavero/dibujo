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
                            createCanvas(matcher);
                        } else {
                            throw new RuntimeException("Invalid parameters for the create new canvas command. Should be: C <width> <height>");
                        }
                    } else if (line.startsWith("L")) {
                        if (canvas == null) {
                            throw new RuntimeException("No canvas. You should create a canvas before creating a new line.");
                        }
                        Matcher matcher = Command.LINE.find(line);
                        if (matcher.find()) {
                            createLine(matcher);

                        } else {
                            throw new RuntimeException("Invalid parameters for the create new line command. Should be: L <starting x> <starting y> <ending x> <ending y>");
                        }

                    } else if (line.startsWith("R")) {
                        if (canvas == null) {
                            throw new RuntimeException("No canvas. You should create a canvas before creating a new rectangle.");
                        }

                        Matcher matcher = Command.RECTANGLE.find(line);
                        if (matcher.find()) {
                            createNewRectangle(matcher);
                        } else {
                            throw new RuntimeException("Invalid parameters for the create new rectangle command. Should be: L <upper left corner x> <upper left corner y> <lower right corner x> <lower right corner y>");
                        }

                    } else if (line.startsWith("B")) {
                        if (canvas == null) {
                            throw new RuntimeException("No canvas. You should create a canvas before filling it.");
                        }

                        Matcher matcher = Command.BACKGROUND.find(line);
                        if (matcher.find()) {
                            fill(matcher);
                        } else {
                            throw new RuntimeException("Invalid parameters for the bucket fill command. Should be: B <starting x> <starting y> <color>");
                        }

                    } else if (line.startsWith("Q")) {
                        out.println("Bye bye!");
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

    private void createCanvas(Matcher matcher) {
        int width = Integer.parseInt(matcher.group(1));
        int height = Integer.parseInt(matcher.group(2));

        canvas = new Canvas(width, height);
    }

    private void createLine(Matcher matcher) {
        int startingX = Integer.parseInt(matcher.group(1));
        int startingY = Integer.parseInt(matcher.group(2));
        int endingX = Integer.parseInt(matcher.group(3));
        int endingY = Integer.parseInt(matcher.group(4));
        canvas.createNewLine(startingX, startingY, endingX, endingY);
    }

    private void createNewRectangle(Matcher matcher){
        int upperLeftCornerX = Integer.parseInt(matcher.group(1));
        int upperLeftCornerY = Integer.parseInt(matcher.group(2));
        int lowerRightCornerX = Integer.parseInt(matcher.group(3));
        int lowerRightCornerY = Integer.parseInt(matcher.group(4));
        canvas.createNewRectangle(upperLeftCornerX, upperLeftCornerY, lowerRightCornerX, lowerRightCornerY);
    }

    private void fill(Matcher matcher){
        int startingX = Integer.parseInt(matcher.group(1));
        int startingY = Integer.parseInt(matcher.group(2));
        String colorCharacter = matcher.group(3);

        Position startingPositionToFill = new Position(startingX, startingY);
        canvas.fill(startingPositionToFill, colorCharacter);
    }


    public static void main(String[] args) {
        new Main().run(System.in, System.out, System.err);
    }
}

