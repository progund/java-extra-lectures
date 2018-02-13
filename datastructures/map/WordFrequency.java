import java.util.*;

public class WordFrequency {
  public static void main(String[] args) {
    Map<String, Integer> frequencies = new TreeMap<>();
    for (String arg : args) {
      Integer frequency = frequencies.get(arg);
      if (frequency == null) { // arg didn't exist "
        frequency = 0;
      }
      frequencies.put(arg, frequency + 1);
    }
    System.out.println(frequencies);
  }
}
