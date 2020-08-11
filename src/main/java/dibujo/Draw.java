package dibujo;

import java.io.PrintStream;

public class Draw {

    public static void draw(PrintStream out, Canvas canvas) {
        out.println();

        for (int i = 0; i <= canvas.getWidth(); i++) {
            out.print("-");
        }
        out.print("-");

        out.println();

        int x = 1, y = 1;
        while (x >= 1 && x <= canvas.getWidth() && y >= 1 && y <= canvas.getHeight()) {
            Position position = canvas.getPosition(x, y);
            if (position.getX() == 1) {
                if (position.getY() > 1) {
                    out.println("|");
                }
                out.print("|");
            }

            if (position.getColor() != null) {
                out.print(position.getColor());
            } else if (position.isFilled()) {
                out.print("x");
            } else {
                out.print(" ");
            }
            x++;
            if (x >= canvas.getWidth() + 1) {
                x = 1;
                y++;
            }
        }

        out.println("|");
        out.print("-");

        for (int i = 0; i < canvas.getWidth(); i++) {
            out.print("-");
        }
        out.print("-");

        out.println();

        out.print("\nenter command: ");
    }
}
