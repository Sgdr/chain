package ru.coolga.chain.clause;

/**
 * Executes query with explicit query result (select queries)
 *
 * @author Dmitry Coolga
 *         02.01.2013 10:00 PM
 */
public interface QueryClause<T> {

    /**
     * Executes query and returns query result as an output
     * @return query result (can be both single values and
     * complex data structures)
     */
    T get();

}
