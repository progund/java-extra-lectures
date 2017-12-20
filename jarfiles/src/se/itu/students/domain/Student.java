package se.itu.students.domain;

public class Student {
  private String name;
  private String email;

  public Student(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String name() {
    return name;
  }

  public String email() {
    return email;
  }

  @Override
  public String toString() {
    return new StringBuilder("Student with name <")
      .append(name).append("> and email <")
      .append(email).append(">").toString();
  }
}
