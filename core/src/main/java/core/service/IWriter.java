package core.service;

import java.io.IOException;

/**
 * @author Mikhail Boldinov
 */
public interface IWriter<T extends IWriteable> {
    void write(T result) throws IOException;
}
