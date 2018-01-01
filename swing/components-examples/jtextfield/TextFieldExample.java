import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import static java.awt.Color.*;
import java.awt.Color;

public class TextFieldExample {

  private JFrame window;
  private JButton colorButton;
  private JLabel prompt;
  private ActionListener colorChanger;
  private JTextField colorTextField;
  
  public TextFieldExample() {
    initComponents();
    layoutComponents();
    addListeners();
  }

  private void initWindow() {
    window = new JFrame("JTextField example");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setSize(800, 600);
  }
  private void initComponents() {
    initWindow();
    colorButton = new JButton("Change color");
    prompt = new JLabel("Color in hex:");
    colorTextField = new JTextField("000000");
    colorTextField.selectAll();
    colorTextField.requestFocus();
    colorTextField.setToolTipText(new StringBuilder("<html><ul><li>Red: FF0000</li>")
                                  .append("<li>Green: 00FF00</li>")
                                  .append("<li>Blue: 0000FF</li></ul></html>")
                                  .toString());
  }

  private void layoutComponents() {
    window.setLayout(new FlowLayout());
    window.add(prompt);
    window.add(colorTextField);
    window.add(colorButton);
  }

  
  
  private void addListeners() {
    colorChanger = e -> {
      Color newColor = null;
      try {
        newColor = Color.decode("0x" + colorTextField.getText());
      } catch (NumberFormatException ignore) {}
      if (newColor != null) {
        prompt.setForeground(newColor);
      } else {
        prompt.setForeground(BLACK);
        colorTextField.setText("??????");
        colorTextField.requestFocus();
        colorTextField.selectAll();
      }
    };
    colorTextField.addActionListener(colorChanger);
    colorButton.addActionListener(colorChanger);
  }
  
  public void show() {
    window.setVisible(true);
  }
}
