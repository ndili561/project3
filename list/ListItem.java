package list;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.List;
import java.util.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JRadioButton;

public class ListItem extends JPanel {

  JList list;
  DefaultListModel model;
  List<Task>editTask;
  String selectedValue = null;

 

  public ListItem() {
    setLayout(new BorderLayout());
    model = new DefaultListModel();
    list = new JList(model);
    JScrollPane pane = new JScrollPane(list);
    JButton editButton = new JButton("Mark as Completed");
    JButton removeButton = new JButton("Remove Element");
    editTask = Panel.tasks;
    for (Task task : editTask){
      model.addElement(task.getTask()+", "+task.getDescription()+", "+task.getDate()+", "+task.getImportance()+", "+task.isCompleted());
      
    }
  

   


    editButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
       // List<String>result = new ArrayList<String>();
       // result.add(selectedValue);
        Task task = new Task();
        task.setToCompleted(selectedValue);
      }
    });
    removeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // List<String>result = new ArrayList<String>();
        // model = (DefaultListModel) list.getModel();
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1){
          model.remove(selectedIndex); 
          Task.convertObject(selectedValue);
        }
      }   
    });

    ListSelectionListener listSelectionListener = new ListSelectionListener(){
      public void valueChanged(ListSelectionEvent listSelectionEvent) {
        boolean adjust = listSelectionEvent.getValueIsAdjusting();
        if (!adjust) {
          list = (JList) listSelectionEvent.getSource();
          int selections[] = list.getSelectedIndices();
          Object selectionValues[] = list.getSelectedValues();
          for (int i = 0, n = selections.length; i < n; i++) {
            if (i == 0) {
              System.out.println(" Selections: ");
            }
            System.out.println(selectionValues[i]);

            selectedValue = selectionValues[i].toString();   
          }
        }
      }
    };
    list.addListSelectionListener(listSelectionListener);


    add(pane, BorderLayout.NORTH);
    add(editButton, BorderLayout.WEST);
    add(removeButton, BorderLayout.EAST);
  }
}