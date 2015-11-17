package core.utils;

import java.io.PrintStream;

/**
 * @author Mikhail Boldinov
 */
public class ProgressIndicator implements Runnable {

    private static final String PROGRESS_SYMBOL = "-";
    private static final int PROGRESS_LENGTH = 72;
    private static final long DELAY = 100L;

    private boolean run = true;
    private PrintStream outStream;
    private int maxLength;
    private String blankLine;
    private String prefix;

    public ProgressIndicator(PrintStream outStream, String prefix) {
        this.outStream = outStream;
        this.prefix = prefix;
        this.maxLength = getMaxLength(prefix);
        this.blankLine = getBlankLine();
    }

    @Override
    public void run() {
        show();
    }

    public void show() {
        int i = reset();
        print(prefix);
        while (run) {
            if (i >= maxLength) {
                i = reset();
                print(prefix);
            }
            print(PROGRESS_SYMBOL);
            sleep();
            i++;
        }
    }

    public void hide() {
        reset();
        run = false;
    }

    private void print(String msg) {
        outStream.print(msg);
    }

    private void carriageReturn() {
        outStream.print("\r");
    }

    private void sleep() {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException ignore) {
        }
    }

    private int getMaxLength(String prefix) {
        return prefix.length() + PROGRESS_LENGTH;
    }

    private String getBlankLine() {
        StringBuilder sb = new StringBuilder(prefix);
        for (int i = prefix.length(); i <= maxLength; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private int reset() {
        carriageReturn();
        print(blankLine);
        carriageReturn();
        return prefix.length();
    }
}
