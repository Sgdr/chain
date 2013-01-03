package ru.coolga.chain.statement;

import ru.coolga.chain.clause.RelationClause;

/**
 * Defines restrictive relation for column and restriction value
 * used in "where" statements to narrow query result data
 *
 * @author Dmitry Coolga
 *         29.12.2012 12:40 PM
 */
public class RelationStatement<T> extends StatementBase implements RelationClause {

    public T equal(Object value) {
        return handleExecute(" = ?", value);
    }

    public T less(Object value) {
        return handleExecute(" < ?", value);
    }

    public T greater(Object value) {
        return handleExecute(" > ?", value);
    }

    private T handleExecute(String code, Object value) {
        query().append(code);
        addParameter(value);
        return nextFinal();
    }

}
