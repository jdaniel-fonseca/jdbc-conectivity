import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    //Metodo para conectar o banco de dados
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

    //metodo para pegar as informações do db.properties
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

    //metodo para fechar a conexão com o banco
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

    //Metodo para tratar o fechamento do Statment
    public static void closeStatment(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }
}
