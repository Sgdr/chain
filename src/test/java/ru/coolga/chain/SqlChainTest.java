package ru.coolga.chain;

import org.junit.Assert;
import org.junit.Test;
import ru.coolga.chain.core.DataType;

import java.sql.SQLException;

/**
 * Add some comments here...
 *
 * @author Dmitry Coolga
 *         30.12.2012 8:24 PM
 */
public class SqlChainTest extends TestBase {

    @Test
    public void deleteByIdTest() throws SQLException {
        SqlChain sql = new SqlChain(connection);
        sql.delete("CUSTOMER").where("ID").equal(1L).run();
        Assert.assertEquals(2, count());
    }

    @Test
    public void deleteAllTest() throws SQLException {
        SqlChain sql = new SqlChain(connection);
        sql.delete("CUSTOMER").all().run();
        Assert.assertEquals(0, count());
    }

    @Test
    public void countByIdTest() throws SQLException {
        SqlChain sql = new SqlChain(connection);
        int count = sql.count("CUSTOMER").where("ID").equal(2L).get();
        Assert.assertEquals(1, count);
    }

    @Test
    public void countAllTest() throws SQLException {
        SqlChain sql = new SqlChain(connection);
        int count = sql.count("CUSTOMER").all().get();
        Assert.assertEquals(3, count);
    }

    @Test
    public void existsByIdTest() throws SQLException {
        SqlChain sql = new SqlChain((connection));
        boolean exists = sql.exists("CUSTOMER").where("ID").equal(3L).get();
        Assert.assertTrue(exists);
    }

    @Test
    public void existsAllTest() throws SQLException {
        SqlChain sql = new SqlChain((connection));
        boolean exists = sql.exists("CUSTOMER").all().get();
        Assert.assertTrue(exists);
    }

    @Test
    public void notExistsTest() throws SQLException {
        SqlChain sql = new SqlChain((connection));
        boolean exists = sql.exists("CUSTOMER").where("ID").equal(4L).get();
        Assert.assertFalse(exists);
    }

}
