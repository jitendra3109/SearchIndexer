import java.io.*;
import java.util.*;

public class file1{
  public static void main(String[] args) throws Exception{
   HashMap<String,Integer> words_fre = new HashMap<String,Integer>();
    HashSet<String> words = new HashSet<String>();

    try{  
     
           File folder = new File("/home/jsrathore/Dropbox/Semester 6th/IR_Lab/IR_Lab_01/files");
           File[] listOfFiles = folder.listFiles();
           
           BufferedReader bufferedReader=null;
           FileInputStream inputfilename=null;
           BufferedWriter out= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("wordCount.txt.",false), "UTF-8"));
                        
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
                      words.add(str);
                    }
                    for(String str : words){
                      if(words_fre.containsKey(str)){  
                               int a = words_fre.get(str);  
                               words_fre.put(str,a+1);             
                      }else{  
                          words_fre.put(str,1);/*uwords++;//unique words count */  
                      }                      
                    }
                    words.clear(); 

                      /*out.write("\n");
                      out.close();*/
                      
                 }
                 for(String str : stopwords) /*remove stopwords*/
                    words_fre.remove(str);   


                 Object[] key =   words_fre.keySet().toArray();   
                      Arrays.sort(key);  
                      for (int i = 0; i < key.length; i++) {  
                        //System.out.println(key[i]+"= "+words_fre.get(key[i]));
                     out.write(key[i]+" : "+words_fre.get(key[i]) +"\n");
                   }

                
             }

                out.close();
                bufferedReader.close();
                    
          }catch(FileNotFoundException ex){
             System.out.println("Error in reading line");
            }catch(IOException ex){
                /*System.out.println("Error in reading line"+fileReader );*/
                ex.printStackTrace();
               }         
  }
  public static String[] stopwords = {"के","एक","में","की","है",
"का",
"यह",
"और",
"से",
"हैं",
"को",
"पर",
"इस",
"होता",
"कि",
"जो",
"कर",
"मे",
"गया",
"करने",
"किया",
"लिये",
"अपने",
"ने",
"बनी",
"नहीं",
"तो",
"ही",
"या",
"एवं",
"दिया",
"हो",
"इसका",
"था",
"द्वारा",
"हुआ",
"तक",
"साथ",
"करना",
"वाले",
"बाद",
"लिए",
"आप",
"कुछ",
"सकते",
"किसी",
"ये",
"इसके",
"सबसे",
"इसमें",
"थे",
"दो",
"होने",
"वह",
"वे",
"करते",
"बहुत",
"कहा",
"वर्ग",
"कई",
"करें",
"होती",
"अपनी",
"उनके",
"थी",
"यदि",
"हुई",
"जा",
"ना",
"इसे",
"कहते",
"जब",
"होते",
"कोई",
"हुए",
"व",
"न",
"अभी",
"जैसे",
"सभी",
"करता",
"उनकी",
"तरह",
"उस",
"आदि",
"कुल",
"एस",
"रहा",
"इसकी",
"सकता",
"रहे",
"उनका",
"इसी",
"रखें",
"अपना",
"पे",
"उसके"};
}