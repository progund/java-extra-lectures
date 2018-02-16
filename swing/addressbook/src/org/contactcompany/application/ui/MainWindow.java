package org.contactcompany.application.ui;
import org.contactcompany.api.entry.Contact;
import org.contactcompany.application.list.ContactList;

//import com.sun.java.swing.plaf.gtk.GTKLookAndFeel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class MainWindow {
  private ContactList contacts;
  private JFrame frame;
  private JMenuBar menuBar;
  private JMenu contact;
  private JMenu file;
  private JMenuItem add;
  private JMenuItem search;
  private JMenuItem list;
  private JMenuItem quit;
  private JList<Contact> contactList;
  private DefaultListModel<Contact> resultsListModel;
  private JScrollPane contactsSP;
  private JPanel addPanel;
  private JPanel listPanel;
  private JPanel searchPanel;
  private JPanel resultsPanel;
  private JTextArea resultsTA;
  private JLabel statusLabel;
  private JLabel nameLabel;
  private JLabel searchNameLabel;
  private JLabel emailLabel;
  private JLabel phoneLabel;
  private JTextField nameTF;
  private JTextField searchNameTF;
  private JTextField emailTF;
  private JTextField phoneTF;
  private JButton addButton;
  private JButton cancelButton;
  private JButton cancelSearchButton;
  private JButton searchButton;

  private void fixLookAndFeel() {
    try {
      // Ignore the lines below - it's a fix for Rikard's computer. Hell Dell!
      javax.swing.UIManager
        .setLookAndFeel((javax.swing.LookAndFeel)Class
                        .forName("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
                        .newInstance());
    } catch (Exception ignore) {}
  }
  
  public MainWindow() {
    fixLookAndFeel();
    initComponents();
    layoutComponents();
    //frame.setSize(600,800);
    frame.pack();
  }

  private void showSearch() {
    setCenter(searchPanel);
  }
  
  private void setCenter(Component c) {
    frame.remove(addPanel);
    frame.remove(searchPanel);
    frame.remove(contactsSP);
    frame.remove(resultsTA);
    frame.getContentPane().add(c, BorderLayout.CENTER);
    frame.validate();
    //frame.setSize(600,800);
    frame.pack();
  }
  
  private void showList() {
       ((DefaultListModel<Contact>)contactList.getModel()).removeAllElements();
       Collections.sort(contacts.entries(), new Comparator<Contact>(){
           public int compare(Contact a, Contact b){
             return a.name().toLowerCase().compareTo(b.name().toLowerCase());
           }
         });
          for(Contact c : contacts.entries()){
            ((DefaultListModel<Contact>)contactList.getModel()).addElement(c);
          }
          setCenter(contactsSP);
  }
  
  private void addListeners() {
    list.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          showList();
        }
      });
    add.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          setCenter(addPanel);
        }
        
      });
    addButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          Contact c = new Contact(nameTF.getText(), emailTF.getText(), phoneTF.getText());
          contacts.addEntry(c);
          contacts.save();
          statusLabel.setText(contacts.numberOfEntries()+" contacts loaded from file");
          showList();
        }
      });

    cancelButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          showList();
        }
      });
    cancelSearchButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          showList();
        }
      });
    search.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          showSearch();
        }
      });
    searchButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          resultsTA.setText("");
          for(Contact c : contacts.entries()) {
            if(c.name().toLowerCase().startsWith(searchNameTF.getText().toLowerCase())){
              resultsTA.append(c.toString());resultsTA.append("\n");
            }
          }
          setCenter(resultsTA);          
        }
      });
  }
  
  private void layoutComponents() {
    listPanel.add(contactsSP);
    file.add(quit);
    menuBar.add(file);
    menuBar.add(contact);
    frame.add(menuBar, BorderLayout.NORTH);
    frame.add(statusLabel, BorderLayout.SOUTH);
    addListeners();
    GroupLayout layout = (GroupLayout)addPanel.getLayout();
    layout
      .setHorizontalGroup(layout.createSequentialGroup()
                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(nameLabel)
                                    .addComponent(emailLabel)
                                    .addComponent(phoneLabel)
                                    .addComponent(cancelButton))
                          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(nameTF)
                                    .addComponent(emailTF)
                                    .addComponent(phoneTF)
                                    .addComponent(addButton))
                          );
    layout
      .setVerticalGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                  .addComponent(nameLabel)
                                  .addComponent(nameTF))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                  .addComponent(emailLabel)
                                  .addComponent(emailTF))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                  .addComponent(phoneLabel)
                                  .addComponent(phoneTF))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                  .addComponent(cancelButton)
                                  .addComponent(addButton))
                        );
    layout.linkSize(SwingConstants.HORIZONTAL, cancelButton, addButton);
    searchPanel.add(searchNameLabel);searchPanel.add(searchNameTF);
    searchPanel.add(cancelSearchButton);searchPanel.add(searchButton);
    contact.add(add);
    contact.add(list);
    contact.add(search);
  }
  
  public void show() {
    frame.setVisible(true);
  }
  
  private void initMenus() {
    menuBar = new JMenuBar();
    contact = new JMenu("Contacts");
    file = new JMenu("File");
    add = new JMenuItem("Add contact");
    list = new JMenuItem("List contacts");
    quit = new JMenuItem("Quit");
    quit.addActionListener(new QuitListener());
    search = new JMenuItem("Search");
  }
  
  private void initAddPanel() {
    addPanel = new JPanel();
    GroupLayout layout = new GroupLayout(addPanel);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    //GridLayout addPanelLayout = new GridLayout(4,2);
    //addPanelLayout.setVgap(4);
    addPanel.setLayout(layout);
    nameLabel = new JLabel("Name:");
    emailLabel = new JLabel("Email:");
    phoneLabel = new JLabel("Phone:");
    nameTF = new JTextField(20);
    emailTF = new JTextField(20);
    phoneTF = new JTextField(20);
    cancelButton = new JButton("Cancel");
    addButton = new JButton("Add");
  }
  
  private void initSearchPanel() {
    searchButton = new JButton("Search");
    cancelSearchButton = new JButton("Cancel");
    searchPanel = new JPanel();
    searchPanel.setLayout(new GridLayout(2,2));
    resultsPanel = new JPanel();
    resultsTA = new JTextArea();
    searchNameLabel = new JLabel("Name:");
    searchNameTF = new JTextField(20);
    resultsPanel.add(resultsTA);
  }
                                
  private void initComponents() {
    contacts = new ContactList();
    contacts.load();
    statusLabel = new JLabel(contacts.numberOfEntries() + " contacts loaded from file");
    frame = new JFrame("Contact book");
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    listPanel = new JPanel();
    initMenus();
    resultsListModel = new DefaultListModel<Contact>();
    contactList = new JList<Contact>(resultsListModel);
    contactList.setVisibleRowCount(20);
    contactsSP = new JScrollPane(contactList);
    initAddPanel();
    initSearchPanel();
  }
  
  private class QuitListener extends WindowAdapter implements ActionListener{
    public void actionPerformed(ActionEvent ae) {
      frame.dispose();
    }
    
    public void WindowClosing(WindowEvent we) {
      frame.dispose();
    }
  }
}
