package ru.coolga.chain.core;

import java.sql.SQLException;
import java.text.MessageFormat;

/**
 * Add some comments here...
 *
 * @author: Dmitry Coolga
 * Date:    31.12.2012 10:55 AM
 */
public class ChainSqlException extends RuntimeException {

    public ChainSqlException(SQLException cause) {
        super(cause);
    }

    public ChainSqlException(String message, Object... args) {
        super(new MessageFormat(message).format(args));
    }

}
