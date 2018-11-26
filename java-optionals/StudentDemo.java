import java.util.Optional;

public class StudentDemo {
  public static void main(String[] args) {
    Student aStudent = new Student("Charlie", "charlie@email.com");
    Student another = new Student("Bob");

    System.out.println(aStudent);
    System.out.println(another);
    
  }
}
