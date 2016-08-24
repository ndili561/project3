package list;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ListItem extends JPanel {

  private JList list;
  private DefaultListModel model;
  private List<Task>editTask;
  private String selectedValue = null;
  private JScrollPane pane;

 

  public ListItem() {
    setLayout(new BorderLayout());
    model = new DefaultListModel();
    list = new JList(model);
    pane = new JScrollPane(list);
    pane.setPreferredSize(new Dimension(300, 300));
    JButton editButton = new JButton("Mark as Completed");
    JButton removeButton = new JButton("Remove Element");
    list.setFont(new Font("Arial",Font.ITALIC,18));
    DefaultListCellRenderer renderer =  (DefaultListCellRenderer)list.getCellRenderer();  
    renderer.setHorizontalAlignment(JLabel.CENTER); 
    list.setForeground(Color.RED);
    editTask = Panel.tasks;
    for (Task task : editTask){
      model.addElement(task.getTask()+", "+task.getDescription()+", "+task.getDate()+", "+task.getCategory()+", "+task.getImportance()+", "+task.isCompleted());
      
    }


    editButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Task task = new Task();
        task.setToCompleted(selectedValue);
        editTask = Panel.tasks;
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1){
          model.remove(selectedIndex); 
        }
      JOptionPane.showMessageDialog(list.getComponent(0), "Task Set As Completed");
      }

    });
    removeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
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