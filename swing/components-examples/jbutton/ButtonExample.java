import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import static java.awt.Color.RED;
import static java.awt.Color.BLACK;

public class ButtonExample {

  private JFrame window;
  private JButton closeButton;
  private JButton toggleButton;
  
  public ButtonExample() {
    initComponents();
    layoutComponents();
    addListeners();
  }

  private void initComponents() {
    window = new JFrame("Window example");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setSize(800, 600);
    closeButton = new JButton("Close");    
    toggleButton = new JButton("Toggle");
  }

  private void layoutComponents() {
    window.setLayout(new FlowLayout());
    window.add(closeButton);
    window.add(toggleButton);
  }

  private void addListeners() {
    closeButton.addActionListener(e -> window.dispose());
    toggleButton.addActionListener(e -> {
        if (toggleButton.getForeground() != RED) {
          toggleButton.setForeground(RED);
        } else {
          toggleButton.setForeground(BLACK);
        }
      });
    /* Or:
       closeButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent ae) {
           window.dispose();
         }
       });
     */
  }
  
  public void show() {
    window.setVisible(true);
  }
}
