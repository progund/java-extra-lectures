package se.juneday;

import java.util.List;
import java.io.File;

public class ObjectCacheReader<T> {

  ObjectCache<T> cache;

  public ObjectCacheReader(String fileName) {
    cache = new ObjectCache<>(fileName);
  }

  public void printObjects() {
    List<T> objects = cache.pull();

    if (objects!=null) {
      for (T o : objects) {
        System.out.println(" * " + o );
      }
    }
  }

}
