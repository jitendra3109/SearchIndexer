import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class file1{
  public static void main(String[] args) throws Exception{
    invd idx=new invd();
    HashMap<String,Integer> words_fre = new HashMap<String,Integer>(); 
    Set<String> h= new HashSet<String>(); 
     Steam ob=new Steam();
     try{

      File folder = new File("/home/jsroyal/IR_System_LAB/IR_Lab_01/files");
           File[] listOfFiles = folder.listFiles();

           BufferedReader bufferedReader=null;
           FileInputStream inputfilename=null;
           BufferedWriter out= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("wordCount.txt."), "UTF-8"));
                        
            for(File file : listOfFiles){           
              inputfilename= new FileInputStream(file); 
              /*System.out.println(file); */    
               bufferedReader= new BufferedReader(new InputStreamReader(inputfilename, "UTF-8"));
               
               String s;
                 while((s = bufferedReader.readLine()) != null){
                   /*System.out.println(line);*/
                      s = s.replaceAll("\\<.*?>"," ");
                        if(s.contains("॥") || s.contains(":")|| s.contains("।")|| 
                         s.contains(",")|| s.contains("!")|| s.contains("?")){
                             s=s.replace("॥"," ");
                             s=s.replace(":"," ");
                             s=s.replace("।"," ");
                             s=s.replace(","," ");
                             s=s.replace("!"," ");
                             s=s.replace("?"," ");
                           }                                                   
                      StringTokenizer st = new StringTokenizer(s," ");
                      while (st.hasMoreTokens()) {         
                      /*out.write(st.nextToken()+"\n");*/
                      String str=(st.nextToken()).toString();                 
                     
                      if(words_fre.containsKey(str)){  
                               int a = words_fre.get(str);  
                               words_fre.put(str,a+1);             
                         }else{                           
                              words_fre.put(str,1);
                            /*uwords++;//unique words count */ 
                      }                    
                    
                    }              
                    /*out.write("\n");
                    out.close();*/
                  }               

                  for (String str:stopwords ) {
                       if (words_fre.containsKey(str)) {
                         words_fre.remove(str);
                       }
                     }                   



                 
                
             }

             Object[] key =   words_fre.keySet().toArray();   
                      Arrays.sort(key);  
                      for (int i = 0; i < key.length; i++) {  
                           int len=ob.stem(key[i].toString());
                           char [] arr=new char[len];
                           for (int j=0;j<len ;j++ ) {
                               arr[j]=(key[i].toString()).charAt(j);
                           }
                        String ss=new String(arr);
                        /*out.write(ss+"\n");*/
                         h.add(ss);
                        System.out.println(key[i]+"= "+words_fre.get(key[i]));
                     /*out.write(key[i]+" : "+words_fre.get(key[i]) +"\n");*/
                   }    


                    
          }catch(FileNotFoundException ex){
             System.out.println("Error in reading line");
            }catch(IOException ex){
                /*System.out.println("Error in reading line"+fileReader );*/
                ex.printStackTrace();
               }
            
               
  }


  public static String[] stopwords = {"से", "हैं", "को", "पर", "इस", "होता", "कि", "जो", "कर", "मे", "गया", "करने", "किया", "लिये", "अपने", "ने", "बनी", "नहीं", "तो", "ही", "या", "एवं", "दिया", "हो", "इसका", "था", "द्वारा", "हुआ", "तक", "साथ", "करना", "वाले", "बाद", "लिए", "आप", "कुछ", "सकते", "किसी", "ये", "इसके", "सबसे", "इसमें", "थे", "दो", "होने", "वह", "वे", "करते", "बहुत", "कहा", "वर्ग", "कई", "करें", "होती", "अपनी", "उनके", "थी", "यदि", "हुई", "जा", "ना", "इसे", "कहते", "जब", "होते", "कोई", "हुए", "व", "न", "अभी", "जैसे", "सभी", "करता", "उनकी", "तरह", "उस", "आदि", "कुल", "एस", "रहा", "इसकी", "सकता", "रहे", "उनका", "इसी", "रखें", "अपना", "पे", "उसके"};
}

class Steam {
      int stem(String st) {
// 5
char buffer[]=st.toCharArray();
int len=st.length();      
if ((len > 6) && (st.endsWith("ाएंगी")
|| st.endsWith("ाएंगे")
|| st.endsWith("ाऊंगी")
|| st.endsWith("ाऊंगा")
|| st.endsWith("ाइयाँ")
|| st.endsWith("ाइयों")
|| st.endsWith("ाइयां")
))

return len - 5;
// 4
if ((len > 5) && (st.endsWith("ाएगी")
||st.endsWith("ाएगा")
|| st.endsWith("ाओगी")
|| st.endsWith("ाओगे")
||st.endsWith("एंगी")
|| st.endsWith("ेंगी")
||st.endsWith("एंगे")
||st.endsWith("ेंगे")
||st.endsWith("ूंगी")
||st.endsWith("ूंगा")
||st.endsWith("ातीं")
||st.endsWith("नाओं")
||st.endsWith("नाएं")
||st.endsWith("ताओं")
||st.endsWith("ताएं")
||st.endsWith("ियाँ")
||st.endsWith("ियों")
|| st.endsWith("ियां")
))
return len - 4;
// 3
if ((len > 4) && (st.endsWith("ाकर")
|| st.endsWith("ाइए")
|| st.endsWith("ाईं")
|| st.endsWith("ाया")
|| st.endsWith("ेगी")
||st.endsWith("ोगी")
|| st.endsWith("ोगे")
|| st.endsWith("ाने")
||st.endsWith("ाना")
||st.endsWith("ाते")
||st.endsWith("ाती")
||st.endsWith("ाता")
||st.endsWith("तीं")
||st.endsWith("ाओं")
||st.endsWith("ाएं")
||st.endsWith("ुओं")
||st.endsWith("ुएं")
||st.endsWith("ुआं")
))
return len - 3;
// 2
if ((len > 3) && (st.endsWith("कर")
|| st.endsWith("ाओ")
|| st.endsWith("िए")
|| st.endsWith("ाई")
|| st.endsWith("ाए")
|| st.endsWith("ने")
|| st.endsWith("नी")
|| st.endsWith("ना")
|| st.endsWith("ते")
|| st.endsWith("ीं")
|| st.endsWith("ती")
|| st.endsWith("ता")
|| st.endsWith("ाँ")
|| st.endsWith("ां")
|| st.endsWith("ों")
|| st.endsWith("ें")
))
return len - 2;
// 1
if ((len > 2) && (st.endsWith("ो")
|| st.endsWith("े")
|| st.endsWith("ू")
||st.endsWith("ु")
|| st.endsWith("ी")
||st.endsWith("ि")
||st.endsWith("ा")
))
return len - 1;
return len;
}
}


class invd { 

         Map<String, List<Tuple>> index = new HashMap<String, List<Tuple>>(); 
        List<String> files = new ArrayList<String>();
 
  public void indexFile(File file) throws IOException {

    int fileno = files.indexOf(file.getPath());
    if (fileno == -1) {
      files.add(file.getPath());
      fileno = files.size() - 1;
    } 
 
    int pos = 0;
    BufferedReader reader = new BufferedReader(new FileReader("wordCount.txt"));
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
        for (String word : line.split("\\W+")) {
         /*String word = _word.toLowerCase();*/
        pos++;
        /*if (stopwords.contains(word))
          continue;*/
        List<Tuple> idx = index.get(word);
        if (idx == null) {
          idx = new LinkedList<Tuple>();
          index.put(word, idx);
        }

        idx.add(new Tuple(fileno, pos));
      }
    }
    
    /*System.out.println("indexed " + file.getPath() + " " + pos + " words");*/
    for(Map.Entry<String,List<Tuple>> entry : index.entrySet()){
      System.out.println( " = " + (entry.getValue().toString()));
    }
 }
}

class Tuple {
    private int fileno;
    private int position;
 
    public Tuple(int fileno, int position) {
      this.fileno = fileno;
      this.position = position;
    }
  }



  

 
