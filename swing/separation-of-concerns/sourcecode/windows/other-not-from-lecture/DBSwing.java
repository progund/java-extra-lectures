import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteConfig;

import javax.swing.*;
import java.awt.*;

public class DBSwing{

    static String getStudentID(){
	return (String)JOptionPane
	    .showInputDialog(null,
			     "Enter student ID: ",
			     "Student ID",
			     JOptionPane.PLAIN_MESSAGE,
			     null,
			     null,
			     "enter id here");
    }

    static String checkStudentID(String id){
	try{
	    Connection con = DBCLI.getConnection();
	    Statement stm = con.createStatement();
	    String query = "select name from students where id="+id;	
	    ResultSet rs = stm.executeQuery(query);
	    if(rs.next()){
		return rs.getString("name");
	    }else{
		return null;
	    }
	}catch(Exception e){
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	    return null;
	}
    }

    public static void main(String[] args)throws Exception{
	Connection con = DBCLI.getConnection();
	String id = getStudentID();
	String name="";
	while( (name=checkStudentID(id))==null){
	    id = getStudentID();
	}
	Statement stm = con.createStatement();
	// SELECT courses.course_code FROM students, courses JOIN registrations ON registrations.course_id=courses.id and registrations.student_id=students.id and students.id=1; 
	String query  = "SELECT courses.course_code FROM students, courses "+
	    "JOIN registrations ON "+
	    "registrations.student_id = students.id and "+
	    "registrations.course_id = courses.id and "+
	    "students.id = "+id;
	ResultSet rs = stm.executeQuery(query);
	StringBuffer courses = new StringBuffer();
	while(rs.next()){
	    courses.append(rs.getString("course_code")).append("\n");
	}
	JOptionPane.showMessageDialog(null, name+" (student id"+id+") is registered to the following courses:\n"+courses.toString());
    }
}
