package ru.coolga.chain.statement;

import ru.coolga.chain.clause.ColumnSelectionClause;
import ru.coolga.chain.clause.WhereClause;
import ru.coolga.chain.core.Statement;

import java.util.Map;

/**
 * Defines column range to be updated from the data set
 *
 * @author Dmitry Coolga
 *         03.01.2013 1:01 PM
 */
public class ColumnUpdateStatement<T> extends StatementBase implements ColumnSelectionClause<T> {

    public WhereClause<T> include(String... columns) {
        for(String column : columns) {
            query().append(column + " = ?, ");
            addParameter(getData().get(column));
        }
        int length = query().length();
        query().delete(length - 2, length);
        return next(Statement.WHERE);
    }

    public WhereClause<T> exclude(String... columns) {
        for(Map.Entry<String, Object> entry : getData().entrySet()) {
            if (isExcludedColumn(entry.getKey(), columns)) {
                continue;
            }
            query().append(entry.getKey() + " = ?, ");
            addParameter(entry.getValue());
        }
        int length = query().length();
        query().delete(length - 2, length);
        return next(Statement.WHERE);
    }

    public WhereClause<T> includeAll() {
        for(Map.Entry<String, Object> entry : getData().entrySet()) {
            query().append(entry.getKey() + " = ?, ");
            addParameter(entry.getValue());
        }
        int length = query().length();
        query().delete(length - 2, length);
        return next(Statement.WHERE);
    }

    private boolean isExcludedColumn(String column, String... excludedColumns) {
        for (String excluded : excludedColumns) {
            if (column.equals(excluded))
                return true;
        }
        return false;
    }

}
