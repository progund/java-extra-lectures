public class Rot13vOO {
  public static void main(String[] args) {
    if (args.length != 2 ||
        !args[0].equals("-e") && !args[0].equals("-d")) {
      System.err.println("Usage:");
      System.err.println("java Rot13vOO -<option> \"(lower case) text\"");
      System.err.println("  -e\tEncrypt text");
      System.err.println("  -d\tDecrypt text");
      System.exit(1);
    }

    String mode = args[0];
    String text = args[1];
    String result = "";

    switch (mode) {
    case "-e":
      Encrypter enc = new Encrypter(text);
      result = enc.encrypt();
      break;
    case "-d":
      Decrypter dec = new Decrypter(text);
      result = dec.decrypt();
      break;
    default:
      assert false : "Option parse error";
    }

    System.out.println(result);

  }
}
