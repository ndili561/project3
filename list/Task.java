package list;
import java.text.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;


public class Task{
 // private List<Entrant>entrants;
 // private Map<String,Double>firstCategory;
 // private Map<String,Double>secondCategory;
 // private Map<String,Double>thirdCategory;
  private String task;
  private String description;
  private String date;
  private String importance;
  private boolean completed;
  private static int nextNumber=1;
  private static List<Task>tasks;


  public Task(String aTask,String aDescription,String aDate, String importance){
   this.task = aTask;
   this.description = aDescription;
   this.date = aDate;
   this.importance = importance;
   this.completed = false;
   Task.nextNumber+=1;
 }

 public Task(){

 }

 public boolean isCompleted(){
  return this.completed;
}

public static int getTaskNumber(){
  return Task.nextNumber;
}

public String getTask(){
  return this.task;
}

public String getDescription(){
  return this.description;
}

public String getDate(){
  return this.date;
}

public String getImportance(){
  return this.importance;
}

public int returnDay(String date){
 int day=0;
 try{
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  Date dat = formatter.parse(date);
  Calendar cal = Calendar.getInstance();
  cal.setTime(dat);
  day = cal.get(Calendar.DAY_OF_MONTH);
}catch (ParseException e) {
  System.out.println(e);
}
return day;
}

public String returnMonth(String date){
 int month=0;
 try{
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  Date dat = formatter.parse(date);
  Calendar cal = Calendar.getInstance();
  cal.setTime(dat);
  month = cal.get(Calendar.MONTH);
}catch (ParseException e) {
  System.out.println(e);
}
return Task.getMonthForInt(month);
}


private static String getMonthForInt(int num) {
  String month = "wrong";
  DateFormatSymbols dfs = new DateFormatSymbols();
  String[] months = dfs.getMonths();
  if (num >= 0 && num <= 11 ) {
    month = months[num];
  }
  return month;
}

public static void writeToFile(Task task){
  String path = Task.choose();
  File file = new File(path);
  FileWriter writer = null;
  try
  {
    writer = new FileWriter(file,true);
    writer.append(System.getProperty("line.separator"));
    writer.write(Task.getTaskNumber()+"    "+task.getTask() + "    " + task.getDescription() + "    " + task.getDate() +"    " +task.isCompleted());
  }
  catch(Exception exception)
  {
    System.out.println("Error" + exception);
  }
  finally 
  {
    try{
      writer.close();
    }
    catch (Exception exception){
      System.out.println("Error "+ exception);
    }
  }



}

private static String choose(){
 String file = null;
 JFileChooser chooser = new JFileChooser();
 Component y = new Checkbox();
 int returnVal = chooser.showOpenDialog(y);
 if(returnVal == JFileChooser.APPROVE_OPTION) {
  file = chooser.getSelectedFile().getAbsolutePath();
}
return file;
}






public static Collection<Task> loadTask(){
 String path = Task.choose();
 File file = new File(path);
 Scanner bufferedScanner = null;
 tasks = new ArrayList<Task>();
 try{
  int count= 0;
  String task;
  String description;
  String date;
  String importance;
  boolean completed;
  Scanner lineScanner;
  String currentLine=null;
  bufferedScanner = new Scanner(new BufferedReader(new FileReader(file)));
  while(bufferedScanner.hasNextLine()){
    currentLine = bufferedScanner.nextLine();
    lineScanner = new Scanner(currentLine);
    task = lineScanner.next();
    description = lineScanner.next();
    date = lineScanner.next();
    importance = lineScanner.next();
    tasks.add(new Task(task,description,date,importance));
  }
}
catch(Exception e){
 System.out.println("Error "+ e);
}
finally{
  try{
    bufferedScanner.close();
  }catch(Exception e){
   System.out.println("Error" +e);
 }
}
System.out.println(tasks);
return tasks;
}




}
