import java.io.*;  
import java.util.*;  
  public class file{  
    public static void main(String[] args) {  
        Scanner in = new Scanner(System.in); 
        int awords=0,uwords=0;   
        try{ 
            
           File inputfile = new File("hindi-document-00001.txt"); 

           FileWriter fw=new FileWriter("abc.txt"); 
           Scanner words = new Scanner(inputfile); 
           //words.useDelimiter("[^A-Za-z0-9]");  
            
           HashMap<String,Integer> words_fre = new HashMap<String,Integer>();  
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
          Object[] key =   words_fre.keySet().toArray();   
          Arrays.sort(key);  
          for (int i = 0; i < key.length; i++) {  
          //System.out.println(key[i]+"= "+words_fre.get(key[i]));
          fw.write(key[i]+"= "+words_fre.get(key[i]) +"\n");  
          } 
         
          
        fw.close();             
           System.out.println("Total Words = "+awords);  
           System.out.println("Unique Words = "+words_fre.size());  
          }catch(IOException e)  
          {  
                System.out.println(e);  
           }  
      }  
 }  