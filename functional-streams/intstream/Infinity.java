import java.util.stream.IntStream;

public class Infinity {
  public static void main(String[] args) {
    long sum = IntStream.iterate(0, i -> ++i)
      .map(i -> (int)Math.pow(2, i))
      .limit(31)
      .reduce(0, (a, b) -> a + b);
     System.out.println("The sum of all powers of two from 0 to 30 is:       " + sum);
     System.out.println("Which is exactly the same as the maximum int value: " + Integer.MAX_VALUE);
  }
}
