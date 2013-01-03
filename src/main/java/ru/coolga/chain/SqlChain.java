package ru.coolga.chain;

import ru.coolga.chain.clause.*;

import java.util.List;
import java.util.Map;

/**
 * Defines available query types
 *
 * @author Dmitry Coolga
 *         03.01.2013 4:07 PM
 */
public interface SqlChain {

    /**
     * Equivalent to sql DELETE statement
     * @param table table name
     * @return clause to define query filter (Equivalent to sql WHERE clause)
     */
    WhereClause<ExecuteClause> delete(String table);

    /**
     * Defines record count. Equivalent to SELECT COUNT(1)... sql statement
     * @param table table name
     * @return clause to define query filter (Equivalent to sql WHERE clause)
     */
    WhereClause<QueryClause<Integer>> count(String table);

    /**
     * Defines that record exists in the table
     * @param table table name
     * @return clause to define query filter (Equivalent to sql WHERE clause)
     */
    WhereClause<QueryClause<Boolean>> exists(String table);

    /**
     * Selects single record from specified table (Equivalent to sql SELECT clause)
     * @param table table name
     * @return clause to select column range
     */
    ColumnSelectionClause<QueryClause<Map<String, Object>>> selectSingle(String table);

    /**
     * Selects records from specified table (Equivalent to sql SELECT clause)
     * @param table table name
     * @return clause to select column range
     */
    ColumnSelectionClause<QueryClause<List<Map<String, Object>>>> select(String table);

    /**
     * Explicitly updates columns with specified values (Equivalent to sql UPDATE clause)
     * @param table table name
     * @return clause to columns and values to be updated (Equivalent to sql SET clause)
     */
    SetClause<ExecuteClause> update(String table);

    /**
     * Updates table records with values defined in the data map. Map keys should be
     * column names and values should be corresponding new values.
     * @param table table name
     * @param data data map
     * @return clause to select column range
     */
    ColumnSelectionClause<ExecuteClause> updateData(String table, Map<String, Object> data);

    /**
     * Explicitly inserts values in specified columns (Equivalent to sql INSERT clause)
     * @param table table name
     * @return clause to define column range
     */
    InsertColumnsClause insert(String table);

    /**
     * Inserts records with values specified in the data map. Map keys should be
     * column names and values should be corresponding new values.
     * @param table table name
     * @param data data map
     * @return clause to select column range
     */
    ColumnInsertClause insertData(String table, Map<String, Object> data);

}
