package product;

import core.service.IGenerator;
import core.service.impl.AbstractGenerator;
import product.services.ProductProcessor;
import product.services.ProductReader;
import product.services.ProductWriter;

import java.io.IOException;

/**
 * @author Mikhail Boldinov
 */
public class ProductGenerator extends AbstractGenerator<ProductReader, ProductProcessor, ProductWriter> implements IGenerator {

    public ProductGenerator(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        ProductGenerator productGenerator = new ProductGenerator(args);
        productGenerator.generate();
    }

    @Override
    public ProductReader getReader() throws IOException {
        return new ProductReader(inputFileName);
    }

    @Override
    public ProductProcessor getProcessor() {
        return new ProductProcessor();
    }

    @Override
    public ProductWriter getWriter() {
        return new ProductWriter(outputFileName);
    }
}
