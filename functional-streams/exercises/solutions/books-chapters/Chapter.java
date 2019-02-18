public class Chapter {

  private int pages;

  private String name;

  public Chapter(String name, int pages) {
    this.name = name;
    this.pages = pages;
  }

  public int pages() {
    return pages;
  }

  public String name() {
    return name;
  }
  public String toString() {
    return new StringBuilder(name)
      .append(" (")
      .append(pages)
      .append(" pages)")
      .toString();
  }
}
