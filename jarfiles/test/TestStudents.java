package test;

import se.itu.students.domain.Student;

public class TestStudents {
  public static void main(String[] args) {
    Student[] students = {
      new Student("Henrik", "henke@gu.se"),
      new Student("Rikard", "rille@gu.se"),
      new Student("Aida", "aida@gu.se"),
      new Student("Urban", "ubbe@gu.se")
    };
    for (Student stud : students) {
      System.out.println(stud);
    }
  }
}
