package DButil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
    public static Connection getconnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:Student.sqlite");
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();

        }
        return null;
    }
}
