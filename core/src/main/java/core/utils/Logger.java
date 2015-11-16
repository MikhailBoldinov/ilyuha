package core.utils;

import java.io.PrintStream;
import java.util.Date;

/**
 * @author Mikhail Boldinov, 16.11.15
 */
public final class Logger {

    private static final String INFO_PREFIX = " [INFO] ";
    private static final String WARNING_PREFIX = " [WARNING] ";
    private static final String ERROR_PREFIX = " [ERROR] ";
    private static final String SEPARATOR_LINE = " [INFO] ------------------------------------------------------------------------";
    private static final String EMPTY = "";

    private static Logger instance = new Logger();
    private static PrintStream outStream = System.out;
    private static PrintStream errStream = System.err;

    private static long startTime;

    private ProgressIndicator progress;

    private Logger() {
    }

    public static Logger getInstance() {
        return instance;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void totalTime() {
        long endTime = System.currentTimeMillis();
        double totalTime = ((double) (endTime - startTime)) / 1000;
        info("Total time: %.3f s", totalTime);
    }

    public void finishTime() {
        info("Finished at: %s", new Date());
    }

    public void startProgress() {
        progress = new ProgressIndicator(outStream, INFO_PREFIX);
        Thread progressThread = new Thread(progress);
        progressThread.start();
    }

    public void endProgress() {
        progress.hide();
    }

    public void newLine() {
        out(EMPTY, SEPARATOR_LINE);
    }

    public void info(String msg) {
        out(INFO_PREFIX, msg);
    }

    public void info(String msg, Object... args) {
        out(INFO_PREFIX, msg, args);
    }

    public void warn(String msg) {
        out(WARNING_PREFIX, msg);
    }

    public void warn(String msg, Object... args) {
        out(WARNING_PREFIX, msg, args);
    }

    public void error(String msg) {
        err(ERROR_PREFIX, msg);
    }

    public void error(String msg, Throwable e) {
        err(ERROR_PREFIX, msg, e);
    }

    public void error(String msg, Throwable e, Object... args) {
        err(ERROR_PREFIX, msg, e, args);
    }

    private void out(String level, String msg, Object... args) {
        outStream.println(level + String.format(msg, args));
    }

    private void err(String level, String msg, Object... args) {
        errStream.println(level + String.format(msg, args));
    }

    private void err(String level, String msg, Throwable e, Object... args) {
        errStream.println(level + String.format(msg, args));
        e.printStackTrace();
    }

    public void setOutStream(PrintStream outStream) {
        Logger.outStream = outStream;
    }

    public void setErrStream(PrintStream errStream) {
        Logger.errStream = errStream;
    }
}
