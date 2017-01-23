import java.io.*;
import java.util.*;

public class file{
  public static void main(String[] args) throws Exception { 
    Scanner in = new Scanner(System.in);
    Scanner sc=null;
    int count=0,uwords=0;
    File folder = new File("/home/jsrathore/Dropbox/Semester 6th/IR_Lab/lab_01/one");
    File[] listOfFiles = folder.listFiles();
    HashMap<String,Integer> words_fre = new HashMap<String,Integer>();

    FileWriter fw = new FileWriter(".txt");
    //ArrayList<String> words = new ArrayList<String>();
    
for (File file : listOfFiles) {

    if (file.isFile()) {
        //System.out.println(file.getName());
     try{
      sc=new Scanner(/*new BufferedReader(new File*/(file));
       //sc.useDelimiter("\\W");

      
       while(sc.hasNext()){
        String s = sc.next().toString();
                     s = s.replaceAll("\\<.*?>","");
                     
                     count++; // words count   
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

      Object[] key =   words_fre.keySet().toArray();   
          Arrays.sort(key);  
          for (int i = 0; i < key.length; i++) {  
          //System.out.println(key[i]+"= "+words_fre.get(key[i]));
          fw.write(key[i]+" : "+words_fre.get(key[i]) +"\n"); 
        }
                 
           
       }catch(IOException e)  
          {  
                System.out.println(e);  
           }
                
  }    	
	
  }
           /*System.out.println("Total Words = "+count);  
           System.out.println("Unique Words = "+words_fre.size());*/
          
           fw.write("Total Words = "+count+"\n"); 
           fw.write("Unique Words = "+words_fre.size());          
         fw.close();
 }
} 
