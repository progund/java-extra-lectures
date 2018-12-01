import java.nio.file.*;
import java.io.*;
import java.util.Scanner;

public class Jcat {
  public static void main(String[] args) {
    try {
      if(args.length != 0) {
        for(int i = 0; i<args.length; i++){
          try {
            for (String line : Files.readAllLines(Paths.get(args[i]))) {
              System.out.println(line);
            }
          } catch (NoSuchFileException fne) {
            System.err.println("No such file or directory: " +
                               fne.getMessage());
          } catch (IOException e) {
            System.err.println(e);
          }
        }
      } else {
        Scanner sc = new Scanner(System.in);
        while( sc.hasNext()){
          System.out.println(sc.nextLine());
        }
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
