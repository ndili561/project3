package list;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;
import javax.swing.*;



public class CategorizeTask extends ListItem{


  private JList list;
  private DefaultListModel model;
  private List<Task>edit;
  private String selectedValue = null;
  private JScrollPane pane;

  public CategorizeTask(){
    setLayout(new BorderLayout());
    model = new DefaultListModel();
    list = new JList(model);
    pane = new JScrollPane(list);
    pane.setPreferredSize(new Dimension(300, 300));
    JButton businessButton = new JButton("Business Tasks");
    JButton pleasureButton = new JButton("Pleasure Tasks");
    list.setFont(new Font("Arial",Font.ITALIC,18));
    DefaultListCellRenderer renderer =  (DefaultListCellRenderer)list.getCellRenderer();  
    renderer.setHorizontalAlignment(JLabel.CENTER); 
    list.setForeground(Color.RED);
    edit = Panel.tasks;
    for (Task task : edit){
      model.addElement(task.getTask()+", "+task.getDescription()+", "+task.getDate()+", "+task.getCategory()+", "+task.getImportance()+", "+task.isCompleted());  
    }

    businessButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        List<Task> business = Task.categoriseBusiness();
        model.clear();
        for (Task task : business){
          model.addElement(task.getTask()+", "+task.getDescription()+", "+task.getDate()+", "+task.getCategory()+", "+task.getImportance()+", "+task.isCompleted());  
        }

      }
    });

    pleasureButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        List<Task> pleasure = Task.categorisePleasure();
        model.clear();
        for (Task task : pleasure){
          model.addElement(task.getTask()+", "+task.getDescription()+", "+task.getDate()+", "+task.getCategory()+", "+task.getImportance()+", "+task.isCompleted());  
        }

      }
    }); 

    add(pane, BorderLayout.NORTH);
    add(businessButton, BorderLayout.WEST);
    add(pleasureButton, BorderLayout.EAST); 
  }



}