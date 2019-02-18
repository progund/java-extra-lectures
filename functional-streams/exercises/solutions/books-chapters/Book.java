import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public final class Book {

  private final List<Chapter> chapters;
  private final String title;

  public Book(String title) {
    this(title, new ArrayList<>());
  }

  public Book(String title, List<Chapter> chapters) {
    this.title = title;
    this.chapters = chapters;
  }
  
  public String title() {
    return title;
  }

  public List<Chapter> chapters() {
    return Collections.unmodifiableList(chapters);
  }

  public int pages() {
    return chapters
      .stream()
      .mapToInt(Chapter::pages)
      .sum();
  }

  public String toString() {
    return new StringBuilder(title)
      .append(" (")
      .append(pages())
      .append(" pages)\n")
      .append("Chapters: ")
      .append(chapters)
      .toString();
  }
}
