package student.data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteConfig;

public class DBUtils{
    private static Connection con;
    static{
        try{
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.setReadOnly(true);
            con = DriverManager.getConnection("jdbc:sqlite:students.db", config.toProperties());
        }catch(Exception e){
            System.err.println("Could not load sqlite jdbc driver: "+e.getMessage());
            System.exit(1);
        }
    }
    public static Connection getConnection(){
        return con;
    }

    public static Statement getStatement(){
        try{
            return con.createStatement();
        }catch(Exception e){
            System.err.println("Couln't create statement: "+e.getMessage());
            return null;
        }
    }

    public static ResultSet executeQuery(String query){
        try{
            return getStatement().executeQuery(query);
        }catch(Exception e){
            System.err.println("Exception executing query: "+e.getMessage());
            return null;
        }
    }

    // Main - only for testing this class as a unit
    public static void main(String[] args){
        try{
            String query="select name,course_code from students,courses"+
                " join registrations on courses.id=registrations.course_id"+
                " and students.id = registrations.student_id";
            ResultSet rs = executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getString("name") + " " + rs.getString("course_code"));
            }
        }catch(SQLException sqle){
            System.err.println("Problem: " + sqle.getMessage());
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.err.println("Something went very wrong: "+e.getMessage());
            }
        }
    }
}
