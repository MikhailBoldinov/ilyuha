package core.service;

/**
 * @author Mikhail Boldinov
 */
public interface IProcessor<P extends IProcessable, W extends IWriteable> {
    W process(P processable);
}
