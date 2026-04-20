import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.open();

            // setAutoCommit(false): faz com que as alterações no banco não sejam salvas automaticamente
            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2070 WHERE DepartmentId = 1");

            //int x = 1;
            //if(x < 2) {
            //    throw new SQLException("Fake error");
           // }

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            // commit(): salva definitivamente as alterações realizadas na transação
            conn.commit();

            System.out.println("rows1 = " + rows1);
            System.out.println("rows 2 = " + rows2);
        }
        catch (SQLException e) {
            try {
                // rollback(): cancela a transação atual, retornando o banco ao estado antes das operações não confirmadas
                conn.rollback();
                throw new DBException("Transaction rolled back! Caused by: " + e.getMessage());
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            DB.closeStatment(st);
            DB.close();
        }


    }
}