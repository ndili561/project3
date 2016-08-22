package list;
import java.text.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;


public class Task{
  private String task;
  private String description;
  private String date;
  private String importance;
  private String completed;
  private static List<Task>tasks;


  public Task(String aTask,String aDescription,String aDate, String importance,String isCompleted){
   this.task = aTask;
   this.description = aDescription;
   this.date = aDate;
   this.importance = importance;
   this.completed = isCompleted;
  }

 public Task(){

 }


 public String isCompleted(){
  return this.completed;
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

 public Date getDateToDate(){
  return Task.dateToDate(this.getDate());
 }

 public String getImportance(){
  return this.importance;
 }

 public void setImportance(String importance){
   this.importance = importance;
  }

 public void setCompleted(String completed){
  this.completed = completed;
 }


 public int returnDay(String date){
 int day=0;
 try{
  SimpleDateFormat formatter = new SimpleDateFormat("MMM d, y");
  Date dat = formatter.parse(date);
  Calendar cal = Calendar.getInstance();
  cal.setTime(dat);
  day = cal.get(Calendar.DAY_OF_MONTH);
  }catch (ParseException e) 
  {
  System.out.println(e);
  }
  return day;
  }

  public String returnMonth(String date){
   int month=0;
   try{
    SimpleDateFormat formatter = new SimpleDateFormat("MMM d, y");
    Date dat = formatter.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(dat);
    month = cal.get(Calendar.MONTH);
  }catch (ParseException e) 
  {
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
  // String path = Task.choose();
  File file = new File("/Users/user/Desktop/text.txt");
  FileWriter writer = null;
  try
  {
    writer = new FileWriter(file,true);
    writer.write(task.getTask() + "    " + task.getDescription() + "    " + task.getDate()+"    "+task.getImportance()+"    " +task.isCompleted());
    writer.write(System.getProperty( "line.separator" ));
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


public static List<Task> loadTask(){
 // String path = Task.choose();
 File file = new File("/Users/user/Desktop/text.txt");
 Scanner bufferedScanner = null;
 tasks = new ArrayList<Task>();
 try{
  String task;
  String description;
  String date;
  String importance;
  String completed;
  Scanner lineScanner;
  String currentLine=null;
  bufferedScanner = new Scanner(new BufferedReader(new FileReader(file)));
  while(bufferedScanner.hasNextLine()){
    currentLine = bufferedScanner.nextLine();
    lineScanner = new Scanner(currentLine);
    lineScanner.useDelimiter("    ");
    task = lineScanner.next();
    description = lineScanner.next();
    date = lineScanner.next();
    importance = lineScanner.next();
    completed = lineScanner.next();
    tasks.add(new Task(task,description,date,importance,completed));
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
return tasks;
}

public static Date dateToDate(String adate){
  Date date = null;
  DateFormat format = new SimpleDateFormat("MMMM d, yyyy");
  try{
  date = format.parse(adate);
}catch(Exception e){
System.out.println("Error" + e);
}
return date;
}

public static void convertObject(String aList){
 String[] array = aList.split(",");
 List<Task>task = Task.loadTask();
 // List<Task> copy = new ArrayList<Task>(task);
//avoid the cuncurrent modification exception
 for( Iterator< Task > it = task.iterator(); it.hasNext() ; )
 {
   Task str = it.next();
   if( str.getTask().equals(array[0] ) )
   {
     it.remove();
   }
 }
 Task.reWrite(task);
}




public static void reWrite(List<Task> list){
  PrintWriter writer = null;
  try{
    writer = new PrintWriter("/Users/user/Desktop/text.txt");
    writer.print("");
  }catch(Exception exec){
   System.out.println("Exception"+exec);
 }
 writer.close();
 File file = new File("/Users/user/Desktop/text.txt");
 FileWriter write = null;
 try{
  write = new FileWriter(file,true);
  for (Task task : list){
    write.write(task.getTask() + "    " + task.getDescription() + "    " + task.getDate()+"    "+task.getImportance()+"    " +task.isCompleted());
    write.write(System.getProperty( "line.separator" ));
  }
}catch(Exception exect){
  System.out.println("Error"+ exect);
}
finally 
{
 try{
   write.close();
 }
 catch (Exception except){
   System.out.println("Error "+ except);
 }
}

} 



  public String createAFile(){
    String file = null;
   JFileChooser chooser = new JFileChooser();
   chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
   chooser.setAcceptAllFileFilterUsed(false);
  if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      file = chooser.getSelectedFile().getAbsolutePath();
  }
  return file;
  }

  public void setToCompleted(String aList){
  String[] array = aList.split(",");
  String lastToken = array[array.length-1];
  lastToken="Completed";
  array[array.length-1]=lastToken;
  List<Task>task = Task.loadTask();
  for( Iterator< Task > it = task.iterator(); it.hasNext() ; )
  {
    Task str = it.next();
    if( str.getTask().equals(array[0] ) )
    {
      it.remove();
    }
  }
  Task result = new Task(array[0],array[1],array[2]+array[3],array[4],lastToken);
  System.out.println(result.isCompleted());
  task.add(result);
  Task.reWrite(task);
  }
}  





