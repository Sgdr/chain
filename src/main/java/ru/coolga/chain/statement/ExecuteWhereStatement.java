package ru.coolga.chain.statement;

import ru.coolga.chain.clause.ExecuteRelationClause;
import ru.coolga.chain.clause.ExecuteWhereClause;
import ru.coolga.chain.core.Statement;

/**
 * Add some comments here...
 *
 * @author Dmitry Coolga
 *         29.12.2012 12:23 PM
 */
public class ExecuteWhereStatement<T> extends StatementBase implements ExecuteWhereClause<T> {

    public ExecuteRelationClause where(String column) {
        query().append(" WHERE ").append(column);
        return next(Statement.EXECUTE_RELATION);
    }

    public T all() {
        return nextFinal();
    }

}
