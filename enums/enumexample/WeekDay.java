package enumexample;

public enum WeekDay {
  SUNDAY("Söndag"),
  MONDAY("Måndag"),
  TUESDAY("Tisdag"),
  WEDNESDAY("Onsdag"),
  THURSDAY("Torsdag"),
  FRIDAY("Fredag"),
  SATURDAY("Lördag");
  private String swedish;
    
  private WeekDay(String swedish) {
    this.swedish = swedish;
  }
    
  public String swedishName() {
    return swedish;
  }
    
  public boolean isHoliday() {
    return this == SATURDAY || this == SUNDAY;
  }
    
  public boolean isWeekday() {
    return !isHoliday();
  }
    
  @Override
  public String toString() {
    return upperFirst(name());
  }
    
  private String upperFirst(String s) {
    return s.charAt(0) + s.substring(1).toLowerCase();
  }
}
