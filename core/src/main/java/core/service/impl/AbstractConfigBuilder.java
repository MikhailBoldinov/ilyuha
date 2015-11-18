package core.service.impl;

import core.xls.Column;
import core.xls.Config;
import core.xls.ConfigItem;
import core.xls.IField;

/**
 * @author Mikhail Boldinov
 */
public abstract class AbstractConfigBuilder {
    protected Config config = new Config();

    protected abstract Config build();

    protected void add(IField field, int rowOffset, Column column) {
        config.add(field, new ConfigItem(rowOffset, column));
    }

    public Config getConfig() {
        return build();
    }
}
