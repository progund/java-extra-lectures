package student;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import student.data.*;

//import java.util.ArrayList;

public class StudentSwing{

    private StudentData data = new StudentDataDB();

    private JFrame frame;
    private JLabel label;
    private JTextField input;
    private JButton search;
    private JList result;
    private DefaultListModel<String> resultModel;
    private void initApplication(){
        frame = new JFrame("Student courses");
        label = new JLabel("Enter student ID:");
        input = new JTextField(4); // four chars wide
        input.setText("<ID>");     // hint to the user what to type
        input.selectAll(); // select the text
        search = new JButton("Search");
        search.setToolTipText("Click here to find the courses for this student");
        resultModel = new DefaultListModel<String>();
        result = new JList<String>(resultModel);
        resultModel.addElement("Result of courses");// Hint that here will the courses show up
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Add action listeners
        addListeners();

        // Place the components
        placeComponents();
        // Show it
        frame.setVisible(true);
    }
    private void placeComponents(){
        frame.setLayout(new BorderLayout());
        JPanel north=new JPanel();
        north.add(label);
        north.add(input);
        north.add(search);
        frame.add(north, BorderLayout.NORTH);
        frame.add(result, BorderLayout.CENTER);
    }
    private class SearchListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String id=input.getText();
            resultModel.removeAllElements();
            if(data.studentIDExists(id)){
                resultModel.addElement(data.getStudentName(id)+" is registered to:");
                for(String course : data.getCourses(id)){
                    resultModel.addElement(course);
                }
                // Give the user a chance to write a new ID
                input.requestFocus();
                input.selectAll();
            }else{
                resultModel.addElement("No such ID for a student exists.\nTry another.");
                // Give the user a chance to write a new ID
                input.requestFocus();
                input.selectAll();
            }
        }
    }
    public void addListeners(){
        SearchListener searchListener = new SearchListener();
        // Add this listener to the search button
        search.addActionListener(searchListener);
        // Add this listener also to the textfield
        // so the user may press enter as an alternative
        // to the search button
        input.addActionListener(searchListener);
    }
    public static void main(String[] args){
        StudentSwing app = new StudentSwing();
        app.initApplication();
    }                  
}
