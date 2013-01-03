package ru.coolga.chain.clause;

/**
 * Defines column range for explicit insert query
 *
 * @author Dmitry Coolga
 *         03.01.2013 1:25 PM
 */
public interface InsertColumnsClause {

    /**
     * Defines explicit column range
     * @param columns column range
     * @return clause for defining inserted values
     */
    InsertValuesClause columns(String... columns);

    /**
     * Column range will be defined by inserted values order
     * @return clause for defining inserted values
     */
    InsertValuesClause columnsAll();

}
