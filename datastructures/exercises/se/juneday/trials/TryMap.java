package se.juneday.trials;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

import se.juneday.domain.Contact;
import se.juneday.util.Kreator;

public class TryMap {

  private static final int nrContacts = 10_000_000;

  private static void findTest(Map<String, Contact> contacts, Contact contact) {
    int size = contacts.size();
    contacts.put(contact.name(), contact);

    long start = System.currentTimeMillis();
    contacts.get(contact.name());
    long stop = System.currentTimeMillis();

    System.out.println("Finding a Contact out of " + 
                       size + " contacts by name " +
                       " (" + contacts.getClass().getName() + ")"
                       + " took " + (stop - start) + " milli seconds");
  }
  
  public static void main(String[] args) {

    Map<String, Contact> contacts = new HashMap<>();
    
    System.out.print("Creating map of Contacts:");
    for (int i=0; i<nrContacts; i++) {
      Contact c = Kreator.createContact();
      contacts.put(c.name()+"_"+i, c);
    }
    System.out.println(" " + contacts.size() + " contacts created");

    Contact contact = new Contact("Reallyoddname ThatsNotInTheList", "@", "1209");
    findTest(contacts, contact);
    
    System.out.println("Tests finished");
  }
  
}

