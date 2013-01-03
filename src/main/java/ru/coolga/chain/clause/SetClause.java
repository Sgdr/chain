package ru.coolga.chain.clause;

/**
 * Defines columns to be updated and new values, the columns to be updated with
 *
 * @author Dmitry Coolga
 *         03.01.2013 12:30 PM
 */
public interface SetClause<T> {

    /**
     * Defines column name and new value for it. Should be used if there are other
     * columns to be updated with new values
     * @param column column name
     * @param value new value
     * @return next "set" clause
     */
    SetClause<T> set(String column, Object value);

    /**
     * Defines column name and new value for it. Should be used if there are no other
     * columns to be updated with new values
     * @param column column name
     * @param value new value
     * @return "where" clause
     */
    WhereClause<T> setLast(String column, Object value);

}
