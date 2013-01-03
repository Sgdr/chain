package ru.coolga.chain;

import org.hsqldb.Server;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.sql.*;

/**
 * Provides basis for query test
 *
 * @author Dmitry Coolga
 *         30.12.2012 8:52 PM
 */
public class TestBase {

    private static final String CREATE_TABLE =
            "CREATE TABLE customer (id BIGINT, name VARCHAR, visits INTEGER, birthday DATE)";

    private static final String INSERT_DATA =
            "INSERT INTO customer (id, name, visits, birthday) values (?, ?, ?, ?)";

    private static final String DELETE_DATA = "DELETE FROM customer";

    private static final String DROP_TABLE = "DROP TABLE customer";

    private static final String COUNT_ROWS = "SELECT COUNT(1) FROM customer";

    protected static Connection connection;

    private static Server hsqlServer;

    @BeforeClass
    public static void beforeClass() throws ClassNotFoundException, SQLException {
        hsqlServer = new Server();
        hsqlServer.setLogWriter(null);
        hsqlServer.setSilent(true);
        hsqlServer.setDatabaseName(0, "xdb");
        hsqlServer.setDatabasePath(0, "mem:test");
        hsqlServer.start();
        Class.forName("org.hsqldb.jdbcDriver");
        connection = DriverManager.getConnection("jdbc:hsqldb:mem:test", "sa", "");
        connection.createStatement().execute(CREATE_TABLE);
    }

    @Before
    @SuppressWarnings("deprecation")
    public void before() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_DATA);
        insertCustomer(statement, 1, "Bob", 12, new Date(8, 1990, 23));
        insertCustomer(statement, 2, "Mary", 26, new Date(1, 1985, 12));
        insertCustomer(statement, 3, "John", 14, new Date(4, 1995, 3));
    }

    @After
    public void after() throws SQLException {
        connection.createStatement().execute(DELETE_DATA);
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        connection.createStatement().execute(DROP_TABLE);
        connection.close();
        hsqlServer.stop();
    }

    protected int count() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery(COUNT_ROWS);
        rs.next();
        return rs.getInt(1);
    }

    private static void insertCustomer(PreparedStatement statement,
                           long id, String name, int visits, Date birthday) throws SQLException {
        statement.setLong(1, id);
        statement.setString(2, name);
        statement.setInt(3, visits);
        statement.setDate(4, birthday);
        statement.execute();
    }

}
