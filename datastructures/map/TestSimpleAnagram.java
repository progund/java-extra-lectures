public class TestSimpleAnagram {

  public static void main(String[] args) {
    SimpleAnagram sa = new SimpleAnagram();
    String anagrams = sa.anagramsFor(args[0]).toString();
    System.out.println(anagrams.equals("[]") ? "No anagrams found." : anagrams);
    System.out.println("=====");
    System.out.println("Words with at least 8 anagrams:");
    System.out.println(sa.foundAnagrams(8));
  }
}
