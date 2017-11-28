import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class SimpleAnagram {
  
  private static final String DICTIONARY="words.txt";
  private Map<String, Set<String>> anagrams = new TreeMap<>();
  
  public SimpleAnagram() {
    anagrams();
  }

  private void anagrams() {
    for (String word : words()) {
      String sorted = sortString(word);
      if (anagrams.containsKey(sorted)) {
        anagrams.get(sorted).add(word);
      } else {
        anagrams.put(sorted, new TreeSet<String>());        
      }
    }
  }

  public List<Set<String>> foundAnagrams(int size) {
    return anagrams.values().stream().filter(s -> s.size() >= size)
      .collect(Collectors.toList());
  }
  
  private List<String> words() {
    try {
      return Files.lines(Paths.get(DICTIONARY))
        .collect(Collectors.toList()); 
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
      return null;
    }
  }

  private String sortString(String s){
    char[] chars;
    Arrays.sort(chars=s.toLowerCase().toCharArray());
    return new String(chars);
  }
  
  public Set<String> anagramsFor(String word) {    
    return anagrams.get(sortString(word));
  }
}
