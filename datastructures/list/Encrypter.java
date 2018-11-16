import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Encrypter {

  private String plainText;

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
  
  public Encrypter(String text) {
    this.plainText = text;
  }

  public String encrypt() {
    char[] result = new char[plainText.length()];
    for (int i = 0; i < plainText.length(); i++)  {
      // translate the current character to its sipher equivalent
      if (cipher.contains(plainText.toLowerCase().charAt(i))) {
        result[i] = cipher.get(alphabet
                               .indexOf(plainText.toLowerCase().charAt(i)));
      } else {
        result[i] = plainText.charAt(i);
      }
    }
    return new String(result);    
  }
}
