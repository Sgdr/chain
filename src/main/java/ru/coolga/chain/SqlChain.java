package ru.coolga.chain;

import ru.coolga.chain.clause.ExecuteClause;
import ru.coolga.chain.clause.ExecuteWhereClause;
import ru.coolga.chain.clause.QueryClause;
import ru.coolga.chain.core.Statement;
import ru.coolga.chain.core.StatementSwitcher;
import ru.coolga.chain.statement.*;
import ru.coolga.chain.core.ChainContext;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Add some comments here...
 *
 * @author Dmitry Coolga
 *         30.12.2012 8:14 PM
 */
public class SqlChain implements StatementSwitcher {

    private Connection connection;
    private Map<Statement, StatementBase> statements;

    public SqlChain(Connection connection) {
        this.connection = connection;
        initStatements();
    }

    public ExecuteWhereClause<ExecuteClause> delete(String table) {
        ChainContext context = new ChainContext(connection, Statement.EXECUTE);
        prepareStatements(context);
        context.getQuery().append("DELETE FROM " + table);
        return getStatement(Statement.EXECUTE_WHERE);
    }

    public ExecuteWhereClause<QueryClause<Integer>> count(String table) {
        ChainContext context = new ChainContext(connection, Statement.COUNT);
        prepareStatements(context);
        context.getQuery().append("SELECT COUNT(1) FROM " + table);
        return getStatement(Statement.EXECUTE_WHERE);
    }

    public ExecuteWhereClause<QueryClause<Boolean>> exists(String table) {
        ChainContext context = new ChainContext(connection, Statement.EXISTS);
        prepareStatements(context);
        context.getQuery().append("SELECT COUNT(1) FROM " + table);
        return getStatement(Statement.EXECUTE_WHERE);
    }

    public <T> T getStatement(Statement statement) {
        return (T)statements.get(statement);
    }

    private void initStatements() {
        statements = new HashMap<Statement, StatementBase>();
        statements.put(Statement.EXECUTE_WHERE, new ExecuteWhereStatement());
        statements.put(Statement.EXECUTE_RELATION, new ExecuteRelationStatement());
        statements.put(Statement.EXECUTE, new ExecuteStatement());
        statements.put(Statement.COUNT, new CountStatement());
        statements.put(Statement.EXISTS, new ExistsStatement());
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
