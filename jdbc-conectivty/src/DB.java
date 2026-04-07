import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection open() {
        try {
            if (conn == null) {
                Properties prop = propertiesData();
                conn = DriverManager.getConnection(prop.getProperty("dburl"), prop);
            }
            return conn;
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    private static Properties propertiesData() {
        try (FileInputStream fl = new FileInputStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(fl);
            return prop;
        }
        catch (IOException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
}
