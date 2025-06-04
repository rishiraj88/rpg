package gam.util;

import java.io.IOException;
import java.io.Writer;

public class IOUtil {
    public static void display(Object model) {
        try {
            Writer writer = Flyweight.writer;
            writer.write(model.toString());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readLine() {
        try {
            String input = Flyweight.reader.readLine();
            return input;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
