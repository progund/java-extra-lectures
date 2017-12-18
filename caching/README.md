# Cache with serialization

We've seen a couple of frameworks to solve the cache problem. The
overhead of many of these are often too high to make them
understandable and in some cases useful.

In this lecture we'll show you how to:
* use serializtion to create your own cache for a specific class
* extend this cache to cache generic objects

# What to cache

In this example we'll be caching User:

```
public class User implements Serializable {

  String name;

  String email;

  ....
```

We have written a small class (```ObjectCacheTest```) that serves as a
test as well as an example of how to use ObjectCache. In short the
following steps are needed:

* create an ObjectCache object, including the type of the objects to cache: ```ObjectCache<User> cache = new ObjectCache<>(User.class);```  (there are constructors)
* set the objects to cache (in RAM): ```cache.set(users);``` (objects is a List of Users)
* write the cache to disk ```cache.push();```
* read the cache back from disk (to RAM ```cache.pull();```
* get the cached objects from RAM ```List<User> cachedUsers = cache.get();```

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