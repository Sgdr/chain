package ru.coolga.chain.clause;

/**
 * Filter the data to be used in query result calculation
 *
 * @author Dmitry Coolga
 *         31.12.2012 5:28 PM
 */
public interface WhereClause<T> {

    /**
     * Defines the column to set some restriction to
     * @param column column name
     * @return clause to set restriction on the specified column
     */
    public RelationClause<T> where(String column);

    /**
     * Set no restriction
     * @return clause to execute query
     */
    public T all();

}
