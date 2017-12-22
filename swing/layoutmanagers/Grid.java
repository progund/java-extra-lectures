import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class Grid {
  private static void createAndShowGUI() {
    //Create and set up the window.
    JFrame frame = new JFrame("GridLayout");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(200,200));
    frame.setLayout(new GridLayout(4,3));
    JButton button9 = new JButton("9");
    JButton button8 = new JButton("8");
    JButton button7 = new JButton("7");
    JButton button6 = new JButton("6");
    JButton button5 = new JButton("5");
    JButton button4 = new JButton("4");
    JButton button3 = new JButton("3");
    JButton button2 = new JButton("2");
    JButton button1 = new JButton("1");
    JButton buttonStar = new JButton("*");
    JButton buttonPound = new JButton("#");
    JButton button0 = new JButton("0");
    frame.add(button7);
    frame.add(button8);
    frame.add(button9);
    frame.add(button4);
    frame.add(button5);
    frame.add(button6);
    frame.add(button1);
    frame.add(button2);
    frame.add(button3);

    frame.add(buttonStar);
    frame.add(button0);
    frame.add(buttonPound);

    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }
 
  private static void fixLookAndFeel() {
    try {
      // Ignore the lines below - it's a fix for Rikard's computer. Hell Dell!
                  javax.swing.UIManager
                    .setLookAndFeel((javax.swing.LookAndFeel)Class
                                    .forName("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
                                    .newInstance());
    } catch (Exception ignore) {}
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
