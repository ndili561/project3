package list;

import java.util.*;
import java.io.*;

public class Task{
 // private List<Entrant>entrants;
 // private Map<String,Double>firstCategory;
 // private Map<String,Double>secondCategory;
 // private Map<String,Double>thirdCategory;
  private String task;
  private String description;
  private String date;
  private boolean importance;
  private static int nextNumber=1;


 public Task(String aTask,String aDescription,String aDate){
   this.task = aTask;
   this.description = aDescription;
   this.date = aDate;
   Task.nextNumber+=1;
 }

 public String getTask(){
  return this.task;
 }

 public String getDescription(){
  return this.description;
 }

 public String date(){
  return this.date;
 }

 public boolean getImportance(){
  return this.importance;
 }

 // public void readInEntrants(String entrants){
 //  String pathname = entrants;
 //  File file = new File(pathname);
 //  BufferedReader bufferedReader=null;
 //  try{

 //  }catch(Exception e){
 //   System.out.println("Error " + e);
 //  }
 // }

}
