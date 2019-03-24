import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet; // for alternative solution
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    // Using alternative solution to the titles union:
    System.out.println("Alternative solution - unique titles:");
    System.out.println(titlesUnion2(books1, books2));

    // Using alternative solution to the titles union:
    System.out.println("Alternative solution - unique titles:");
    System.out.println(titlesUnion3(books1, books2, books1, books2));
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

  /* Alternative solution using a helper method titlesSet() here: */
  static Set<String> titlesSet(List<Book> b) {
    return b.stream()
      .map(Book::title)
      .collect(Collectors.toSet());
  }
  static Set<String> titlesUnion2(List<Book> b1, List<Book> b2) {
    Set<String> titles = new HashSet<>(titlesSet(b1));
    titles.addAll(titlesSet(b2));
    return titles;
  }

  /* Another alternative solution to titlesUnion: */
  /* Accepts one or more book lists */
  @SafeVarargs
  static Set<String> titlesUnion3(List<Book>...bs) {
    Set<String> titles = new HashSet<>();
    for (List<Book> books : bs) {
        titles.addAll(titlesSet(books));
    }
    return titles;
  }
}
