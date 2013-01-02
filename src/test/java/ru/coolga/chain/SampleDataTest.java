package ru.coolga.chain;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Add some comments here...
 *
 * @author: Dmitry Coolga
 * Date:    30.12.2012 10:08 PM
 */
public class SampleDataTest extends TestBase {

    private static final String SELECT_CUSTOMER_DATA = "SELECT * FROM customer WHERE id = ?";

    @Test
    @SuppressWarnings("deprecation")
    public void hsqldbTest() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMER_DATA);
        checkCustomerData(statement, 1, "Bob", 12, new Date(8, 1990, 23));
        checkCustomerData(statement, 2, "Mary", 26, new Date(1, 1985, 12));
        checkCustomerData(statement, 3, "John", 14, new Date(4, 1995, 3));
    }

    private void checkCustomerData(PreparedStatement statement,
            long id, String name, int visits, Date birthday) throws SQLException {
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        rs.next();
        Assert.assertEquals(rs.getLong(1), id);
        Assert.assertEquals(rs.getString(2), name);
        Assert.assertEquals(rs.getInt(3), visits);
        Assert.assertEquals(rs.getDate(4), birthday);
    }

}
