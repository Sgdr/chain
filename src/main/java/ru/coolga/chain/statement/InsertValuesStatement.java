package ru.coolga.chain.statement;

import ru.coolga.chain.clause.ExecuteClause;
import ru.coolga.chain.clause.InsertValuesClause;
import ru.coolga.chain.core.Statement;

/**
 * Defines values to be inserted via explicit insert query
 *
 * @author Dmitry Coolga
 *         03.01.2013 1:29 PM
 */
public class InsertValuesStatement extends StatementBase implements InsertValuesClause {

    public ExecuteClause values(Object... values) {
        query().append(" VALUES ( ");
        for(Object value : values) {
            query().append("?, ");
            addParameter(value);
        }
        int length = query().length();
        query().delete(length - 2, length);
        query().append(" )");
        return next(Statement.EXECUTE);
    }

}
