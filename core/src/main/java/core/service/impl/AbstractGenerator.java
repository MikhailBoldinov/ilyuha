package core.service.impl;

import core.service.IProcessable;
import core.service.IProcessor;
import core.service.IReader;
import core.service.IWriteable;
import core.service.IWriter;
import core.utils.Logger;

import java.io.IOException;

/**
 * @author Mikhail Boldinov
 */
public abstract class AbstractGenerator<R extends IReader, P extends IProcessor, W extends IWriter> {

    private static final int COUNT_TO_WARN = 1000;
    private static final String DEFAULT_OUT_FILE_NAME = "out.xls";

    private static final Logger LOGGER = Logger.getInstance();

    protected String inputFileName;
    protected String outputFileName;

    protected R reader;
    protected W writer;
    protected P processor;

    public AbstractGenerator(String[] args) {
        if (args.length == 0) {
            LOGGER.error("Please specify input file name as first program argument.");
            System.exit(1);
        }
        inputFileName = args[0];
        outputFileName = args.length < 2 ? DEFAULT_OUT_FILE_NAME : args[1];

        try {
            reader = getReader();
            writer = getWriter();
            processor = getProcessor();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void generate() {
        try {
            start();
            IProcessable processable = read();
            IWriteable writeable = process(processable);
            write(writeable);
            finish();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public abstract R getReader() throws IOException;

    public abstract P getProcessor();

    public abstract W getWriter();

    private void start() {
        LOGGER.start();
    }

    private IProcessable read() throws IOException {
        LOGGER.newLine();
        LOGGER.info("Reading input file '%s'...", inputFileName);
        IProcessable processable = reader.read();
        LOGGER.info("Success!");
        return processable;
    }

    @SuppressWarnings("unchecked")
    private IWriteable process(IProcessable processable) {
        LOGGER.newLine();
        LOGGER.info("Data processing...");
        IWriteable writeable = processor.process(processable);
        LOGGER.info("Success!");
        return writeable;
    }

    @SuppressWarnings("unchecked")
    private void write(IWriteable writeable) throws IOException {
        LOGGER.newLine();
        LOGGER.info("Saving results into '%s'...", outputFileName);
        if (writeable.getCount() > COUNT_TO_WARN) {
            LOGGER.warn("Generated codes number is %,d. Data processing might be time consuming.", writeable.getCount());
        }
        LOGGER.startProgress();
        writer.write(writeable);
        LOGGER.endProgress();
        LOGGER.info("Success!");
    }

    private void finish() {
        LOGGER.newLine();
        LOGGER.totalTime();
        LOGGER.finishTime();
        LOGGER.newLine();
    }
}
