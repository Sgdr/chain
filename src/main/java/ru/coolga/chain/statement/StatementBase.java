package ru.coolga.chain.statement;

import ru.coolga.chain.core.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Add some comments here...
 *
 * @author: Dmitry Coolga
 * 29.12.2012 12:06
 */
public abstract class StatementBase {

    private ChainContext context;
    private StatementSwitcher statementSwitcher;

    public void setContext(ChainContext context) {
        this.context = context;
    }

    public void setStatementSwitcher(StatementSwitcher statementSwitcher) {
        this.statementSwitcher = statementSwitcher;
    }

    protected Connection connection() {
        return context.getConnection();
    }

    protected StringBuilder query() {
        return context.getQuery();
    }

    protected void addParameter(DataType type, Object value) {
       context.getParams().add(new QueryParameter(type, value));
    }

    protected <T> T next(Statement statement) {
        return statementSwitcher.getStatement(statement);
    }

    protected <T> T nextFinal() {
        return statementSwitcher.getStatement(context.getFinalStatement());
    }

    protected void injectParameters(PreparedStatement statement) throws SQLException {
        for (int i = 1; i <= context.getParams().size(); i++) {
            QueryParameter parameter = context.getParams().get(i - 1);
            switch (parameter.getType()) {
                case DATE: statement.setDate(i, (Date)parameter.getValue()); break;
                case INT: statement.setInt(i, (Integer)parameter.getValue()); break;
                case LONG: statement.setLong(i, (Long)parameter.getValue()); break;
                case STRING: statement.setString(i, (String)parameter.getValue()); break;
            }
        }
    }

}
