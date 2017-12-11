import java.io.Serializable;
import java.util.List;
//import java.util.ArrayList;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Session<T> {

  public static class Context {}
  
  private List<T> objects;
  private long localCacheDate ;
  private Context context;

  private String cacheFileName;
  private final static long maxDiff = (1 * 60 * 1000);

  public Session(Class tClass, Context context){
    localCacheDate = 0;
    cacheFileName = tClass.getSimpleName()+ "_serialized.data";
    this.context=context;
  }

  public void set(List<T> objects) {
    this.objects = objects;
    localCacheDate = System.currentTimeMillis();
  }

  public void commit() {
    FileOutputStream fos = null;
    ObjectOutputStream out = null;
    try {
      fos = new FileOutputStream(cacheFileName);
      out = new ObjectOutputStream(fos);
      out.writeObject(objects);      
      out.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public List<T> pull() {
    File f = new File(cacheFileName);
    long diff =
      System.currentTimeMillis() - f.lastModified();

    if (diff>maxDiff) {
      this.objects=null;
      System.err.println("cache timed out for objects");
      return null;
    }
    FileInputStream fis = null;
    ObjectInputStream in = null;
    List<T> tmpObjects;
    try {
      fis = new FileInputStream(cacheFileName);
      in = new ObjectInputStream(fis);
      tmpObjects = (List<T>) in.readObject();
      in.close();
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
    objects = tmpObjects;
    return objects;
  }

  public List<T> get() {
    return objects;
  }

}
