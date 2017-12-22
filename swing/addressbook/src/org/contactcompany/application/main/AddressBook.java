package org.contactcompany.application.main;
import  org.contactcompany.application.ui.MenuWithContactList;
import  org.contactcompany.application.ui.MainWindow;

public class AddressBook{
  public static void main(String[] args){
    String ui = System.getProperty("ui");
    // Default: Run CLI (command line interface)
    if ( ui == null ||
         ui.equals("cli")){
      new MenuWithContactList().start();
    } else if (ui.equals("gui")) {
      try{
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              new MainWindow().show();
            }
          });
        //new MenuWithContactList().start();
      }catch(Exception e){
        e.printStackTrace();
        System.err.println("Fatal error. Please see logfile...bla");
      }
    }
  }
}
