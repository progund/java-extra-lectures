import java.util.*;

public class ArgSorter {
  public static void main(String[] args) {
    Arrays.stream(args) // create a stream (source)
      .sorted() // intermediate op, creates a new stream
      .forEach(System.out::println); // terminal operator, void
  }
}
