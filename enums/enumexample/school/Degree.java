package school;

public class Degree {
  enum Level {
    BACHELOR,
    MASTER,
    DOCTORATE    
  }

  private String uni;
  private String programme;
  private Level level;
  
  public Degree(Level level, String programme, String uni) {
    this.level = level;
    this.programme = programme;
    this.uni = uni;
  }

  public Level level() {
    return level;
  }

  public String programme() {
    return programme();
  }

  public String uni() {
    return uni;
  }
  
  public String toString() {
    return new StringBuilder("" + level.toString().charAt(0))
      .append(level.toString().substring(1).toLowerCase())
      .append(" of ")
      .append(programme)
      .append(" at ")
      .append(uni)
      .toString();
  }
}

