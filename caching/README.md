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

# Links

* (Serializable Objects)[https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html]
