package ru.coolga.chain.statement;

import ru.coolga.chain.clause.QueryClause;
import ru.coolga.chain.core.ChainSqlException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Selects single record from table
 *
 * @author Dmitry Coolga
 *         03.01.2013 10:48 AM
 */
public class SelectSingleStatement extends StatementBase implements QueryClause<Map<String, Object>> {

    public Map<String, Object> get() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection().prepareStatement(query().toString());
            injectParameters(statement);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return handleResults(resultSet);
            } else {
                throw new ChainSqlException("Bad query to count rows : {0}", query());
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

    private Map<String, Object> handleResults(ResultSet resultSet) throws SQLException {
        ResultSetMetaData meta = resultSet.getMetaData();
        Map<String, Object> result = new HashMap<String, Object>();
        for (int index = 1; index <= meta.getColumnCount(); index++) {
            result.put(meta.getColumnName(index), resultSet.getObject(index));
        }
        if (getExcluded() != null) {
            for (String column : getExcluded()) {
                result.remove(column);
            }
        }
        return result;
    }

}
