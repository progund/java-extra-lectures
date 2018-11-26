public class WeekdayDemo {

  public static void main(String[] args) {
    for (Weekday day : Weekday.values()) {
      testDay(day);
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
