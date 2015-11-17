package product;

import core.utils.Logger;
import product.beans.Group;
import product.beans.Product;
import product.beans.ProductResult;
import product.service.ProductProcessor;
import product.service.ProductReader;
import product.service.ProductWriter;

/**
 * @author Mikhail Boldinov
 */
public class ProductGenerator {

    public static final String SLASH = "/";
    public static final String DASH = "-";
    public static final String DOT = ".";
    public static final String SPACE = " ";
    public static final String NEW_LINE = "\r\n";

    private static final int CODES_COUNT_TO_WARN = 1000;
    private static final String DEFAULT_OUT_FILE_NAME = "out.xls";

    private static final Logger LOGGER = Logger.getInstance();

    public static void main(String[] args) {
        try {
            LOGGER.start();

            LOGGER.newLine();
            if (args.length == 0) {
                LOGGER.error("Please specify input file name as first program argument.");
                System.exit(1);
            }
            String inFileName = args[0];
            String outFileName = args.length < 2 ? DEFAULT_OUT_FILE_NAME : args[1];
            LOGGER.info("Reading input file '%s'...", inFileName);
            ProductReader productReader = new ProductReader(inFileName);
            Product product = productReader.read();
            LOGGER.info("Success!");

            LOGGER.newLine();
            LOGGER.info("Data processing...");
            ProductProcessor productProcessor = new ProductProcessor(product);
            ProductResult productResult = productProcessor.process();
            LOGGER.info("Success!");

            LOGGER.newLine();
            LOGGER.info("Saving results into '%s'...", outFileName);
            int codesCount = getCodesCount(product);
            if (codesCount > CODES_COUNT_TO_WARN) {
                LOGGER.warn("Generated codes number is %,d. Data processing might be time consuming.", codesCount);
            }
            LOGGER.startProgress();
            ProductWriter productWriter = new ProductWriter(outFileName);
            productWriter.write(productResult);
            LOGGER.endProgress();
            LOGGER.info("Success!");

            LOGGER.newLine();
            LOGGER.totalTime();
            LOGGER.finishTime();

            LOGGER.newLine();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static int getCodesCount(Product product) {
        int count = 1;
        for (Group group : product.getGroups()) {
            int codesCount = group.getCodes().size();
            if (codesCount != 0) {
                count *= codesCount;
            }
        }
        return count;
    }
}
