import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class ObjectCacheTest {

  public static void main(String[] args) {
    ObjectCache<User> cache = new ObjectCache<>(User.class);

    
    if ( (args.length>0) &&
         (args[0].equals("--store")) ) {
      System.out.println("Creating objects");
      List<User> users = new ArrayList<>();
      users.add(new User("Henrik Sandklef", "hesa@sandklef.com"));
      users.add(new User("Rikard Fröberg", "rille@rillefroberg.se"));
      System.out.println("main: " + users);
      cache.set(users);
      cache.commit();
    } else {
      System.out.println("Reading objects");
      cache.pull();
      List<User> cachedUsers = cache.get();
      System.out.println("cached: " + cachedUsers);
    }
  }
}
