import java.io.Serializable;

public class User implements Serializable {
  String name;
  String email;

  public User(String name, String email) {
    this.name=name;
    this.email=email;
  }

  public String toString() {
    return "\"" + name + "\""+ (email!=null?"<" + email + ">":"");
  }

}
