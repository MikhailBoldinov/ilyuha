package description;

import core.utils.Logger;

/**
 * @author Mikhail Boldinov
 */
public class DescriptionGenerator {

    private static final Logger LOGGER = Logger.getInstance();

    public static void main(String[] args) {
        LOGGER.start();
        LOGGER.newLine();
        LOGGER.totalTime();
        LOGGER.finishTime();

        LOGGER.newLine();
    }
}
