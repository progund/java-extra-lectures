import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FilterNames {

  private static List<String> names = Arrays.asList("Mozart", "Beethoven", "Bach", "Berlioz",
                                                    "Bártok", "Händel", "Brecht", "Ravel", "Chopin",
                                                    "von Bingen", "Rachmaninov", "Stravinsky", "Rehnström",
                                                    "Sibelius", "Telemann", "Gesualdo");
  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("java FilterNames <prefix>");
      System.err.println(" where <prefix> is the start of names to filter on");
      System.exit(1);
    }
    String prefix = args[0];
    List<String> result = getNamesStartingWith(prefix);
    if (result == Collections.EMPTY_LIST) {
      System.out.println("Sorry. No names start with " + prefix);
    } else {
      System.out.println("Names starting with " + prefix + ":");
      System.out.println(result);
    }
  }

  private static List<String> getNamesStartingWith(String prefix) {
    List<String> results = names.stream().filter(s -> s.startsWith(prefix)).collect(Collectors.toList());
    if (results.size() == 0) {
      return Collections.<String>emptyList();
    }
    return results;
  }
}
