package ru.coolga.chain.statement;

import ru.coolga.chain.clause.SetClause;
import ru.coolga.chain.clause.WhereClause;
import ru.coolga.chain.core.Statement;

/**
 * Defines columns to be updated and new values, the columns to be updated with
 *
 * @author Dmitry Coolga
 *         03.01.2013 12:35 PM
 */
public class SetStatement<T> extends StatementBase implements SetClause<T> {

    public SetClause<T> set(String column, Object value) {
        query().append(column + " = ?, ");
        addParameter(value);
        return next(Statement.SET);
    }

    public WhereClause<T> setLast(String column, Object value) {
        query().append(column + " = ?");
        addParameter(value);
        return next(Statement.WHERE);
    }

}
