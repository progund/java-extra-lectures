package se.juneday.trials;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import se.juneday.domain.Contact;
import se.juneday.util.Kreator;


public class TryList {

  private enum ListType {
    ARRAY_LIST,
    LINKED_LIST
  } 

  private enum Action {
    SORT,
    ADD,
    REMOVE,
    FIND,
    NAIVE_FIND
  } 

  public ListType listType = ListType.ARRAY_LIST;
  private int nrContacts = 10_000_000;
  private List<Contact> contacts;
  private Action action = Action.SORT;


  private void sortTest() {
    long start = System.currentTimeMillis();
    Collections.sort(contacts);
    long stop = System.currentTimeMillis();

    System.out.println("sort       | " +
                       contacts.size() + " contacts | " +
                       contacts.getClass().getSimpleName() + " | " +
                       (stop - start) + " milli seconds");
  }

  private void removeTest(int position) {
    long start = System.currentTimeMillis();
    contacts.remove(position);
    long stop = System.currentTimeMillis();

    System.out.println("remove     | " +
                       "at " + position + " | "+
                       contacts.getClass().getSimpleName() + " | " +
                       (stop - start) + " milli seconds");
  }
  
  private void addTest(Contact contact, int position) {
    int size = contacts.size();

    long start = System.currentTimeMillis();
    contacts.add(contact);
    long stop = System.currentTimeMillis();

    System.out.println("add        | " +
                       "at " + position + " | "+
                       contacts.getClass().getSimpleName() + " | " +
                       (stop - start) + " milli seconds");
  }


  private void findTest() {
    //    System.out.println("Inserting contact at position: " + position + ". Now: " + contacts.size()  + " elements in the list");

    long sortStart = System.currentTimeMillis();
    Collections.sort(contacts);
    long sortStop = System.currentTimeMillis();
    //System.out.println("Sorting the list took: " + (stop-start) + " milli seconds");

    // Get the Contact in the middle
    int replacePosition = contacts.size()/2;
    Contact old = contacts.get(replacePosition);
    // Replace Contact with a new Contact with a slightly altered name
    Contact unique = new Contact(old.name()+" _unique", old.email(), old.phone());
    contacts.set(replacePosition, unique);
    // Sort again
    Collections.sort(contacts);
    
    
    long start = System.currentTimeMillis();
    int pos = Collections.binarySearch(contacts, unique);
    long stop = System.currentTimeMillis();

    if (pos<0) {
      System.out.println("Failed finding : " + unique.name());
    }//  else {
    //   System.out.println("Found \"" + contacts.get(pos).name() + "\" at position: " + pos);
    // }

    System.out.println("find       | " +
                       "at " + pos + " | "+
                       contacts.getClass().getSimpleName() + " | " +
                       (stop - start) + " milli seconds | " +
                       (sortStop - sortStart) + " milli seconds sorting ");
  }

  private void findNaiveTest() {

    // Get the Contact in the middle
    int replacePosition = contacts.size()/2;
    Contact old = contacts.get(replacePosition);
    // Replace Contact with a new Contact with a slightly altered name
    Contact unique = new Contact(old.name()+"_unique", old.email(), old.phone());
    contacts.set(replacePosition, unique);

    int pos = -1;
    long start = System.currentTimeMillis();
    int i=0;
    Iterator it = contacts.iterator();
    while (it.hasNext()) {
      i++;
      Contact c = (Contact) it.next();
      if (c.equals(unique)) {
        pos = i;
        break;
      }
    }
    long stop = System.currentTimeMillis();

    if (pos<0) {
      System.out.println("Failed finding : " + unique.name());
    }//  else {
    //   System.out.println("Found \"" + contacts.get(pos).name() + "\" at position: " + pos);
    // }

    System.out.println("naive find | " +
                       "at " + pos + " | "+
                       contacts.getClass().getSimpleName() + " | " +
                       (stop - start) + " milli seconds");
  }
  
  
  private static void usage() {
    System.out.println("TryList  [OPTIONS]   <target>");
    System.out.println();
    System.out.println("OPTIONS");
    System.out.println("   --linked-list, -ll   use LinkedList");
    System.out.println("   --array-list, -al    use ArrayList (default)");
    //    System.out.println("   --contacts nr        number of elements. Default: " + nrContacts);
    System.out.println();
    System.out.println("TARGETS");
    System.out.println("   sort                   sorting list (default)");
    System.out.println("   remove                 remove elements");
    System.out.println("   add                    add elements");
    System.out.println("   find                   find elements");
    System.out.println("   naive                  find elements naively");
    System.out.println();
  }

  public void parseArgs(String[] args) {
    for(int i=0; i<args.length; i++) {
      switch (args[i]) {
      case "--linked-list":
      case "-ll":
        listType = ListType.LINKED_LIST;
        break;
      case "--array-list":
      case "-al":
        listType = ListType.ARRAY_LIST;
        break;
      case "--contacts":
      case "-c":
        nrContacts = Integer.parseInt(args[i+1]);
        i++;
        break;
      case "sort":
        action = Action.SORT;
        break;
      case "add":
        action = Action.ADD;
        break;
      case "remove":
        action = Action.REMOVE;
        break;
      case "find":
        action = Action.FIND;
        break;
      case "naive":
        action = Action.NAIVE_FIND;
        break;
      default:
        System.err.println("SYNTAX ERROR: " + args[i]);
        System.err.println();
        usage();
        System.exit(1);
      }
      
      
    }
  }

  private void setup() {
    // System.out.println();
    // System.out.println("Setting up");
    // System.out.println("------------------------");
    if (listType == ListType.ARRAY_LIST) {
      contacts = new ArrayList<>(nrContacts);
    } else {
      contacts = new LinkedList<>();
    }
    // System.out.println(" * Created: " + contacts.getClass().getSimpleName());

    for (int i=0; i<nrContacts; i++) {
      contacts.add(Kreator.createContact());
    }
    // System.out.println(" * Added:   " + nrContacts + " Contacts");
    // System.out.println();
  }
  
  public void takeAction() {
    Contact contact;
    // System.out.println("Action");
    // System.out.println("-------------------------");
    switch (action) {
    case SORT:
      sortTest();
      break;
    case REMOVE:
      removeTest(0);
      removeTest(nrContacts/2); 
      removeTest(nrContacts - 3 ); //to has been removed ;)
      break;
    case ADD:
      contact = Kreator.createContact();
      addTest(contact, 0);
      addTest(contact, nrContacts/2);
      addTest(contact, nrContacts + 1); //2 added to the list and -1 for the indexing from 0
      break;
    case FIND:
      findTest();
      break;
    case NAIVE_FIND:
      // Create unique contact
      contact = new Contact("LReallyoddname ThatsNotInTheList", "@", "1209");
      findNaiveTest();
      break;
    }
  }
  
  public static void main(String[] args) {
    TryList t = new TryList();
    
    t.parseArgs(args);

    t.setup();
    
    t.takeAction();
    

    
    
    //List<Contact> contacts = new ArrayList<>(nrContacts);
    

    /*
    */

    /*
    */

    
  }
  
}
