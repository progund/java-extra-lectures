public class Book {
  private String author;
  private String title;
  private int year;

  public Book(String author, String title, int year) {
    this.author = author;
    this.title = title;
    this.year = year;
  }

  public String author() { return author; }
  public String title() {return title; }
  public int year() { return year; }
  public String toString() { return author + " " + title + " " + year; }
}
