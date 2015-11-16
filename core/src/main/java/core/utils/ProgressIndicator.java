package core.utils;

import java.io.PrintStream;

/**
 * @author Mikhail Boldinov
 */
public class ProgressIndicator implements Runnable {

    private static final String PROGRESS_SYMBOL = "-";
    private static final int PROGRESS_LENGTH = 72;

    private boolean run = true;
    private PrintStream outStream;
    private int length;
    private String blankLine;
    private String prefix;

    public ProgressIndicator(PrintStream outStream, String prefix) {
        this.outStream = outStream;
        this.prefix = prefix;
        this.length = prefix.length() + PROGRESS_LENGTH;

        StringBuilder sb = new StringBuilder(prefix);
        for (int i = prefix.length(); i <= length; i++) {
            sb.append(" ");
        }
        blankLine = sb.toString();
    }

    @Override
    public void run() {
        int i = prefix.length();
        outStream.print(prefix);
        while (run) {
            if (i >= length) {
                i = prefix.length();
                outStream.print("\r");
                outStream.print(blankLine);
                outStream.print("\r");
                outStream.print(prefix);
            }
            outStream.print(PROGRESS_SYMBOL);
            try {
                Thread.sleep(100l);
            } catch (InterruptedException ignore) {
            }
            i++;
        }
    }

    public void hide() {
        outStream.print("\r");
        outStream.print(blankLine);
        outStream.print("\r");
        run = false;
    }
}
