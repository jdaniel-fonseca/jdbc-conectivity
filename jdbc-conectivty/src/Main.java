import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.open();
            st = conn.prepareStatement(
                    "DELETE FROM department "
                    + "WHERE Id = ?");
            st.setInt(1, 4);

            int rowsAffected = st.executeUpdate();
            System.out.println("Linhas afetadas = " + rowsAffected);
        }
        catch (SQLException e) {
            throw new DBIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatment(st);
            DB.close();
        }


    }
}