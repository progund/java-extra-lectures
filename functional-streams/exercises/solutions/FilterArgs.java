import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Predicate;

public class FilterArgs {
  public static void main(String[] args) {
    List<String> arguments = Arrays.asList(args);
    //Collections.sort(arguments);
    System.out.println("Un-sorted:");
    System.out.println(arguments);

    arguments.sort((s1, s2) -> s1.toLowerCase().compareTo(s2.toLowerCase()));
    System.out.println("\nCase-insensitive sort:");
    System.out.println(arguments);

    Collections.shuffle(arguments);
    System.out.println("\nShuffled:");
    System.out.println(arguments);

    arguments.sort( (s1, s2) ->
                       (s1.startsWith("The ") ? s1.substring(4) : s1)
                   .compareTo(
                       (s2.startsWith("The ") ? s2.substring(4) : s2)
                              ) );
    System.out.println("\nRecord-store sort:");
    System.out.println(arguments);
                   
    Collections.shuffle(arguments);
    System.out.println("\nShuffled:");
    System.out.println(arguments);

    List<String>copy = new ArrayList<>(arguments);
    System.out.println("copy: ");
    System.out.println(copy);

    Predicate<String> p = s -> s.startsWith("The ");
    copy.removeIf(p.negate());
    System.out.println("\ncopy with only strings staring with 'The ':");
    System.out.println(copy);

    copy = new ArrayList<>(arguments);
    System.out.println("\ncopy, restored:");
    System.out.println(copy);

    List<String> result = copy
      .stream()
      .filter(p)
      .collect(Collectors.toList());
    System.out.println("\nCopy filtered using stream():");
    System.out.println(result);
  }
}
