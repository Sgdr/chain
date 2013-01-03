package ru.coolga.chain;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JUnit test set for update queries
 *
 * @author Dmitry Coolga
 *         03.01.2013 4:37 PM
 */
public class UpdateSqlChainTest extends TestBase {

    @Test
    public void updateAllTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        sql.update("CUSTOMER").set("NAME", "noname").setLast("VISITS", 10).all().run();
        List<Map<String, Object>> data = sql.select("CUSTOMER").includeAll().all().get();
        Assert.assertNotNull(data);
        for(Map<String, Object> entity : data) {
            Assert.assertEquals("noname", entity.get("NAME"));
            Assert.assertEquals(10, entity.get("VISITS"));
        }
    }

    @Test
    public void updateDataTest() throws SQLException {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("NAME", "noname");
        input.put("VISITS", 10);
        SqlChain sql = ChainController.getSqlChain(connection);
        sql.updateData("CUSTOMER", input).includeAll().all().run();
        List<Map<String, Object>> data = sql.select("CUSTOMER").includeAll().all().get();
        Assert.assertNotNull(data);
        for(Map<String, Object> entity : data) {
            Assert.assertEquals("noname", entity.get("NAME"));
            Assert.assertEquals(10, entity.get("VISITS"));
        }
    }

}
