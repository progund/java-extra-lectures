import java.nio.file.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class BookJoin {
  static String authorsFile = "authors.csv";
  static String titlesFile = "titles.csv";
  public static void main(String[] args) {

    Map<String, String> isbnToAuthor = new HashMap<>();
    Map<String, String> isbnToTitle = new HashMap<>();

    try {
      for (String line : Files.readAllLines(Paths.get(authorsFile)) ) {
        isbnToAuthor.put(line.split(",")[1], line.split(",")[0]);
      }
    } catch (Exception e) {
      System.err.println("Error reading data: " + e.getMessage());
    }

    try {
      for (String line : Files.readAllLines(Paths.get(titlesFile)) ) {
        isbnToTitle.put(line.split(",")[1], line.split(",")[0]);
      }
    } catch (Exception e) {
      System.err.println("Error reading data: " + e.getMessage());
    }

    List<Book> books = join(isbnToTitle, isbnToAuthor);
    System.out.println(books);
  }

  private static List<Book> join(Map<String, String> titles,
                          Map<String, String> authors) {

    List<Book> books = new ArrayList<>();
    
    for (String isbn : titles.keySet()) {
      books.add( new Book(isbn, titles.get(isbn), authors.get(isbn)) ); 
    }
    return books;
  }
  
}
class Book {
  private String isbn;
  private String title;
  private String author;

  public Book(String isbn, String title, String author) {
    this.isbn = isbn;
    this.title = title;
    this.author = author;
  }

  public String toString() {
    return new StringBuilder(title)
      .append(" by ")
      .append(author)
      .append(" with isbn: ")
      .append(isbn)
      .toString();
  }
}
