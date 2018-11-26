package se.juneday.util.test;

import se.juneday.domain.Contact;
import se.juneday.util.Kreator;

public class KreatorTest {

  public static void main(String[] args) {
    Contact c = Kreator.createContact();
    System.out.println("Test contact generator (Kreator)");
    System.out.println("New contact: " + c);
    System.out.print("Name: ");
    assert ( (c.name() != null) &&
             (c.name().length() > 0)  ) : "Fail, name incorrect: " + c.name();
    System.out.println("OK");
    
    System.out.print("Email: ");
    assert ( (c.email() != null) &&
             (c.email().length() > 0) &&
             c.email().contains("@") ) : "Fail, email incorrect: " + c.email();
    System.out.println("OK");

    System.out.print("Phone: ");
    assert ( (c.phone() != null) &&
             (c.phone().length() > 0) ) : "Fail, phone incorrect: " + c.email();
    System.out.println("OK");
  }
}
