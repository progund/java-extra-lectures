package school;
import static school.Degree.Level.*;

public class DegreeDemo {
  public static void main(String[] args) {
    Degree master = new Degree(MASTER, "Informatics", "GU");

    if (master.level() != BACHELOR) {
      System.out.println(master + " is a higher degree.");      
    } else {
      System.out.println(master + " is a lower degree.");
    }
  }
}
