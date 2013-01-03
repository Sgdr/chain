package ru.coolga.chain.clause;

/**
 * Defines restrictive relation for column and restriction value
 * used in "where" statements to narrow query result data
 *
 * @author Dmitry Coolga
 *         31.12.2012 5:18 PM
 */
public interface RelationClause<T> {

    /**
     * Defines that the column value must be equal to defined value
     * @param value restrictive value
     * @return next logic clause
     */
    public T equal(Object value);

    /**
     * Defines that the column value must be less than defined value
     * @param value restrictive value
     * @return next logic clause
     */
    public T less(Object value);

    /**
     * Defines that the column value must be greater than defined value
     * @param value restrictive value
     * @return next logic clause
     */
    public T greater(Object value);

}
