package core.utils;

/**
 * @author Mikhail Boldinov
 */
public final class Utils {

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
