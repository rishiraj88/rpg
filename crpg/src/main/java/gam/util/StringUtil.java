package gam.util;

public class StringUtil {
    public static String addDelimiter(String input, char delimiter) {
        StringBuffer buffer = new StringBuffer(input);
        for (int i = 0; i < buffer.length(); ++i) {
            if ('1' == buffer.charAt(i)) {
                buffer.replace(i, i + 1, "" + ((char) (i + '1')));
            }
        }
        for (int i = buffer.length() - 1; i > 0; --i) {
            buffer.insert(i, delimiter);
        }
        return buffer.toString();
    }
}
