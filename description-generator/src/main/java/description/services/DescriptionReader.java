package description.services;

import core.io.IReader;
import core.io.impl.AbstractReader;
import core.xls.AbstractConfigBuilder;
import description.beans.Description;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.io.IOException;

import static description.beans.Field.CODE;
import static description.beans.Field.DESCRIPTION_TEXT;

/**
 * @author Mikhail Boldinov
 */
public class DescriptionReader extends AbstractReader implements IReader<Description> {

    public DescriptionReader(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    public Description read() throws IOException {
        HSSFSheet sheet = getSheet(0);
        int rows = getNumberOfRows(sheet);
        Description description = new Description();
        description.setDescriptionText(getStringValue(sheet, 0, DESCRIPTION_TEXT));

        for (int currentRow = 1; currentRow < rows; currentRow++) {
            description.addCode(getStringValue(sheet, currentRow, CODE));
        }
        return description;
    }

    @Override
    protected AbstractConfigBuilder getConfigBuilder() {
        return new DescriptionConfigBuilder();
    }
}
