import java.io.*;
import java.util.*;

public class Anagrams {
  private Map<String,Set<String>> lookup;
  private static final String DICTIONARY="words.txt";
  public Anagrams() {
    load();
  }

  private void load() {
    File f = new File(DICTIONARY);
    /* As a general rule, the default load factor (.75)
     * offers a good tradeoff between time and space costs. 
     * If the initial capacity is greater than the maximum
     * number of entries divided by the load factor, no rehash
     * operations will ever occur.
     */
    // A map from String -> a set of Strings
    Map<String,Set<String>> anagrams = new HashMap<String,Set<String>>((int)(121427*0.75));	
    Set<String> anagramsFor;
    try {
      BufferedReader in = new BufferedReader(new FileReader(f));
      String s;
      while ( (s=in.readLine())!=null ) {
        s=s.toLowerCase();
        String sorted = sortString(s);
        if (anagrams.containsKey(sorted)) {
          anagramsFor=anagrams.get(sorted);		    
        } else {
          anagramsFor = new TreeSet<String>();
          anagrams.put(sorted, anagramsFor);
        }
        anagramsFor.add(s);
      }
      lookup=anagrams;
    } catch(IOException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
  
  private String sortString(String s) {
    char[] chars;
    Arrays.sort(chars=s.toLowerCase().toCharArray());
    return new String(chars);
  }
  
  private Set<String> mostAnagrams(){
    Set<String> longest=null;
    int max=0;
    for (Set<String> list : lookup.values()) {
      if (list.size() > max) {
        max=list.size();
        longest=list;
      }
    }
    return longest;
  }
  
  private Set getAnagrams(String s) {
    return lookup.get(sortString(s));
  }
  private boolean areAnagrams(String s1, String s2) {
    return getAnagrams(sortString(s1)).contains(s2);
  }
  
  private Map anagramsOfAtLeast(int size) {
    Map<Integer,List<Set<String>>> result = new TreeMap<Integer,List<Set<String>>>();
    List<Set<String>> grams = null;
    Set<String>set=null;
    for(Map.Entry<String,Set<String>> entry : lookup.entrySet()){
      set=entry.getValue();
      if(set.size()>=size){
        if(result.containsKey(set.size())){
          result.get(set.size()).add(set);
        }else{
          grams = new ArrayList<Set<String>>();
          grams.add(set);
          result.put(set.size(), grams);		    
        }
      }
    }
    return result;
  }
  
  public static void main(String[] args) {
    Anagrams m = new Anagrams();
    if (args.length == 1) {
      try {
        int min = Integer.parseInt(args[0]);
        System.out.println(m.anagramsOfAtLeast(min));		
      } catch(NumberFormatException nfe) {
        System.out.println("Anagrams for " + args[0] + ":");
        System.out.println(m.getAnagrams(args[0]));
      }
    } else if(args.length == 2) {
      String result="";
      if (! m.areAnagrams(args[0], args[1])) {
        result=" not";
      }
      System.out.println(args[0] + " and " + args[1] + " are" + result +" anagrams");
    } else {
      System.err.println("Usage: java Anagrams word "+
                         "(gives a list of anagrams for word)");
      System.err.println("   or: java Anagrams word1 "+
                         "word2 (tells if word1 is an anagram of word2");
      System.err.println("   or: java Anagrams num "+
                         "(lists all words that have at least num anagrams)");
    }
    //System.out.println(m.mostAnagrams());
  }
}
