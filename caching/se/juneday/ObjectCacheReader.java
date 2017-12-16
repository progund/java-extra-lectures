import se.juneday.ObjectCache;

import java.util.List;
import java.io.File;

public class ObjectCacheReader<T> {

  ObjectCache<T> cache;

  public ObjectCacheReader(String fileName) {
    cache = new ObjectCache<>(fileName);
    File f = new File(fileName);
    if (!f.exists()) {
      throw new ObjectCacheReaderException(fileName + " does not exist");
    }
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
