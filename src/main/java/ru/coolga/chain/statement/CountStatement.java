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
 *         02.01.2013 10:01 PM
 */
public class CountStatement extends StatementBase implements QueryClause<Integer> {

      public Integer get() {
          PreparedStatement statement = null;
          ResultSet resultSet = null;
          try {
              statement = connection().prepareStatement(query().toString());
              injectParameters(statement);
              resultSet = statement.executeQuery();
              if (resultSet.next()) {
                  return resultSet.getInt(1);
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

}
