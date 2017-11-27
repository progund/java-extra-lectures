public class Unicode {
  public static void main(String[] args) {
    int c = 0;

    // Some interesting strings:
    String honey = new StringBuilder().appendCodePoint(0x1F36F).toString();
    String bomb = new StringBuilder().appendCodePoint(0x1F4A3).toString();
    // What int value is 0x1F36F ?
    c = 0x1F36F;
    System.out.println("0x1F36F (unicode honeypot) is in decimal: " + c);
    
    bomb = "abcd" + bomb;

    // What int value is the code for the bomb?
    c = 0x1F4A3;
    System.out.println("0x1F4A3 (unicode bomb) is in decimal:     " + c);
    // The largest int value possible to store in a char:
    c = Character.MAX_VALUE;
    System.out.println("char type max value:                       " + c);


    // Print the honeypot:
    System.out.println(honey);
    // Print the string with the bomb:
    System.out.println(bomb);

    // Now, if we are looking for the index of the bomb in the
    // String with the bomb, indexOf() must accept an int!
    // Because the largest number we can store in a char is 65535
    System.out.println("The bomb is at " +    bomb.indexOf(128163));

    // Java is cool. We can actually use the
    // bomb character in the source code:
    String test = "💣";
    // And print it again :)
    System.out.println(test);

    String flag = new String(new int[] {0x1F1F8},0,1);
    System.out.println(flag);
    String smoking = new String(new int[] {0x1F6AD},0,1);
    System.out.println(smoking);
    String beer = new String(new int[] {0x1F37A},0,1);
    System.out.println(beer);


    String turd = new String(new int[] {0x1F4A9},0,1);
    System.out.println(turd);
    c = 0x1F4A9;
    System.out.println((int)turd.charAt(0));
    System.out.println("Is the turd as a char a surrogate? " +
                       Character.isSurrogate(turd.charAt(0)));
    System.out.println("A low one? " +
                       Character.isLowSurrogate(turd.charAt(0)));
    System.out.println("A high one? " +
                       Character.isHighSurrogate(turd.charAt(0)));
    System.out.println("Turds length: " + turd.length());
    System.out.println("First part:  " + Integer.toHexString(turd.charAt(0)));
    System.out.println("Second part: " + Integer.toHexString(turd.charAt(1)));

    // How to UTF-16 encode the turd:
    // Creating the pair

    // 1. subtract 0x010000
    c -= 0x010000; // Keep 16 bits

    // We now have: 1111010010101001

    // 2. keep the highest 10 bits, and add 0xD800 in the high surrogate
    int high = (c >> 10)   + 0xD800;

    // First:       1111010010101001 >> 10 = 1111010010
    //
    // Then:       1111010010 
    //       1101100000000000+ (0xD800)
    //       ----------------
    //       1101101111010010  (0xD83D) -> high
    
    // 3. keep the lowest 10 bits, and add 0xDC00 in the low surrogate
    int low  = (c & 0x3FF) + 0xDC00;

    //     1111010010101001
    //     0000001111111111 &
    //     ----------------
    //           0010101001

    // Add 0xDC00:
    //
    //           0010101001
    //     1101110000000000 +
    // --------------------
    //     1101110010101001 (0xDCA9)
    // Let's look at the resulting hex:
    System.out.println("High:" + Integer.toHexString(high));
    System.out.println("Low:" + Integer.toHexString(low));
    // Create a String using these to ints as chars
    System.out.println(new String(new int[] {high, low}, 0,2));
    // Will print a turd :-)
    
    // We have split up the large number 0x1F4A9
    // into two 16 bit values. Together they represent the turd.
    // This: String turd = new String(new int[] {0x1F4A9},   0, 1);
    // is the same as:
    // System.out.println( new String(new int[] {high, low}, 0, 2));
    //
    // 0x1F4A9        11111010010101001 (int)
    // is to a String, the same as two chars:
    //                 1101101111010010 and
    //                 1101110010101001 
  }
}

