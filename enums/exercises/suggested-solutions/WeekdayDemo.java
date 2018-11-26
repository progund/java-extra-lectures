public class WeekdayDemo {

  public static void main(String[] args) {
    for (Weekday day : Weekday.values()) {
      testDay(day);
      whichIsGreater(day);
    }
  }

  private static void whichIsGreater(Weekday day) {
    Weekday sat = Weekday.SATURDAY;
    int result = day.compareTo(sat);
    System.out.print(day + " is ");
    if (result < 0) {
      System.out.println("less than (before) " + sat);
    } else if (result ==  0) {
      System.out.println("the same as " + sat);
    } else {
      System.out.println("greater than (after) " + sat);
    }
  }

  private static void testDay(Weekday day) {
    System.out.print("It's " + day);
    if (day.isWeekday()) {
      System.out.println(" Go to work.");
    } else {
      System.out.println(" Stay home and play with your cat.");
    }
  }
}
