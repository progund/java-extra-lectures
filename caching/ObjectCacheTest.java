import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import se.juneday.ObjectCache;

public class ObjectCacheTest {

  public static void main(String[] args) {
    // Create an ObjectCache instance - pass the User class as a
    // parameter (used to set the name of the file to write/cache to).
    ObjectCache<User> cache = new ObjectCache<>(User.class);

    // Check command line args:
    // if "--store", create, set and push objects
    // else, pull and get objects
    if ( (args.length>0) &&
         (args[0].equals("--store")) ) {
      System.out.println("Creating objects");
      List<User> users = new ArrayList<>();
      users.add(new User("Henrik Sandklef", "hesa@sandklef.com"));
      users.add(new User("Rikard Fröberg", "rille@rillefroberg.se"));
      System.out.println("main: " + users);
      // Add the users created above. Now the users are in RAM
      cache.set(users);
      // Store the users set above to file. Now the users are serialized to file
      cache.push();
    } else {
      System.out.println("Reading objects");
      // Read the users from file. Now the users are de-serialized from the file and stored in RAM
      cache.pull();
      // Get the users from RAM
      List<User> cachedUsers = cache.get();
      System.out.println("cached: " + cachedUsers);
    }
  }
}
