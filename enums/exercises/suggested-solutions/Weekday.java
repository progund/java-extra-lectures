public enum Weekday {
  MONDAY,
  TUESDAY,
  WEDNESDAY,
  THURSDAY,
  FRIDAY,
  SATURDAY,
  SUNDAY;

  public boolean isWeekday() {
    return this != SATURDAY && this != SUNDAY;
  }

  public boolean isHoliday() {
    return !isWeekday();
  }
}
