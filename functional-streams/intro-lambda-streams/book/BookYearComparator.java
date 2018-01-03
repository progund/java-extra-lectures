import java.util.Comparator;

public class BookYearComparator implements Comparator<Book> {
  public int compare(Book first, Book second) {
    return first.year() - second.year();
  }
}
