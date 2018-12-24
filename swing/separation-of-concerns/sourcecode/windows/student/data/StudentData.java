package student.data;
import java.util.ArrayList;

public interface StudentData{
    public ArrayList<String>getCourses(String studentID);
    public String getStudentName(String studentID);
    public boolean studentIDExists(String studentID);
    public String getCourseCode(String courseID);
}
