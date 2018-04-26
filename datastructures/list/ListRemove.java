import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ListRemove {
  public static void main(String[] args) {
    List<String> names = new ArrayList<>(Arrays.asList(args));
    // Remove all names starting with "rik", Using a ListIterator
    for (ListIterator<String> it = names.listIterator(); it.hasNext(); ) {
      String currentName = it.next();
      if (currentName.toLowerCase().startsWith("rik")) {
        System.out.println("Removing: " + currentName);
        it.remove();
      }
    }
    System.out.println("The resulting list:");
    for (String name : names) {
      System.out.println(name);
    }
  }
}
