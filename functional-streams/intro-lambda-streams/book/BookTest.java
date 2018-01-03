import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class BookTest {
  public static void main(String[] args) {
    List<Book> books = someBooks();
    System.out.println(books);
    //Collections.sort(books, new BookYearComparator());
    //Collections.sort(books, (b1, b2) -> b1.year() - b2.year());
    Collections.sort(books, Comparator.comparing(Book::year));
    System.out.println(books);
  }
  static List<Book> someBooks() {
    Book[] books = {
      new Book("Java for dummies", "Henrik", 2012),
      new Book("Programming in the future", "Jan", 2039),
      new Book("Java for weirdos", "Henrik", 2013),
      new Book("C for university teachers", "Henrik", 2009),
      new Book("Ada for Beda", "Rikard", 2010)
    };
    return new ArrayList<Book>(Arrays.asList(books));
  }
}
