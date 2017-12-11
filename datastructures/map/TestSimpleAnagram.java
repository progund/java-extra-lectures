import java.util.Set;

/**
 * <p>Application showing the algorithm for finding anagrams
 * put to practice.</p>
 * <p>
 * USAGE:
 * java TestSimpleAnagram <word>...
 * Where <word>... is the word(s) to find anagrams for.
 * </p>
 * <p>
 * The program also lists the sets of at least 8 anagrams found in the
 * dictionary.
 * </p>
 */
public class TestSimpleAnagram {

  private static void check(String[] args) {
    if (args.length == 0) {
      System.err.println("USAGE:");
      System.err.println("java TestSimpleAnagram <word>...");
      System.err.println("Where <word>... is the word(s) to " +
                         "find anagrams for.");
      System.exit(1);
    }
  }
  
  public static void main(String[] args) {
    // Verify that we have at least one argument
    check(args);
    // List the found anagrams for the words,
    // and all 8-or-more anagram sets found in the dictionary
    anagrams(args);
  }

  private static void anagrams(String[] words) {
    SimpleAnagram sa = new SimpleAnagram();
    System.out.println("====Anagrams====");
    for (String word : words) {
      Set<String> anagrams = sa.anagramsFor(word);
      if (anagrams != null && anagrams.size() > 0) {
        System.out.println("* Anagrams for " + word + ": " + anagrams);
      } else {
        System.out.println("* No anagrams for " + word + " found");
      }
    }
    System.out.println("\n\n=================\n");
    System.out.println("Words with at least 8 anagrams:");
    for (Set<String> anagrams : sa.foundAnagrams(8)) {
      System.out.println(anagrams.size() + " - " + anagrams);
    }
  }    
}
