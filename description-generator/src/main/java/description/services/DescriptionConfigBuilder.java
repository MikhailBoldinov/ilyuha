package description.services;

import core.xls.AbstractConfigBuilder;
import core.xls.Config;

import static core.xls.Column.A;
import static core.xls.Column.B;
import static description.beans.Field.CODE;
import static description.beans.Field.DESCRIPTION_TEXT;

/**
 * @author Mikhail Boldinov
 */
public class DescriptionConfigBuilder extends AbstractConfigBuilder {

    @Override
    protected Config build() {
        add(DESCRIPTION_TEXT, 0, B);
        add(CODE, 0, B);
        return config;
    }
}
