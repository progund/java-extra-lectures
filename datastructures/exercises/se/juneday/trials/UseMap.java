package se.juneday.trials;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Set;

import se.juneday.domain.Contact;
import se.juneday.util.Kreator;

public class UseMap {

  public static void main(String[] args) {

    int nrContacts = 10;
    
    Map<String, Contact> contacts = new HashMap<>();    
    System.out.print("Creating map of Contacts:");
    for (int i=0; i<nrContacts; i++) {
      Contact c = Kreator.createContact();
      contacts.put(c.name(),c);
    }
    System.out.println(" " + contacts.size() + " contacts in the map");

    // Get one element from the map using a key
    // Add a user to the map - so we know that the key has acorresponding Contact
    Contact c = new Contact("Henrikard", "henrikard@juneday.se", "3276234");
    contacts.put(c.name(), c);
    System.out.println(" Get Henrikard: " + contacts.get("Henrikard"));
    
    // Get one element from the map using a key (that does not exist in the map)
    System.out.println(" Get Ozzy Osbourne: " + contacts.get("Ozzy Osbourne"));

    // Get all the values from the map
    Collection<Contact> contactCollection = contacts.values();
    System.out.println("Values");
    for (Contact con : contactCollection) {
      System.out.println(" * " + con);
    }

    // Get all the keys from the map
    System.out.println("Keys");
    Set<String> keys = contacts.keySet();
    for (String key : keys) {
      System.out.println(" * " + key);
    }
    
    // Get all the keys and values from the map
    System.out.println("Entries");
    Set<Map.Entry<String,Contact>> entries = contacts.entrySet();
    for (Map.Entry<String,Contact> contactEntry : entries) {
      System.out.println(" * " + contactEntry.getKey() + ":" + contactEntry.getValue());
    }

    // Create Contacts and use integer as key
    Map<Integer, Contact> contactsIC = new HashMap<>();    
    System.out.print("Creating map <I,C> of Contacts:");
    for (int i=0; i<nrContacts; i++) {
      contactsIC.put(i,Kreator.createContact());
    }
    System.out.println(" " + contactsIC.size() + " contacts in the map");
    // ... and get all the keys from the map
    System.out.println("Keys");
    Set<Integer> keysI = contactsIC.keySet();
    for (Integer key : keysI) {
      System.out.println(" * " + key);
    }
    
    
  }
  
}

