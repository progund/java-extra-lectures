import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * Reads a dictionary file and creates a datastructure with all anagrams
 * found in the dictionary, as a Map&lt;String, Set&lt;String&gt;&gt .
 * The key of the map is the words with their letters sorted.
 */
public class SimpleAnagram {
  
  private static final String DICTIONARY="words.txt";
  private Map<String, Set<String>> anagrams = new TreeMap<>();

  /** Creates a new SimpleAnagram object
   * with the anagrams data structure created.
   */
  public SimpleAnagram() {
    anagrams();
  }

  private void anagrams() {
    for (String word : words()) {
      String sorted = sortString(word);
      if (anagrams.containsKey(sorted)) {
        anagrams.get(sorted).add(word);
      } else {
        Set<String> set = new TreeSet<String>();
        set.add(word);
        anagrams.put(sorted, set);        
      }
    }
  }

  /**
   * Returns a list of all the anagram sets of at least <code>size</code>
   * anagrams.
   * @param size - the minimum size of the anagram sets to return
   * @return A List&lt;Set&lt;String&gt;&gt; of all the anagram sets of
   * at least <code>size</code>
   */
  public List<Set<String>> foundAnagrams(int size) {
    return anagrams.values().stream()
      .sorted((s1, s2) -> s1.size() - s2.size())
      .filter(s -> s.size() >= size)
      .collect(Collectors.toList());
  }
  
  private List<String> words() {
    try {
      return Files.lines(Paths.get(DICTIONARY))
        .map(String::toLowerCase)
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

  /**
   * Returns a Set&lt;String&gt; of all the anagrams found for the given
   * word, an empty Set for words not in the dictionary, and a Set of only
   * the word itself, for words without anagrams.
   * @param word The word to get the anagrams for
   * @return A Set&lt;String&gt; with the found anagrams for the word.
   */
  public Set<String> anagramsFor(String word) {    
    return anagrams.get(sortString(word));
  }
}
