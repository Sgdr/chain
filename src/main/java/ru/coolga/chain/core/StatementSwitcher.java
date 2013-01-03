package ru.coolga.chain.core;

/**
 * Defines the way to switch between query statements
 *
 * @author Dmitry Coolga
 *         31.12.2012 4:50 PM
 */
public interface StatementSwitcher {

    public <T> T getStatement(Statement statement);

}
