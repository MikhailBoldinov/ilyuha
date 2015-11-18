package core.utils;

/**
 * @author Mikhail Boldinov
 */
public final class Utils {

    public static final String SLASH = "/";
    public static final String DASH = "-";
    public static final String DOT = ".";
    public static final String SPACE = " ";
    public static final String CR_LF = "\r\n";
    public static final String CR = "\r";
    public static final String LF = "\n";

    private Utils() {
    }

    public static String buildString(String... str) {
        StringBuilder builder = new StringBuilder();
        for (String s : str) {
            builder.append(s);
        }
        return builder.toString();
    }
}
