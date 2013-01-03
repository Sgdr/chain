package ru.coolga.chain;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * JUnit test set for insert queries
 *
 * @author Dmitry Coolga
 *         03.01.2013 4:38 PM
 */
public class InsertSqlChainTest extends TestBase {

    @Test
    @SuppressWarnings("deprecation")
    public void insertColumnValuesTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        sql.insert("CUSTOMER").columns("ID", "NAME", "VISITS", "BIRTHDAY")
                .values(4L, "Incognito", 34, new Date(6, 1965, 8)).run();
        Map<String, Object> data = sql.selectSingle("CUSTOMER").includeAll().where("ID").equal(4L).get();
        Assert.assertNotNull(data);
        Assert.assertEquals(4L, data.get("ID"));
        Assert.assertEquals("Incognito", data.get("NAME"));
        Assert.assertEquals(34, data.get("VISITS"));
    }

    @Test
    @SuppressWarnings("deprecation")
    public void insertDataTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("ID", 4L);
        input.put("NAME", "Incognito");
        input.put("VISITS", 34);
        input.put("BIRTHDAY", new Date(6, 1965, 8));
        sql.insertData("CUSTOMER", input).includeAll().run();

        Map<String, Object> data = sql.selectSingle("CUSTOMER").includeAll().where("ID").equal(4L).get();
        Assert.assertNotNull(data);
        Assert.assertEquals(4L, data.get("ID"));
        Assert.assertEquals("Incognito", data.get("NAME"));
        Assert.assertEquals(34, data.get("VISITS"));
    }

}
