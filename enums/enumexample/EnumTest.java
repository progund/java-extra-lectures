package enumexample;

public class EnumTest {
    public static void main(String[] args) {
        for(WeekDay day : WeekDay.values()) {
            System.out.printf("%s is in Swedish: %s and is %s\n",
                              day,
                              day.swedishName(),
                              (day.isHoliday() ? "a holiday" : "a weekday."));
        }
        WeekDay aDay = WeekDay.SATURDAY;
        System.out.printf("The Swedish name for %s is %s\n",
                          aDay, aDay.swedishName());
    }
}
