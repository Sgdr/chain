package ru.coolga.chain;

import ru.coolga.chain.clause.*;
import ru.coolga.chain.core.Statement;
import ru.coolga.chain.core.StatementSwitcher;
import ru.coolga.chain.statement.*;
import ru.coolga.chain.core.ChainContext;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages statements for sql chain execution
 *
 * @author Dmitry Coolga
 *         30.12.2012 8:14 PM
 */
public class ChainController implements StatementSwitcher, SqlChain {

    public static SqlChain getSqlChain(Connection connection) {
        return new ChainController(connection);
    }

    private Connection connection;
    private Map<Statement, StatementBase> statements;

    private ChainController(Connection connection) {
        this.connection = connection;
        initStatements();
    }

    public WhereClause<ExecuteClause> delete(String table) {
        ChainContext context = new ChainContext(connection, Statement.EXECUTE);
        prepareStatements(context);
        context.getQuery().append("DELETE FROM " + table);
        return getStatement(Statement.WHERE);
    }

    public WhereClause<QueryClause<Integer>> count(String table) {
        ChainContext context = new ChainContext(connection, Statement.COUNT);
        prepareStatements(context);
        context.getQuery().append("SELECT COUNT(1) FROM " + table);
        return getStatement(Statement.WHERE);
    }

    public WhereClause<QueryClause<Boolean>> exists(String table) {
        ChainContext context = new ChainContext(connection, Statement.EXISTS);
        prepareStatements(context);
        context.getQuery().append("SELECT COUNT(1) FROM " + table);
        return getStatement(Statement.WHERE);
    }

    public ColumnSelectionClause<QueryClause<Map<String, Object>>> selectSingle(String table) {
        ChainContext context = new ChainContext(connection, Statement.SELECT_SINGLE);
        prepareStatements(context);
        context.getQuery().append("SELECT ");
        context.setTable(table);
        return getStatement(Statement.COLUMN_SELECT);
    }

    public ColumnSelectionClause<QueryClause<List<Map<String, Object>>>> select(String table) {
        ChainContext context = new ChainContext(connection, Statement.SELECT);
        prepareStatements(context);
        context.getQuery().append("SELECT ");
        context.setTable(table);
        return getStatement(Statement.COLUMN_SELECT);
    }

    public SetClause<ExecuteClause> update(String table) {
        ChainContext context = new ChainContext(connection, Statement.EXECUTE);
        prepareStatements(context);
        context.getQuery().append("UPDATE " + table + " SET ");
        return getStatement(Statement.SET);
    }

    public ColumnSelectionClause<ExecuteClause> updateData(String table, Map<String, Object> data) {
        ChainContext context = new ChainContext(connection, Statement.EXECUTE);
        prepareStatements(context);
        context.setData(data);
        context.getQuery().append("UPDATE " + table + " SET ");
        return getStatement(Statement.COLUMN_UPDATE);
    }

    public InsertColumnsClause insert(String table) {
        ChainContext context = new ChainContext(connection, Statement.EXECUTE);
        prepareStatements(context);
        context.getQuery().append("INSERT INTO " + table);
        return getStatement(Statement.INSERT_COLUMNS);
    }

    public ColumnInsertClause insertData(String table, Map<String, Object> data) {
        ChainContext context = new ChainContext(connection, Statement.EXECUTE);
        prepareStatements(context);
        context.setData(data);
        context.getQuery().append("INSERT INTO " + table);
        return getStatement(Statement.COLUMN_INSERT);
    }

    public <T> T getStatement(Statement statement) {
        return (T)statements.get(statement);
    }

    private void initStatements() {
        statements = new HashMap<Statement, StatementBase>();
        statements.put(Statement.WHERE, new WhereStatement());
        statements.put(Statement.RELATION, new RelationStatement());
        statements.put(Statement.EXECUTE, new ExecuteStatement());
        statements.put(Statement.COUNT, new CountStatement());
        statements.put(Statement.EXISTS, new ExistsStatement());
        statements.put(Statement.SELECT, new SelectStatement());
        statements.put(Statement.SELECT_SINGLE, new SelectSingleStatement());
        statements.put(Statement.COLUMN_SELECT, new ColumnSelectStatement());
        statements.put(Statement.COLUMN_UPDATE, new ColumnUpdateStatement());
        statements.put(Statement.INSERT_VALUES, new InsertValuesStatement());
        statements.put(Statement.INSERT_COLUMNS, new InsertColumnsStatement());
        statements.put(Statement.COLUMN_INSERT, new ColumnInsertStatement());
        statements.put(Statement.SET, new SetStatement());
        for (StatementBase statement : statements.values()) {
            statement.setStatementSwitcher(this);
        }
    }

    private void prepareStatements(ChainContext context) {
        for (StatementBase statement : statements.values()) {
            statement.setContext(context);
        }
    }

}
