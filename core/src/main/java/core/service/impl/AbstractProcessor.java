package core.service.impl;

/**
 * @author Mikhail Boldinov
 */
public abstract class AbstractProcessor<T> {

    private T processData;

    protected AbstractProcessor(T processData) {
        this.processData = processData;
    }

    public T getProcessData() {
        return processData;
    }
}
