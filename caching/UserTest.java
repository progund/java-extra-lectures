import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class UserTest {

  public static void main(String[] args) {
    Session<User> session = new Session<>(User.class, new Session.Context());

    if (args.length>0) {
      System.out.println("Creating objects");
      List<User> users = new ArrayList<>();
      users.add(new User("Henrik Sandklef", "hesa@sandklef.com"));
      users.add(new User("Rikard Fröberg", "rille@rillefroberg.se"));
      
      System.out.println("main: " + users);
      session.set(users);
      session.commit();
    }
    System.out.println("Reading objects");
    session.pull();
    List<User> cachedUsers = session.get();
    System.out.println("cached: " + cachedUsers);
  }
}
