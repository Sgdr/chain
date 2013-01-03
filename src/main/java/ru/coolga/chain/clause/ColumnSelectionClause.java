package ru.coolga.chain.clause;

/**
 * Defines column range to be updated from the data set or selected from the query
 *
 * @author Dmitry Coolga
 *         03.01.2013 11:25 AM
 */
public interface ColumnSelectionClause<T> {

    /**
     * Defines the column range explicitly
     * @param columns column range
     * @return "where" clause
     */
    WhereClause<T> include(String... columns);

    /**
     * Includes all data set columns except defined ones
     * @param columns column range not to be used in query
     * @return "where" clause
     */
    WhereClause<T> exclude(String... columns);

    /**
     * Includes all columns from the data set
     * @return "where" clause
     */
    WhereClause<T> includeAll();

}
