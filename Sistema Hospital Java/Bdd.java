
import java.sql.*;

public class Bdd {

    String URL, user, pass, driver;
    String nombre, apellido;
    String sql;
    Connection con;
    Statement stmt;
    ResultSet rs;

    Bdd() {
        try {
            URL = "jdbc:mysql://localhost:3306/database1";
            user = "root";
            pass = "root";
            con = DriverManager.getConnection(URL, user, pass);
            stmt = con.createStatement();
            // System.out.println(getConnection());

        } catch (Exception e) {
            System.out.println("error " + e.toString());
        }
    }

    public Connection getConnection() {
        return con;
    }
}