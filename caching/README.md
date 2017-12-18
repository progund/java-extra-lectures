# Cache with serialization

We've seen a couple of frameworks to solve the cache problem. The
overhead of many of these are often too high to make them
understandable and in some cases useful.

In this lecture we'll show you how to:
* use serializtion to create your own cache for a specific class
* extend this cache to cache generic objects

# What to cache

In this example we'll be caching a class, ```User```, representing a user in some system:

```
public class User implements Serializable {

  String name;

  String email;

  ....
```

# Requirements on the class to be cached

We rely completely on [Serializable Objects](https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html). To get your class Serializable you need to (read this link: [Interface Serializable](https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html)). To make things short, you did read the link didn't you?, we provide some text about serialVersionUID here: 
> The serialization runtime associates with each serializable class a version number, called a serialVersionUID, which is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. If the receiver has loaded a class for the object that has a different serialVersionUID than that of the corresponding sender's class, then deserialization will result in an InvalidClassException. A serializable class can declare its own serialVersionUID explicitly by declaring a field named "serialVersionUID" that must be static, final, and of type long:
>
> ANY-ACCESS-MODIFIER static final long serialVersionUID = 42L;
The above is copied from: [Serializable Objects](https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html)

BTW: if you feel like reading our opinion on inheritance: [Inheritance - Problems with inheritance](http://wiki.juneday.se/mediawiki/index.php/Chapter:Inheritance_-_Problems_with_inheritance)

## Fixing the User class (above)

We need to add a ```serialVersionUID```. Let's use ```1L```.

```  private static final long serialVersionUID = 1L;```

So the class, ```User```, looks like this.
```public class User implements Serializable {
  String name;
  String email;

  private static final long serialVersionUID = 1L;
```
etc etc

# Using ObjectCache - a quick guide

We have written a small class (```ObjectCacheTest```) that serves as a
test as well as an example of how to use ObjectCache. In short the
following steps are needed:

* create an ObjectCache object, including the type of the objects to cache: ```ObjectCache<User> cache = new ObjectCache<>(User.class);```  (there are constructors)
* set the objects to cache (in RAM): ```cache.set(users);``` (objects is a List of Users)
* write the cache to disk ```cache.push();```
* read the cache back from disk (to RAM ```cache.pull();```
* get the cached objects from RAM ```List<User> cachedUsers = cache.get();```

# Using ObjectCache - a short example

```
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
```

# Using ObjectCache in Android

## ADHD

Check out [ADHD](https://github.com/progund/adhd), a tool with which
you can read out database and serialized files (ObjectCache) from an
Android device and create text and html from that. This is useful if
you want to see what's in your app's database etc on the Android
device.

# Links

* [Serializable Objects](https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html)
* [Generic Types](https://docs.oracle.com/javase/tutorial/java/generics/types.html)