package ru.coolga.chain.statement;

import ru.coolga.chain.clause.ColumnSelectionClause;
import ru.coolga.chain.clause.WhereClause;
import ru.coolga.chain.core.Statement;

/**
 * Defines column range to be selected from the query
 *
 * @author Dmitry Coolga
 *         03.01.2013 11:29 AM
 */
public class ColumnSelectStatement<T> extends StatementBase implements ColumnSelectionClause<T> {

    public WhereClause<T> include(String... columns) {
        for (String column : columns) {
            query().append(column + ", ");
        }
        int length = query().length();
        query().delete(length - 2, length);
        query().append(" FROM " + table());
        return next(Statement.WHERE);
    }

    public WhereClause<T> exclude(String... columns) {
        setExcluded(columns);
        query().append("* FROM " + table());
        return next(Statement.WHERE);
    }

    public WhereClause<T> includeAll() {
        query().append("* FROM " + table());
        return next(Statement.WHERE);
    }

}
