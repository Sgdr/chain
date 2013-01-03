package ru.coolga.chain.clause;

/**
 * Executes query with no explicit output (such as delete, update and insert)
 *
 * @author Dmitry Coolga
 *         31.12.2012 5:19 PM
 */
public interface ExecuteClause {

    /**
     * Executes query
     */
    public void run();

}
