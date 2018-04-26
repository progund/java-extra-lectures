import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Rot13 {

  static List<Character> alphabet;  
  static List<Character> cipher;
  static {
    alphabet = new ArrayList<>();
    for (char ch = 'a'; ch <= 'z'; ch++) {
      alphabet.add(ch);
    }
    cipher = new ArrayList<>(alphabet);
    Collections.rotate(cipher, 13);
    
  }
  
  static void usage() {
    System.err.println("USAGE java Rot13 string");
    System.exit(1);
  }
  
  public static void main(String[] args) {
    String input = "";
    String direction = "";
    if (args.length != 1) {
       usage();
    }
    input = args[0];
    String result = "";
    result = encode(input);
    System.out.println("Result: " + result);
    System.out.println(alphabet);
    System.out.println(cipher);
  }
  
  static String encode(String input) {
    char[] result = new char[input.length()];
    for (int i = 0; i < input.length(); i++)  {
      // translate the current character to its sipher equivalent
      if (cipher.contains(input.charAt(i))) {
        result[i] = cipher.get(alphabet.indexOf(input.charAt(i)));
      } else {
        result[i] = input.charAt(i);
      }
    }
    return new String(result);
  }
}
