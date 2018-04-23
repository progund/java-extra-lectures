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
    alphabet.add(' ');

    cipher = new ArrayList<>(alphabet);
    Collections.rotate(cipher, 13);
    
  }
  
  static void usage() {
    System.err.println("USAGE java Rot13 string encode|decode");
    System.err.println(" where string contains [a-zA-Z ]*");
    System.err.println(" (letters between a-z, A-Z and space)");
    System.exit(1);
  }
  
  public static void main(String[] args) {
    String input = "";
    String direction = "";
    if (args.length == 2) {
      input = args[0].toLowerCase();
      if (! "encode".equals(args[1]) && ! "decode".equals(args[1])) {
        usage();
      }
      direction = args[1];
    } else {
      usage();
    }
    String result = "";
    if (direction.equals("encode")) {
      result = encode(input);
    } else {
      result = decode(input);
    }
    System.out.println("Result: <" + result + ">");
    System.out.println(alphabet);
    System.out.println(cipher);
  }
  
  static String encode(String input) {
    char[] result = new char[input.length()];
    for (int i = 0; i < input.length(); i++)  {
      // translate the current character to its sipher equivalent
      result[i] = cipher.get(alphabet.indexOf(input.charAt(i)));
    }
    return new String(result);
  }

  static String decode(String input) {
    char[] result = new char[input.length()];
    for (int i = 0; i < input.length(); i++)  {
      // translate the current character to its alphabet equivalent
      result[i] = alphabet.get(cipher.indexOf(input.charAt(i)));
    }
    return new String(result);
  }
}
