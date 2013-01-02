package ru.coolga.chain.statement;

import ru.coolga.chain.clause.ExecuteClause;
import ru.coolga.chain.core.ChainSqlException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Add some comments here...
 *
 * @author: Dmitry Coolga
 * Date:    31.12.2012 11:21 AM
 */
public class ExecuteStatement extends StatementBase implements ExecuteClause {

    public void run() {
        PreparedStatement statement = null;
        try {
            statement = connection().prepareStatement(query().toString());
            injectParameters(statement);
            statement.execute();
        } catch(SQLException ex) {
            throw new ChainSqlException(ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                throw new ChainSqlException(ex);
            }

        }
    }

}
