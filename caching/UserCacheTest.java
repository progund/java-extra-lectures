import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class UserCacheTest {

  public static void main(String[] args) {
    UserCache cache = new UserCache();

    if ( (args.length>0) &&
         (args[0].equals("--store")) ) {
      System.out.println("Creating objects");
      List<User> users = new ArrayList<>();
      users.add(new User("Henrik Sandklef", "hesa@sandklef.com"));
      users.add(new User("Rikard Fröberg", "rille@rillefroberg.se"));
      
      System.out.println("main: " + users);
      cache.set(users);
      cache.push();
    } else {
      System.out.println("Reading objects");
      cache.pull();
      List<User> cachedUsers = cache.get();
      System.out.println("cached users: " + cachedUsers);
    }
  }
}
