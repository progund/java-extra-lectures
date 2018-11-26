public class StudentDemo {
  public static void main(String[] args) {
    Student aStudent = new Student("Charlie", "charlie@email.com");
    Student another = new Student("Bob");

    System.out.println(aStudent);
    System.out.println(another);

    if (another.email().isPresent()) {
      System.out.println("Here's the email for " + another.name() + ": "
                         + another.email().get());
    } else {
      System.out.println(another.name() + " doesn't have an email.");
    }
    
    if (aStudent.email().isPresent()) {
      System.out.println("Here's the email for " + aStudent.name() + ": "
                         + aStudent.email().get());
    } else {
      System.out.println(aStudent.name() + " doesn't have an email.");
    }
    
  }
}
