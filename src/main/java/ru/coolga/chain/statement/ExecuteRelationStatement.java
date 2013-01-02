package ru.coolga.chain.statement;

import ru.coolga.chain.clause.ExecuteRelationClause;
import ru.coolga.chain.core.DataType;

import java.sql.Date;

import static ru.coolga.chain.core.DataType.*;

/**
 * Add some comments here...
 *
 * @author : Dmitry Coolga
 * 29.12.2012 12:40 PM
 */
public class ExecuteRelationStatement<T> extends StatementBase implements ExecuteRelationClause {

    public T equal(Long value) { return handleExecute(" = ?", LONG, value); }
    public T equal(Integer value) { return handleExecute(" = ?", INT, value); }
    public T equal(String value) { return handleExecute(" = ?", STRING, value); }
    public T equal(Date value) { return handleExecute(" = ?", DATE, value); }

    public T less(Long value) { return handleExecute(" < ?", LONG, value); }
    public T less(Integer value) { return handleExecute(" < ?", INT, value); }
    public T less(String value) { return handleExecute(" < ?", STRING, value); }
    public T less(Date value) { return handleExecute(" < ?", DATE, value); }

    public T greater(Long value) { return handleExecute(" > ?", LONG, value); }
    public T greater(Integer value) { return handleExecute(" > ?", INT, value); }
    public T greater(String value) { return handleExecute(" > ?", STRING, value); }
    public T greater(Date value) { return handleExecute(" > ?", DATE, value); }

    private T handleExecute(String code, DataType type, Object value) {
        query().append(code);
        addParameter(type, value);
        return nextFinal();
    }

}
