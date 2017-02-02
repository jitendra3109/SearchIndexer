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
public class invert{
	
    public static Set<String> set =null;
    public static HashMap<String,Integer> words_frequncy = new HashMap<String,Integer>();
    public static HashMap<String,HashSet<String>>invert_index  = new HashMap<String,HashSet<String>>();
    public static Steam stm=new Steam();

	public static void main(String[] args) throws Exception {
			

    try{
		File folder = new File("/home/jsrathore/IR_System_LAB/IR_Lab_01/files");
        File[] listOfFiles = folder.listFiles();        
       /*BufferedWriter out= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("wordCount.txt"), "UTF-8"));*/
        BufferedWriter outfile= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("invert.txt"), "UTF-8"));
                   
              /*System.out.println(file);*/
              
            
            /*System.out.println(set);*/
            String[] stopwords = { "से", "हैं", "को", "पर", "इस", "होता", "कि", "जो", "कर", "मे", "गया", "करने", "किया", "लिये", "अपने", "ने", "बनी", "नहीं", "तो", "ही", "या", "एवं", "दिया", "हो", "इसका", "था", "द्वारा", "हुआ", "तक", "साथ", "करना", "वाले", "बाद", "लिए", "आप", "कुछ", "सकते", "किसी", "ये", "इसके", "सबसे", "इसमें", "थे", "दो", "होने", "वह", "वे", "करते", "बहुत", "कहा", "वर्ग", "कई", "करें", "होती", "अपनी", "उनके", "थी", "यदि", "हुई", "जा", "ना", "इसे", "कहते", "जब", "होते", "कोई", "हुए", "व", "न", "अभी", "जैसे", "सभी", "करता", "उनकी", "तरह", "उस", "आदि", "कुल", "एस", "रहा", "इसकी", "सकता", "रहे", "उनका", "इसी", "रखें", "अपना", "पे", "उसके"};
            
            set=new HashSet<String>(Arrays.asList(stopwords));     
       
       

	
          BufferedReader bufferedReader=null;
          FileInputStream inputfilename=null;

          for(File file : listOfFiles){ 

              inputfilename= new FileInputStream(file); 
              /*System.out.println(file); */    
               bufferedReader= new BufferedReader(new InputStreamReader(inputfilename, "UTF-8"));
               String s;
		        while((s = bufferedReader.readLine()) != null){		        	
		        	//System.out.println("File name"+file.getName());
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
                while (st.hasMoreTokens()){         
                String str=(st.nextToken()).toString();
                if (set.contains(str)) {
                	continue;
                }
                else{                   
                        int len=stm.stem(str);
                           char [] arr=new char[len];
                           for (int j=0;j<len ;j++ ) {
                               arr[j]=str.charAt(j);
                           }
                        String ss=new String(arr);
                        if(!invert_index.containsKey(ss)){
                        	invert_index.put(ss,new HashSet<String>());
                        }
                        HashSet<String> sat=invert_index.get(ss);
                        sat.add(file.getName());   

                        
                   }

                	    


                    }             
                
                              
                    
            }
        
       
    
    }

    for (Map.Entry m:invert_index.entrySet()) {
       	 /*System.out.println(m.getKey()+" : "+ m.getValue());*/
       	 outfile.write(m.getKey()+" : "+ m.getValue() + "\n");
       }

    }catch(Exception e){
        	System.out.println(e);
        }

   }


}

class Steam {
      static int stem(String st) {
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




