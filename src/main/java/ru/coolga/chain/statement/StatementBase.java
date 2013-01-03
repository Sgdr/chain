package ru.coolga.chain.statement;

import ru.coolga.chain.core.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * Provides basis from statements
 *
 * @author Dmitry Coolga
 *         29.12.2012 12:06PM
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

    protected void injectParameters(PreparedStatement statement) throws SQLException {
        for (int i = 0; i < context.getParams().size(); i++) {
            statement.setObject(i + 1, context.getParams().get(i));
        }
    }

    protected Connection connection() {
        return context.getConnection();
    }

    protected StringBuilder query() {
        return context.getQuery();
    }

    protected void addParameter(Object value) {
       context.getParams().add(value);
    }

    protected <T> T next(Statement statement) {
        return statementSwitcher.getStatement(statement);
    }

    protected <T> T nextFinal() {
        return statementSwitcher.getStatement(context.getFinalStatement());
    }

    protected String table() {
        return context.getTable();
    }

    protected void setExcluded(String[] columns) {
        context.setExcludedColumns(columns);
    }

    protected String[] getExcluded() {
        return context.getExcludedColumns();
    }

    protected Map<String, Object> getData() {
        return context.getData();
    }


}
