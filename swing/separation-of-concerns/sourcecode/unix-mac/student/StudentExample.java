package student;

import student.data.*;

//import java.util.ArrayList;

public class StudentExample {

  public static void main(String[] args) {

    if (args.length != 1) {
      System.out.println("USAGE: java student.StudentExample <ID>");
      System.out.println(" where <ID> is a student ID.");
      System.exit(-1);
    }
        
    String studentID = args[0];
    StudentDataDB data = new StudentDataDB();

    if (! data.studentIDExists(studentID) ) {
      System.out.println("No student found with ID " + studentID);
      System.exit(0);
    }

    System.out.println("Courses for " +
                       data.getStudentName(studentID) +
                       " (id: " + studentID+"):");

    for (String course : data.getCourses(studentID)) {
      System.out.println(course);
    }
  }                  
}
