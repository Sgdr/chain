package ru.coolga.chain.statement;

import ru.coolga.chain.clause.ColumnInsertClause;
import ru.coolga.chain.clause.ExecuteClause;
import ru.coolga.chain.core.Statement;

import java.util.Map;

/**
 * Defines column range to be inserted into table from the data set
 *
 * @author Dmitry Coolga
 *         03.01.2013 2:09 PM
 */
public class ColumnInsertStatement extends StatementBase implements ColumnInsertClause {

    public ExecuteClause include(String... columns) {
        query().append(" ( ");
        for (String column : columns) {
            query().append(column + ", ");
            addParameter(getData().get(column));
        }
        int length = query().length();
        query().delete(length - 2, length);
        query().append(" ) VALUES ( ");
        for (int i = 0; i < columns.length; i++) {
            query().append("?, ");
        }
        length = query().length();
        query().delete(length - 2, length);
        query().append(" )");
        return next(Statement.EXECUTE);
    }

	// Where is comment?

    public ExecuteClause exclude(String... columns) {
        query().append(" ( ");
        int columnCount = 0;
        for (String column : columns) {
            if (isExcludedColumn(column, columns)) {
                continue;
            }
            columnCount++;
            query().append(column + ", ");
            addParameter(getData().get(column));
        }
        int length = query().length();
        query().delete(length - 2, length);
        query().append(" ) VALUES ( ");
        for (int i = 0; i < columnCount; i++) {
            query().append("?, ");
        }
        length = query().length();
        query().delete(length - 2, length);
        query().append(" )");
        return next(Statement.EXECUTE);
    }

    public ExecuteClause includeAll() {
        query().append(" ( ");
        for (Map.Entry<String, Object> entry : getData().entrySet()) {
            query().append(entry.getKey() + ", ");
            addParameter(entry.getValue());
        }
        int length = query().length();
        query().delete(length - 2, length);
        query().append(" ) VALUES ( ");
        for (int i = 0; i < getData().size(); i++) {
            query().append("?, ");
        }
        length = query().length();
        query().delete(length - 2, length);
        query().append(" )");
        return next(Statement.EXECUTE);
    }

    private boolean isExcludedColumn(String column, String... excludedColumns) {
        for (String excluded : excludedColumns) {
            if (column.equals(excluded))
                return true;
        }
        return false;
    }

}
