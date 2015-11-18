package product.services;

import core.service.IReader;
import core.service.impl.AbstractConfigBuilder;
import core.service.impl.AbstractReader;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import product.beans.Group;
import product.beans.Product;

import java.io.IOException;

import static product.beans.Field.CODE;
import static product.beans.Field.CODE_DESCRIPTION;
import static product.beans.Field.DESCRIPTION;
import static product.beans.Field.FIRST_GROUP_NUM;
import static product.beans.Field.GROUP;
import static product.beans.Field.IMAGE_EXTENSION;
import static product.beans.Field.NUM;
import static product.beans.Field.PRODUCER;
import static product.beans.Field.SEPARATOR;
import static product.beans.Field.SERIAL_NUMBER;
import static product.beans.Field.SHORT_DESCRIPTION;

/**
 * @author Mikhail Boldinov
 */
public class ProductReader extends AbstractReader implements IReader<Product> {

    public ProductReader(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    public Product read() throws IOException {
        HSSFSheet sheet = getSheet(0);
        int rows = getNumberOfRows(sheet);
        Product product = new Product();
        product.setDescription(getStringValue(sheet, 0, DESCRIPTION));
        product.setProducer(getStringValue(sheet, 0, PRODUCER));
        product.setSerialNumber(getStringValue(sheet, 0, SERIAL_NUMBER));
        product.setShortDescription(getStringValue(sheet, 0, SHORT_DESCRIPTION));
        product.setImageExtension(getStringValue(sheet, 0, IMAGE_EXTENSION));

        for (int currentRow = config.getRow(FIRST_GROUP_NUM); currentRow < rows; currentRow++) {
            Group group = new Group();

            int rowNum = getGroupRowNum(sheet, currentRow, NUM);
            if (rowNum < 0) {
                continue;
            }

            group.setName(getStringValue(sheet, rowNum, GROUP));
            group.setSeparator(getStringValue(sheet, rowNum, SEPARATOR));
            while (++rowNum < rows && !isGroupStart(sheet, rowNum, NUM)) {
                group.addCode(getStringValue(sheet, rowNum, CODE), getStringValue(sheet, rowNum, CODE_DESCRIPTION));
            }
            product.addGroup(group);
        }
        return product;
    }

    @Override
    protected AbstractConfigBuilder getConfigBuilder() {
        return new ProductConfigBuilder();
    }
}
