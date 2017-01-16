import java.io.*;
import java.util.*;

public class file{
  public static void main(String[] args) throws Exception {
  	
    File folder = new File("/home/jsrathore/Dropbox/Semester 6th/IR_Lab/lab_01");
    File[] listOfFiles = folder.listFiles();

for (File file : listOfFiles) {
    if (file.isFile()) {
        System.out.println(file.getName());
    }
}
  	/*BufferedReader in =new BufferedReader(new FileReader("one.txt"));
    String line=null;
    while((line=in.readLine())!=null){
    	System.out.println(line);
    }    
    */
      	
	
  } 
}