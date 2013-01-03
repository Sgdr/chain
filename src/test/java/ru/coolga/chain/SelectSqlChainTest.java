package ru.coolga.chain;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * JUnit test set for select queries
 *
 * @author Dmitry Coolga
 *         03.01.2013 4:37 PM
 */
public class SelectSqlChainTest extends TestBase {

    @Test
    public void countByIdTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        int count = sql.count("CUSTOMER").where("ID").equal(2L).get();
        Assert.assertEquals(1, count);
    }

    @Test
    public void countAllTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        int count = sql.count("CUSTOMER").all().get();
        Assert.assertEquals(3, count);
    }

    @Test
    public void existsByIdTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        boolean exists = sql.exists("CUSTOMER").where("ID").equal(3L).get();
        Assert.assertTrue(exists);
    }

    @Test
    public void existsAllTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        boolean exists = sql.exists("CUSTOMER").all().get();
        Assert.assertTrue(exists);
    }

    @Test
    public void notExistsTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        boolean exists = sql.exists("CUSTOMER").where("ID").equal(4L).get();
        Assert.assertFalse(exists);
    }

    @Test
    public void selectSingleAllTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        Map<String, Object> data = sql.selectSingle("CUSTOMER").includeAll().where("ID").equal(2L).get();
        Assert.assertNotNull(data);
        Assert.assertEquals(4, data.size());
    }

    @Test
    public void selectSingleIncludeTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        Map<String, Object> data = sql.selectSingle("CUSTOMER").include("ID", "NAME").where("ID").equal(2L).get();
        Assert.assertNotNull(data);
        Assert.assertEquals(2, data.size());
    }

    @Test
    public void selectSingleExcludeTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        Map<String, Object> data = sql.selectSingle("CUSTOMER").exclude("ID").where("ID").equal(2L).get();
        Assert.assertNotNull(data);
        Assert.assertEquals(3, data.size());
    }

    @Test
    public void selectAllTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        List<Map<String, Object>> data = sql.select("CUSTOMER").includeAll().all().get();
        Assert.assertNotNull(data);
        Assert.assertEquals(3, data.size());
    }

    @Test
    public void selectByIdTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        List<Map<String, Object>> data = sql.select("CUSTOMER").includeAll().where("ID").equal(2L).get();
        Assert.assertNotNull(data);
        Assert.assertEquals(1, data.size());
    }

    @Test
    public void selectNoneTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        List<Map<String, Object>> data = sql.select("CUSTOMER").includeAll().where("ID").equal(4L).get();
        Assert.assertNotNull(data);
        Assert.assertEquals(0, data.size());
    }

}
