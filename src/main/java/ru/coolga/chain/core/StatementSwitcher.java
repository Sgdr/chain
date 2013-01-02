package ru.coolga.chain.core;

/**
 * Add some comments here...
 *
 * @author: Dmitry Coolga
 * Date:    31.12.2012 4:50 PM
 */
public interface StatementSwitcher {

    public <T> T getStatement(Statement statement);

}
