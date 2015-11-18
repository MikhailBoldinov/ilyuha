package core.service;

import java.io.IOException;

/**
 * @author Mikhail Boldinov
 */
public interface IReader<T extends IProcessable> {
    T read() throws IOException;
}
