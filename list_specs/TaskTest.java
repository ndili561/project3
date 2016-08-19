import static org.junit.Assert.*;
import org.junit.*;
import list.*;


public class TaskTest{

Task task;
@Before
public void before(){
  task = new Task("shop", "food", "23/12/2016");
}

@Test
public void hasTask(){
  assertEquals("shop", task.getTask());
}

}