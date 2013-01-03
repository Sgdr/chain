package ru.coolga.chain.statement;

import ru.coolga.chain.clause.RelationClause;
import ru.coolga.chain.clause.WhereClause;
import ru.coolga.chain.core.Statement;

/**
 * Filter the data to be used in query result calculation
 *
 * @author Dmitry Coolga
 *         29.12.2012 12:23 PM
 */
public class WhereStatement<T> extends StatementBase implements WhereClause<T> {

    public RelationClause<T> where(String column) {
        query().append(" WHERE ").append(column);
        return next(Statement.RELATION);
    }

    public T all() {
        return nextFinal();
    }

}
