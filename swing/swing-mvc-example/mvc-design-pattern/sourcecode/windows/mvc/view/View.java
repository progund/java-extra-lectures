package mvc.view;
import javax.swing.*;
import java.awt.*;

public class View{

    /* Components */
    private JFrame frame;
    private JTextField searchField;
    private JButton searchButton;
    private JButton deleteButton;
    private JList<String> resultList;
    private DefaultListModel<String> result;
    private JPanel north;
    private JPanel south;
    private JLabel label;

    /* Constructor */
    public View(){
        initAndLayout();
    }

    /* Initialize and lay out the components */
    private void initAndLayout(){
        initComponents();
        layoutComponents();
        frame.setSize(500,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private void initComponents(){
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        north = new JPanel();
        south = new JPanel();
        label = new JLabel("Search for a person:");
        searchField = new JTextField(20);
        searchField.setText("Search here");
        searchField.requestFocus();
        searchField.selectAll();
        searchButton = new JButton("Search");
        deleteButton = new JButton("Delete selected");
        result = new DefaultListModel<String>();
        result.addElement("Results:");
        resultList = new JList<String>(result);
        resultList.setVisibleRowCount(20);
    }
    private void layoutComponents(){
        north.add(label);
        north.add(searchField);
        north.add(searchButton);
        south.add(deleteButton);
        frame.add(north, BorderLayout.NORTH);
        frame.add(resultList, BorderLayout.CENTER);
        frame.add(south, BorderLayout.SOUTH);
    }

    /* Accessor methods for the control */

    public JTextField getSearchField(){
        return searchField;
    }
    public JButton getSearchButton(){
        return searchButton;
    }
    public JButton getDeleteButton(){
        return deleteButton;
    }
    public DefaultListModel<String> getResultModel(){
        return result;
    }
    public JList<String> getResultList(){
        return resultList;
    }
}
