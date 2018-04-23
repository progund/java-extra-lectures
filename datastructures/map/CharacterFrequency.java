import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Collections;

public class CharacterFrequency {
  public static void main(String[] args) {

    if (args.length != 1) {
      System.err.println("USAGE: java CharacterFrequency string");
      System.exit(1);
    }

    // A set for unique chars in args[0]
    Set<Character> uniqueChars = new HashSet<>();
    // Create a list of all characters in args[0]
    List<Character> chars = new ArrayList<>();

    // Populate the list and set
    for (char ch : args[0].toLowerCase().toCharArray()) {
      chars.add(ch);
      uniqueChars.add(ch);
    }
    
    // Create a map <Character, Integer> with the frequencies
    Map<Character, Integer> frequency = new TreeMap<>();
    // Create a map <Integer, List<Character>> with the top list of frequencies
    Map<Integer, List<Character>> topList = new TreeMap<>();

    // Populate the maps
    for (Character ch : uniqueChars) {
      int freq = Collections.frequency(chars, ch);
      frequency.put(ch, freq);
      if (! topList.containsKey(freq)) {
        topList.put(freq, new ArrayList<>());
      }      
      topList.get(freq).add(ch);
    }
    
    System.out.println("Frequencies by character: " + frequency);
    System.out.println("Frequencies by frequence: " + topList);
  }
}
