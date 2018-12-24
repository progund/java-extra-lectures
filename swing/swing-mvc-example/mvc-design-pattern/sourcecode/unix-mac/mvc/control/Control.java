package mvc.control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import mvc.view.View;
import mvc.model.Model;

/* Control:
 * Responsible for handling user actions on the view
 * and updating the view using the model if needed.
 * Also updating the model if the user wants to change
 * the underlying data.
 */
public class Control {
  
  private View view;
  private Model model;

  public Control(View view, Model model) {
    this.view = view;
    this.model  = model;
  }
    
  public void controlIt() {
    controlFieldAndButton();
    controlDeleteButton();  
  }
  
  private class SearchAction implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      searchForPerson();
      view.getSearchField().setText("Search here");
      view.getSearchField().requestFocus();
      view.getSearchField().selectAll();
    }
  }
  
  private class DeleteAction implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      List<String> values = view.getResultList().getSelectedValuesList();
      view.getResultModel().removeAllElements();
      for (String value : values) {
        model.delete(value);
        view.getResultModel().addElement("Deleted: "+value);
      }
      view.getSearchField().setText("Search here");
      view.getSearchField().requestFocus();
      view.getSearchField().selectAll();
    }
  }
  
  private void controlFieldAndButton() {
    SearchAction sa = new SearchAction();
    view.getSearchField().addActionListener(sa);
    view.getSearchButton().addActionListener(sa);
  }
  
  private void controlDeleteButton() {
    DeleteAction da = new DeleteAction();
    view.getDeleteButton().addActionListener(da);
  }
  
  private void searchForPerson() {
    view.getResultModel().removeAllElements();
    for (String match : model.search(view.getSearchField().getText()) ) {
      view.getResultModel().addElement(match);
    }
  }
}
