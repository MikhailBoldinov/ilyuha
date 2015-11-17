package description.services;

import core.io.IReader;
import core.io.impl.AbstractReader;
import core.xls.AbstractConfigBuilder;
import description.beans.Description;

import java.io.IOException;

/**
 * @author Mikhail Boldinov
 */
public class DescriptionReader extends AbstractReader implements IReader<Description> {

    protected DescriptionReader(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    public Description read() throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected AbstractConfigBuilder getConfigBuilder() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
