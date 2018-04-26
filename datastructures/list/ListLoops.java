import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ListLoops {
  public static void main(String[] args) {
    List<String> names = new ArrayList<>(Arrays.asList(args));
    // Loop through the list and print names
    // 1. For-each-loop
    for (String name : names) {
      System.out.println("Name: " + name);
    }
    // 2. Using index and classic for loop
    for (int i = 0; i < names.size(); i++) {
      System.out.println("Name [" + i + "]: " + names.get(i));
    }
    // 3. Using a ListIterator
    for (ListIterator<String> it = names.listIterator(); it.hasNext(); ) {
      System.out.println("Next name: " + it.next());
    }
  }
}
