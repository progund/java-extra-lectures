package org.contactcompany.application.ui;

import org.contactcompany.api.container.SimpleMutableList;
import org.contactcompany.api.entry.Contact;
import org.contactcompany.api.ui.textmenu.Menu;
import org.contactcompany.api.ui.textmenu.MenuAction;
import org.contactcompany.application.list.ContactList;
import static org.contactcompany.api.util.TextUtils.askFor;
import java.util.Iterator;

public class MenuWithContactList{
  private SimpleMutableList<Contact> list;
  private Menu menu = new Menu("Address book");

  /**
   * Constructs a new MenuWithContactList
   * with a fresh address book (ContactList) loaded from file
   * if the file exists.
   */
  public MenuWithContactList(){
    list = new ContactList();
    list.load();
    System.out.println(list.numberOfEntries() +
                       " items loaded from file.");
  }
  private void createMenu(){
    /* Add a menu item for listing all contacts */
    menu.addMenuItem("List", new MenuAction(){
        public void onItemSelected(){
          list.listEntries();
        }
      });
    /* Add a menu item for adding a contact */
    menu.addMenuItem("Add", new MenuAction(){
        public void onItemSelected(){
          String name = askFor("Name: ");
          String email = askFor("Email: ");
          String phone = askFor("Phone: ");
          // Only accept real names!
          while("".equals(name)){
            System.out.println("Name cannot be empty");
            name = askFor("Name: ");
          }
          list.addEntry(new Contact(name, email, phone));
          list.save();
        }
      });
    /*
    menu.addMenuItem("Search", new MenuAction(){
        public void onItemSelected(){
          String name = askFor("Full name: ");
          if(list.contains(new Contact(name, null, null))){
            System.out.println(list.get(new Contact(name, null, null)));
          }else{
            System.out.println("Not found.");
          }
        }
      });
    */
  }

  /**
   * Starts the application by setting up and starting the menu system.
   */
  public void start(){
    createMenu();
    menu.start();
  }
}
