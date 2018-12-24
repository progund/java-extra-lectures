package student.data;

import java.util.ArrayList;
import java.sql.ResultSet;

public class StudentDataDB implements StudentData {

  public ArrayList<String> getCourses(String studentID) {       
    ArrayList<String> courses = new ArrayList<String>();
    String query = "SELECT courses.course_code FROM students, courses "+
      "JOIN registrations ON "+
      "registrations.student_id = students.id and "+
      "registrations.course_id = courses.id and "+
      "students.id = "+studentID;
    
    try {
      ResultSet rs = DBUtils.executeQuery(query);
      while(rs.next()) {
        courses.add(rs.getString("course_code"));
      }
    } catch (Exception e) {
      System.err.println("Error getting courses: "+e.getMessage());
    }
    return courses;
  }
  
  public String getStudentName(String studentID) {
    String query = "SELECT name FROM students WHERE id="+studentID;
    try {
      ResultSet rs = DBUtils.executeQuery(query);
      if (rs.next()) {
        return rs.getString("name");
      }
    } catch (Exception e) {
      System.err.println("Error getting student name: "+e.getMessage());
    }
    return null;
  }
  
  public boolean studentIDExists(String studentID) {
    return getStudentName(studentID) != null;
  }
  
  public String getCourseCode(String courseID) {
    String query="SELECT course_code FROM courses WHERE id=" + courseID;
    ResultSet rs = DBUtils.executeQuery(query);
    try {

    }catch(Exception e){
      System.err.println("Error getting course_code: "+e.getMessage());
    }
    return null;
  }
}
