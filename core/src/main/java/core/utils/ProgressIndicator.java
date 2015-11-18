package core.utils;

import java.io.PrintStream;

import static core.utils.Utils.CR;

/**
 * @author Mikhail Boldinov
 */
public class ProgressIndicator implements Runnable {

    private static final String PROGRESS_SYMBOL = ".";
    private static final int MAX_LENGTH = 80;
    private static final long DELAY = 100L;

    private boolean run = true;
    private PrintStream outStream;
    private String prefix;
    private String emptyLine;

    public ProgressIndicator(PrintStream outStream, String prefix) {
        this.outStream = outStream;
        this.prefix = prefix;
        this.emptyLine = getEmptyLine(prefix);
    }

    @Override
    public void run() {
        show();
    }

    public void show() {
        int i = reset();
        while (run) {
            if (i >= MAX_LENGTH) {
                i = reset();
            }
            print(PROGRESS_SYMBOL);
            sleep();
            i++;
        }
    }

    public void hide() {
        carriageReturn();
        print(getEmptyLine(""));
        carriageReturn();
        run = false;
    }

    private void print(String msg) {
        outStream.print(msg);
    }

    private void carriageReturn() {
        outStream.print(CR);
    }

    private static void sleep() {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException ignore) {
        }
    }

    private int reset() {
        erase();
        return prefix.length();
    }

    private void erase() {
        carriageReturn();
        print(emptyLine);
        carriageReturn();
        print(prefix);
    }

    private String getEmptyLine(String prefix) {
        StringBuilder sb = new StringBuilder(prefix);
        for (int i = prefix.length(); i <= MAX_LENGTH; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
