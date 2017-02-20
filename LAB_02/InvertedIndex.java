package search;
/**
 *
 * @author Jitendra Singh
 */
import java.io.*;
import java.util.*;/*Included labriry*/
public class InvertedIndex {

    public static Set<String> stopWordsSet =null; /*Storing stopwords in hash set*/
    public static HashMap<String,HashSet<Integer>>invert_index = new HashMap<>(); /*Inveted index hash map*/
    public static int documentid=1; /*giving Document id*/  

    public static void main(String[] args) throws Exception{      
        File objectFile=new File("InvertedIndexHashMap"); /*binary file*/       
        BufferedWriter outputfile= new BufferedWriter(new
            OutputStreamWriter(new FileOutputStream("invert_50k.txt"), "UTF-8")); 
        /*output text file*/  

        if(!objectFile.exists()){ /*If binary not exist then create and created inverted index object in binary file */           
            File folder = new File("/home/jsroyal/IR_System_LAB/IR_Lab_01/hindi");/*path of folder*/
            File[] ListOfFiles=folder.listFiles();        
            String [] StopWords={"से", "हैं", "को", "पर", "इस", "होता", "कि", "जो", "कर", "मे", "गया", "करने", "किया", "लिये", "अपने", "ने", "बनी", "नहीं", "तो", "ही", "या", "एवं", "दिया", "हो", "इसका", "था", "द्वारा", "हुआ", "तक", "साथ", "करना", "वाले", "बाद", "लिए", "आप", "कुछ", "सकते", "किसी", "ये", "इसके", "सबसे", "इसमें", "थे", "दो", "होने", "वह", "वे", "करते", "बहुत", "कहा", "वर्ग", "कई", "करें", "होती", "अपनी", "उनके", "थी", "यदि", "हुई", "जा", "ना", "इसे", "कहते", "जब", "होते", "कोई", "हुए", "व", "न", "अभी", "जैसे", "सभी", "करता", "उनकी", "तरह", "उस", "आदि", "कुल", "एस", "रहा", "इसकी", "सकता", "रहे", "उनका", "इसी", "रखें", "अपना", "पे", "उसके"};
            stopWordsSet =new HashSet<>(Arrays.asList(StopWords));/*Convert array to has set*/  
            for(File file : ListOfFiles){              
              /*System.out.println(file); */ 
              Inverted_index(file,documentid); /*sending file and Id of document*/
              documentid++;
              System.out.println(documentid);/*track the document of running*/
            }
            
            FileOutputStream hashmap = new FileOutputStream("InvertedIndexHashMap"); 
            ObjectOutputStream obj = new ObjectOutputStream(hashmap);
            obj.writeObject(invert_index);/*writting object of hash map */ 
            obj.close();
            }
        else{ 
            ObjectInputStream fileRead = new ObjectInputStream(new BufferedInputStream(new FileInputStream(objectFile)));
            HashMap<String,HashSet<Integer>>objectHashMap = (HashMap) fileRead.readObject();/*reading object of hash map*/
            fileRead.close();
            //findDocument(objectHashMap,"व्हेल"); // find the words in documents
            
            for (Map.Entry<String, HashSet<Integer>> entry : objectHashMap.entrySet()){
                /*System.out.println(entry.getKey() + " " + entry.getValue());*/
                outputfile.write(entry.getKey()+" : "+ entry.getValue() + "\n");/*writing inverted index in txt file*/
                  
            }

           
        }
    }
  
       

    public static void Inverted_index(File file,int id) throws Exception{   
            
        Stemmer stm=new Stemmer(); /*object of Stemmering class*/        
        BufferedReader bufferedReader=null;
        FileInputStream inputfilename=null;
        inputfilename= new FileInputStream(file); /*taking file input */
        bufferedReader= new BufferedReader(new InputStreamReader(inputfilename, "UTF-8"));
        String s;
        while((s = bufferedReader.readLine()) != null){             
            s = s.replaceAll("\\<.*?>"," ");/*removing tag */
            if(s.contains("॥") || s.contains(":")|| s.contains("।")|| s.contains(",")|| s.contains("!")|| s.contains("?") || s.contains(".")){
                s=s.replace("॥"," "); s=s.replace(":"," "); s=s.replace("।"," ");
                s=s.replace(","," "); s=s.replace("!"," "); s=s.replace("?"," ");s=s.replace("?"," ");s=s.replace("."," ");
            }  /*removing punction marks*/                                                 
            StringTokenizer st = new StringTokenizer(s," ");/*String tokenzer*/
            while (st.hasMoreTokens()){         
                String str=st.nextToken();
                if (!stopWordsSet.contains(str)) {/*Remove stop words*/         
                    int len=stm.stem(str);/*stemming the words*/
                    char [] charArray=new char[len];
                    for (int j=0;j<len ;j++ ) {
                        charArray[j]=str.charAt(j);
                    }
                    String word=new String(charArray);/*after the stemming*/	
                    if(!invert_index.containsKey(word)){  /*check key if not exist*/
                        invert_index.put(word,new HashSet<>());/*putting key and hash set value*/
                        }
                        HashSet<Integer> sat=invert_index.get(word);/*getting the  values set of key*/
                       sat.add(documentid);  /*adding data in set of value if not exist*/                     
                }
            }             
        }                               
    }
    public static void findDocument(HashMap<String,HashSet<Integer>> hash_map,String word){ /*find the doucment which is words contains*/      
            if(hash_map.containsKey(word)){/*check the words contents*/
             System.out.println(hash_map.get(word));/*print the set of values for this key*/
            }
            else{
                System.out.println("Not availble in index");
            }   
    }     
}


class Stemmer {/*Stemmer class for hindi*/
    int stem(String st) {/*stemming the words and send back length*/
// 5   
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
if ((len > 2) && ((st.endsWith("ो")
|| st.endsWith("े")
|| st.endsWith("ू")
||st.endsWith("ु")
|| st.endsWith("ी")
||st.endsWith("ि")
||st.endsWith("ा")
)))
return len - 1;
 return len;
  }
}


    
