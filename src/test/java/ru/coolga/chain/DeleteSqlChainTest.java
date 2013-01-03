package ru.coolga.chain;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * JUnit test set for delete queries
 *
 * @author Dmitry Coolga
 *         30.12.2012 8:24 PM
 */
public class DeleteSqlChainTest extends TestBase {

    @Test
    public void deleteByIdTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        sql.delete("CUSTOMER").where("ID").equal(1L).run();
        Assert.assertEquals(2, count());
    }

    @Test
    public void deleteAllTest() throws SQLException {
        SqlChain sql = ChainController.getSqlChain(connection);
        sql.delete("CUSTOMER").all().run();
        Assert.assertEquals(0, count());
    }

}
