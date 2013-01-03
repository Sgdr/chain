package ru.coolga.chain.clause;

/**
 * Defines values to be inserted via explicit insert query
 *
 * @author Dmitry Coolga
 *         03.01.2013 1:26 PM
 */
public interface InsertValuesClause {

    /**
     * Defines values to be inserted
     * @param values values to be inserted
     * @return execute query clause
     */
    ExecuteClause values(Object... values);

}
