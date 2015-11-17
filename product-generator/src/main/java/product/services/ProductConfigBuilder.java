package product.services;

import core.xls.AbstractConfigBuilder;
import core.xls.Config;

import static core.xls.Column.A;
import static core.xls.Column.B;
import static core.xls.Column.C;
import static core.xls.Column.D;
import static core.xls.Column.E;
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
public class ProductConfigBuilder extends AbstractConfigBuilder {

    @Override
    protected Config build() {
        add(DESCRIPTION, 0, B);
        add(PRODUCER, 1, B);
        add(SERIAL_NUMBER, 2, B);
        add(SHORT_DESCRIPTION, 3, B);
        add(IMAGE_EXTENSION, 4, B);
        add(FIRST_GROUP_NUM, 7, A);
        add(NUM, 0, A);
        add(GROUP, 0, B);
        add(SEPARATOR, 0, C);
        add(CODE, 0, D);
        add(CODE_DESCRIPTION, 0, E);
        return config;
    }
}
