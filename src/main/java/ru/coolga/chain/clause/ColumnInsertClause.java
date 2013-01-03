package ru.coolga.chain.clause;

/**
 * Defines column range to be inserted into table from the data set
 *
 * @author Dmitry Coolga
 *         03.01.2013 2:11 PM
 */
public interface ColumnInsertClause {

    /**
     * Defines the column range explicitly
     * @param columns column range
     * @return execute clause
     */
    ExecuteClause include(String... columns);

    /**
     * Includes all data set columns except defined ones
     * @param columns column range not to be used in query
     * @return execute clause
     */
    ExecuteClause exclude(String... columns);

    /**
     * Includes all columns from the data set
     * @return execute clause
     */
    ExecuteClause includeAll();

}
