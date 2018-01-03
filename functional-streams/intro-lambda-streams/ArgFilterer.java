import java.util.*;

public class ArgFilterer {
  public static void main(String[] args) {
    Arrays.stream(args) // create a stream (source)
      .filter(s -> s.startsWith("i")) // intermediate op, creates a new stream
      .forEach(System.out::println); // terminal operator, void
  }
}
