package description.services;

import core.io.IWriter;
import core.io.impl.AbstractWriter;
import description.beans.DescriptionResult;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;

/**
 * @author Mikhail Boldinov
 */
public class DescriptionWriter extends AbstractWriter implements IWriter<DescriptionResult> {

    protected DescriptionWriter(String fileName) {
        super(fileName);
    }

    @Override
    public void write(DescriptionResult descriptionResult) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void writeHeader(Sheet sheet) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
