interface CaseFixer {
  public String ucFirst(String s);
}

public class Uc {
  public static void main(String[] args) {
    /*
    CaseFixer caseFixer =
      s -> Character.toUpperCase(s.charAt(0)) + s.substring(1);
    */
    CaseFixer caseFixer = s -> {
      if (s == null || s.equals("")) {
        return "";
      } else {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
      }
    };
    for (String arg : args) {
      System.out.println(fixIt(arg, caseFixer));
    }
    // Test "" and null:
    System.out.println(fixIt("", caseFixer));
    System.out.println(fixIt(null, caseFixer));
  }
  static String fixIt(String s, CaseFixer cf) {
    return cf.ucFirst(s);
  }
}
