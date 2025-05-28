package gam.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IOUtil {
    public static void display(Object model) {

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            writer.write(model.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readLine() {
        String input = "";
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            input = reader.readLine();
            return input;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
