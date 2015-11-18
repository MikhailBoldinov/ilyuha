package description;

import core.utils.Logger;
import description.beans.Description;
import description.beans.DescriptionResult;
import description.services.DescriptionProcessor;
import description.services.DescriptionReader;
import description.services.DescriptionWriter;

/**
 * @author Mikhail Boldinov
 */
public class DescriptionGenerator {
    private static final int CODES_COUNT_TO_WARN = 1000;
    private static final String DEFAULT_OUT_FILE_NAME = "out.xls";

    private static final Logger LOGGER = Logger.getInstance();

    public static void main(String[] args) {
        try {
            LOGGER.start();
            if (args.length == 0) {
                LOGGER.error("Please specify input file name as first program argument.");
                System.exit(1);
            }
            String inFileName = args[0];
            String outFileName = args.length < 2 ? DEFAULT_OUT_FILE_NAME : args[1];

            LOGGER.newLine();
            LOGGER.info("Reading data file '%s'...", inFileName);
            DescriptionReader descriptionReader = new DescriptionReader(inFileName);
            Description description = descriptionReader.read();
            LOGGER.info("Success!");

            LOGGER.newLine();
            LOGGER.info("Data processing...");
            DescriptionProcessor descriptionProcessor = new DescriptionProcessor(description);
            DescriptionResult descriptionResult = descriptionProcessor.process();
            LOGGER.info("Success!");

            LOGGER.newLine();
            LOGGER.info("Saving results into '%s'...", outFileName);
            int descriptionsCount = descriptionResult.getResultMap().size();
            if (descriptionsCount > CODES_COUNT_TO_WARN) {
                LOGGER.warn("Generated descriptions number is %,d. Data processing might be time consuming.", descriptionsCount);
            }
            DescriptionWriter descriptionWriter = new DescriptionWriter(outFileName);
            descriptionWriter.write(descriptionResult);
            LOGGER.info("Success!");

            LOGGER.newLine();
            LOGGER.totalTime();
            LOGGER.finishTime();

            LOGGER.newLine();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
