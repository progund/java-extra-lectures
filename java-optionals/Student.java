import java.util.Optional;

public class Student {
  private Optional<String> email;
  private String name;

  public Student(String name) {
    this.name = name;
    this.email = Optional.empty();
  }

  public Student(String name, String email) {
    this(name);
    this.email = Optional.of(email);
  }

  public String name() {
    return name;
  }

  public Optional<String> email() {
    return email;
  }

  public String toString() {
    return (name + " " + email.orElse("")).trim();
  }
}
