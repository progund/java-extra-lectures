import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

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

  public static void main(String[] args) {
    Session<User> session = new Session<>(User.class, new Session.Context());

    if (args.length>0) {
      List<User> users = new ArrayList<>();
      users.add(new User("Henrik Sandklef", "hesa@sandklef.com"));
      users.add(new User("Maja Sandklef", "maja@sandklef.com"));
      users.add(new User("Filippa Sandklef", "filippa@sandklef.com"));
      
      System.out.println("main: " + users);
      session.set(users);
      session.commit();
    }
    session.pull();
    List<User> cachedUsers = session.get();
    System.out.println("cached: " + cachedUsers);
  }
}
