import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class Flow {
  private static void fixLookAndFeel() {
    try {
      // Ignore the lines below - it's a fix for Rikard's computer. Hell Dell!
      javax.swing.UIManager
        .setLookAndFeel((javax.swing.LookAndFeel)Class
                        .forName("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
                        .newInstance());
    } catch (Exception ignore) {}
  }
  
  private static void createAndShowGUI() {
    //Create and set up the window.
    JFrame frame = new JFrame("FlowLayout");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(480,200));
    frame.setLayout(new FlowLayout());
    JButton button1 = new JButton("First button");
    JButton button2 = new JButton("Second button");
    JButton button3 = new JButton("Third");
    JButton button4 = new JButton("Button 4");
    JButton button5 = new JButton("The last of the buttons");
    frame.add(button1);
    frame.add(button2);
    frame.add(button3);
    frame.add(button4);
    frame.add(button5);
    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }
 
  public static void main(String[] args) {
    fixLookAndFeel();
    // Threading is tricky. We'll leave that to Java for now!

    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          createAndShowGUI();
        }
      });
  }
}
