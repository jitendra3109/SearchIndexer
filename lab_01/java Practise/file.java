import java.io.*;
import java.util.*;

public class file{
  public static void main(String[] args) throws Exception {
    int awords=0,uwords=0; 
    Scanner in = new Scanner(System.in);
  	FileWriter fw = new FileWriter("abc.txt");
    File folder = new File("/home/jsrathore/Desktop/IR_System_LAB/lab_01/java Practise");
    File[] listOfFiles = folder.listFiles();
    HashMap<String,Integer> words_fre = new HashMap<String,Integer>(); 

  for (File file : listOfFiles) {
    if (file.isFile()) {
        /*System.out.println(file.getName());*/
        BufferedReader inputStream = new BufferedReader(
                                    new FileReader(file));
        String line;

                    while ((line = inputStream.readLine()) != null) {
                      Scanner words = new Scanner(line);
                     while(words.hasNext()) 
                   {
                     String s = words.next().toString();   
                     awords++; // words count   
                     if(words_fre.containsKey(s))  
                          {  
                               int a = words_fre.get(s);  
                               words_fre.put(s,a+1);             
                          }  
                     else {  
                          words_fre.put(s,1);  
                          uwords++; // unique words count   
                        }  
                }  
                    }
                    Object[] key =   words_fre.keySet().toArray();   
                    Arrays.sort(key); 
                    for (int i = 0; i < key.length; i++) {  
          //System.out.println(key[i]+"= "+words_fre.get(key[i]));
          fw.write(key[i]+"= "+words_fre.get(key[i]) +"\n");  
          }
          fw.close();  
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