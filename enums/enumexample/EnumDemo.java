public class EnumDemo {

  enum Repeat {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE;
    public void times(java.util.function.Consumer<String> con, String s) {
      for (int i = 0; i < ordinal() + 1; i++) {
        con.accept(s);
      }
    }
  }
  
  enum Status {
    PUBLIC,
    PROTECTED,
    PRIVATE;

    @Override
    public String toString() {
      return name().charAt(0) +
        name().toLowerCase().substring(1, name().length());
    }
  }

  enum StringFrom {
    REVERSE {
      public String of(String s) {
        return new StringBuilder(s).reverse().toString();
      }
    },
    TO_LOWER {
      public String of(String s) {
        return s.toLowerCase();
      }
    },
    TO_UPPER {
      public String of(String s) {
        return s.toUpperCase();
      }
    },
    UPPER_FIRST {
      public String of(String s) {
        return Character.toUpperCase(s.charAt(0)) +
          s.toLowerCase().substring(1, s.length());
      }
    };

    public abstract String of(String s);
  }

  
  public static void main(String[] args) {
    // Getting Status.PUBLIC as the string "Public":
    String pub = Status.PUBLIC.toString();

    // Creating a reference to Staus.PROTECTED from a String:
    Status status = Status.valueOf(Status.class, "PROTECTED");

    System.out.println(pub); // prints "Public"
    System.out.println(status); // prints "Protected"

    Repeat.FIVE.times(System.out::println, "Hello");

    System.out.println(StringFrom.REVERSE.of("slipup desserts"));
    System.out.println(StringFrom.UPPER_FIRST.of(StringFrom.REVERSE.of("slipup desserts")));

    // Make sure the name has a capital first letter
    // and the rest in lower case:
    String name = "rikard";
    String correct = StringFrom.UPPER_FIRST.of(name);
    System.out.println(correct); // Prints "Rikard"
    
  }
}
