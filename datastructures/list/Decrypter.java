import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Decrypter {

  private String encrypted;
  
  public Decrypter(String encrypted) {
    this.encrypted = encrypted;
  }

  public String decrypt() {
    return new Encrypter(encrypted).encrypt();
  }
  
}
