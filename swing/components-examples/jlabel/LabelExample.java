import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class LabelExample {

  private JFrame window;
  private JButton linkButton;
  private JLabel link;
  private ActionListener linkChanger;
  private JTextField linkTextField;
  
  public LabelExample() {
    initComponents();
    layoutComponents();
    addListeners();
  }

  private void initWindow() {
    window = new JFrame("JLabel link example");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setSize(800, 600);
  }
  private void initComponents() {
    initWindow();
    linkButton = new JButton("Create link");
    link = new JLabel("link will show up here");
    link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    linkTextField = new JTextField("enter a url here, please");
    linkTextField.selectAll();
    linkTextField.requestFocus();
    linkTextField.setToolTipText(new StringBuilder("<html>A tooltip can have links: <a href=\"http://wiki.juneday.se\">")
                                  .append("wiki.junday.se")
                                  .append("</a></html>")
                                  .toString());
  }

  private void layoutComponents() {
    window.setLayout(new FlowLayout());
    window.add(link);
    window.add(linkTextField);
    window.add(linkButton);
  }

  
  
  private void addListeners() {
    linkChanger = e -> {
      link.setText("<html><a href=\"" + linkTextField.getText() + "\">" +
                   linkTextField.getText() + "</a></html>");
      linkTextField.requestFocus();
      linkTextField.selectAll();
    };
    linkTextField.addActionListener(linkChanger);
    linkButton.addActionListener(linkChanger);
    link.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent me) {
            try {
              Desktop.getDesktop().browse(new URL(linkTextField.getText()).toURI());
            } catch (Exception e) {
              System.err.println("Error clicking link:" + e.getMessage());
              link.setText("malformed url");
            }
          }
      });
  }
  
  public void show() {
    window.setVisible(true);
  }
}
