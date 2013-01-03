package ru.coolga.chain.core;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Defines context for executed query. Contains all the necessary data,
 * such as connection, query string, query parameters and so on
 *
 * @author Dmitry Coolga
 *         30.12.2012 10:24 PM
 */
public class ChainContext {

    private Connection connection;
    private StringBuilder query;
    private List<Object> params;
    private Statement finalStatement;
    private String table;
    private String[] excludedColumns;
    private Map<String, Object> data;

    public ChainContext(Connection connection, Statement finalStatement) {
        this.connection = connection;
        this.finalStatement = finalStatement;
        query = new StringBuilder();
        params = new ArrayList<Object>();
    }

    public Connection getConnection() {
        return connection;
    }

    public StringBuilder getQuery() {
        return query;
    }

    public List<Object> getParams() {
        return params;
    }

    public Statement getFinalStatement() {
        return finalStatement;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String[] getExcludedColumns() {
        return excludedColumns;
    }

    public void setExcludedColumns(String[] excludedColumns) {
        this.excludedColumns = excludedColumns;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
