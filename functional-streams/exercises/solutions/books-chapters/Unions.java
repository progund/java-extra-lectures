import java.util.Set;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Optional;

public class Unions {
  static List<Book> getBooks() {
    return new ArrayList<>(Arrays.asList(new Book("Java"),
                                       new Book("C"),
                                       new Book("Databases"),
                                       new Book("Web")));
  }

  static List<Book> getBooksWithChapters() {
    List<Book> books =
      new ArrayList<>(Arrays
                      .asList(new Book("Java",
                                       Arrays.asList(new Chapter("Variables",
                                                                 30),
                                                     new Chapter("Methods",
                                                                 48),
                                                     new Chapter("Classes",
                                                                 44))),
                              new Book("Databases",
                                       Arrays.asList(new Chapter("SELECT",
                                                                 40),
                                                     new Chapter("DELETE",
                                                                 38),
                                                     new Chapter("JOIN",
                                                                 24)))));
    return books;
  }
  
  public static void main(String[] args) {
    List<Book> books1 = getBooks();
    List<Book> books2 = getBooks();
    books2.add(new Book("JavaScript"));
    Set<String> uniqueTitles = titlesUnion(books1, books2);
    System.out.println(uniqueTitles);
    List<Book> books3 = getBooksWithChapters();
    for (Book b : books3) {
      System.out.println(b.title() + " has " + b.pages() + " pages");
    }

    System.out.println("Finding book by title Java: " +
                       findByTitle(books3, "Java"));
    System.out.println("Finding book by title Java: " +
                       findByTitle2(books3, "Databases"));
    
  }

  static Book findByTitle(List<Book> books, String title) {
    return findByTitle_(books, title).orElse(new Book(title));
  }
  
  static Optional<Book> findByTitle_(List<Book> books, String title) {
    return books
      .stream()
      .filter(b -> b.title().equals(title))
      .findFirst();
  }

  static Book findByTitle2(List<Book> books, String title) {
    return books
            .stream()
            .filter(b -> b.title().equals(title))
            .findFirst().orElse(new Book(title));
  }

  static Set<String> titlesUnion(List<Book> b1, List<Book> b2) {
    return Stream.concat(b1.stream(), b2.stream())
      .map(Book::title)
      .collect(Collectors.toSet());
  }

}
