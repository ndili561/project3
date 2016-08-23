package list;
import com.github.lgooddatepicker.components.*;
import com.github.lgooddatepicker.optionalusertools.*;
import java.time.*;
import com.github.lgooddatepicker.zinternaltools.HighlightInformation;
import com.github.lgooddatepicker.tableeditors.*;
import com.github.lgooddatepicker.ysandbox.*;
import com.github.lgooddatepicker.zinternaltools.*;
import java.awt.Color;
import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import java.util.*;
import java.util.Iterator;
import java.util.Enumeration;



public class Highlight {

  public HighlightInformation getHighlightInformationOrNull(LocalDate date){

    if (date.getDayOfMonth() == 30) {
      return new HighlightInformation(Color.red, null, "");
    }
    return null;
  }
   

}
   
  






  
