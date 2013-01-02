package ru.coolga.chain.statement;

import ru.coolga.chain.clause.QueryClause;
import ru.coolga.chain.core.ChainSqlException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Add some comments here...
 *
 * @author Dmitry Coolga
 *         02.01.2013 11:00 PM
 */
public class ExistsStatement extends StatementBase implements QueryClause<Boolean> {

    public Boolean get() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection().prepareStatement(query().toString());
            injectParameters(statement);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            } else {
                throw new ChainSqlException("Bad query to check existence : {0}", query());
            }

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
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                throw new ChainSqlException(ex);
            }

        }
    }

}
