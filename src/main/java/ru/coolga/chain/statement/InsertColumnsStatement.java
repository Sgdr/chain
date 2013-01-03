package ru.coolga.chain.statement;

import ru.coolga.chain.clause.InsertColumnsClause;
import ru.coolga.chain.clause.InsertValuesClause;
import ru.coolga.chain.core.Statement;

/**
 * Defines column range for explicit insert query
 *
 * @author Dmitry Coolga
 *         03.01.2013 1:29 PM
 */
public class InsertColumnsStatement extends StatementBase implements InsertColumnsClause {

    public InsertValuesClause columns(String... columns) {
        query().append(" ( ");
        for(String column : columns) {
            query().append(column + ", ");
        }
        int length = query().length();
        query().delete(length - 2, length);
        query().append(" )");
        return next(Statement.INSERT_VALUES);
    }

    public InsertValuesClause columnsAll() {
        return next(Statement.INSERT_VALUES);
    }

}
