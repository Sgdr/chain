package ru.coolga.chain.core;

import java.sql.SQLException;
import java.text.MessageFormat;

/**
 * Runtime exception/ Uses to define some problems during
 * query formatting and execution
 *
 * @author Dmitry Coolga
 *         31.12.2012 10:55 AM
 */
public class ChainSqlException extends RuntimeException {

    /**
     * Defines the cause of the exception
     * @param cause exception cause
     */
    public ChainSqlException(SQLException cause) {
        super(cause);
    }

    /**
     * Describes the cause of the exception using string with arguments set
     * @param message message text
     * @param args arguments set
     */
    public ChainSqlException(String message, Object... args) {
        super(new MessageFormat(message).format(args));
    }

}
