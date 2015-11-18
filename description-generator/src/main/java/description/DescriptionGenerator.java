package description;

import core.service.IGenerator;
import core.service.impl.AbstractGenerator;
import description.services.DescriptionProcessor;
import description.services.DescriptionReader;
import description.services.DescriptionWriter;

import java.io.IOException;

/**
 * @author Mikhail Boldinov
 */
public class DescriptionGenerator extends AbstractGenerator<DescriptionReader, DescriptionProcessor, DescriptionWriter> implements IGenerator {

    public DescriptionGenerator(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        DescriptionGenerator descriptionGenerator = new DescriptionGenerator(args);
        descriptionGenerator.generate();
    }

    @Override
    public DescriptionReader getReader() throws IOException {
        return new DescriptionReader(inputFileName);
    }

    @Override
    public DescriptionProcessor getProcessor() {
        return new DescriptionProcessor();
    }

    @Override
    public DescriptionWriter getWriter() {
        return new DescriptionWriter(outputFileName);
    }
}
