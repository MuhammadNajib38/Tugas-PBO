package helpers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    private static Connection connection;

    @Contract(pure = true)
    public static @Nullable Connection getConnection(){
        Connection c = null;

        try {
            System.out.println("Connecting..");
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(
                    "jdbc:mysql://localhost/pbo_sepeda",
                    "root",
                    "");
            System.out.println("Connected!");
        } catch (ClassNotFoundException e) {
            System.err.println("Error Driver");
        } catch (SQLException e){
            System.err.println("Error SQL"); }
        return c;
    }
}
