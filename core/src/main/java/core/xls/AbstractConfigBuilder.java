package core.xls;

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

    protected abstract AbstractConfigBuilder getInstance();
}
