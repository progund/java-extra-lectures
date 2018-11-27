import java.util.Optional;

public class Contact {

  private String name;
  private String email;
  private String phone;
  private Optional<String> workPhone;

  public Contact(String name, String email, String phone) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.workPhone = Optional.empty();
  }

  public Contact(String name, String email, String phone, String workPhone) {
    this(name, email, phone);
    this.workPhone = Optional.of(workPhone);
  }

  public String name() {
    return name;
  }

  public String email() {
    return email;
  }

  public String phone() {
    return phone;
  }

  public Optional<String> workPhone() {
    return workPhone;
  }

  @Override
  public String toString() {
    return new StringBuilder(name)
      .append(" ")
      .append(email)
      .append(" ")
      .append(phone)
      .append(" ")
      .append(workPhone.orElse(""))
      .toString().trim();
  }

}
