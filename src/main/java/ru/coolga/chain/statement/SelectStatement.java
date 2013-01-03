package ru.coolga.chain.statement;

import ru.coolga.chain.clause.QueryClause;
import ru.coolga.chain.core.ChainSqlException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Selects records from tables
 *
 * @author Dmitry Coolga
 *         03.01.2013 12:14 PM
 */
public class SelectStatement extends StatementBase
        implements QueryClause<List<Map<String, Object>>> {

    public List<Map<String, Object>> get() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection().prepareStatement(query().toString());
            injectParameters(statement);
            resultSet = statement.executeQuery();
            return handleResults(resultSet);
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

    private List<Map<String, Object>> handleResults(ResultSet resultSet) throws SQLException {
        ResultSetMetaData meta = resultSet.getMetaData();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        while(resultSet.next()) {
            Map<String, Object> entity = new HashMap<String, Object>();
            for (int index = 1; index <= meta.getColumnCount(); index++) {
                entity.put(meta.getColumnName(index), resultSet.getObject(index));
            }
            if (getExcluded() != null) {
                for (String column : getExcluded()) {
                    result.remove(column);
                }
            }
            result.add(entity);
        }
        return result;
    }

}
