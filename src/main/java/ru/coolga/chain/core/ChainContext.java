package ru.coolga.chain.core;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Add some comments here...
 *
 * @author Dmitry Coolga
 *         30.12.2012 10:24 PM
 */
public class ChainContext {

    private Connection connection;
    private StringBuilder query;
    private List<QueryParameter> params;
    private Statement finalStatement;

    public ChainContext(Connection connection, Statement finalStatement) {
        this.connection = connection;
        this.finalStatement = finalStatement;
        query = new StringBuilder();
        params = new ArrayList<QueryParameter>();
    }

    public Connection getConnection() {
        return connection;
    }

    public StringBuilder getQuery() {
        return query;
    }

    public List<QueryParameter> getParams() {
        return params;
    }

    public Statement getFinalStatement() {
        return finalStatement;
    }

}
