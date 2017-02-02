import java.io.*;
import java.util.*;
import java.util.Map;
import java.util.WeakHashMap;

public class file2{
  public static void main(String[] args) throws Exception{
    File file=new File("/home/jsrathore/IR_System_LAB/IR_Lab_01/wordCount.txt.");

    FileInputStream fileInputStream = new FileInputStream(file);
    HindiStemmerLight obj =new HindiStemmerLight();
    BufferedWriter out= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Steaming.txt.",true), "UTF-8"));
    BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
    String s;
    while((s = bufferedReader.readLine()) != null){
      /*System.out.println(obj.stem(s));*/
      ArrayList<String> wordArrayList = new ArrayList<String>();

       wordArrayList.add(obj.stem(s));
    }
    for(String str: wordArrayList) {
       out.write(str);
     }    
  }
}



class HindiStemmerLight{

  /**
   * A cache of words and their stems
   */
  static private Map<String, String> cache = new WeakHashMap<String, String>();

  /**
   * A buffer of the current word being stemmed
   */
  private StringBuilder sb = new StringBuilder();

  /**
   * Default constructor
   */
  public HindiStemmerLight() {
  }

  public String stem(String word) {      
    String result = cache.get(word);

    if (result != null)
      return result;

    // 
    sb.delete(0, sb.length());

    // 
    sb.append(word);

    /* remove the case endings from nouns and adjectives */
    remove_suffix(sb);   
    

    result = sb.toString();
    cache.put(word, result);
     
    return result;
  }

  private void remove_suffix(StringBuilder word) {
    int len = word.length() - 1;

    /* article */
    
    if (len > 4) {
      if (word.substring( len- 2, len+1).equals("िया")) {
        word.delete(len-2 , len + 1);
        return;
      }
      if (word.substring( len- 2, len+1).equals("ियो")) {
        word.delete(len-2 , len + 1);
        return;
      }
      
    } /* end if len >4 */
    if (len > 3) {
      if (word.substring(len-1, len+1).equals("ाए")) {
        word.delete(len - 1, len + 1);
        return;
      }
      if (word.substring(len-1, len+1).equals(" ाओ")) {
        word.delete(len - 1, len + 1);
        return;
      }
      if (word.substring(len-1, len+1).equals(" ुआ")) {
        word.delete(len - 1, len + 1);
        return;
      }
      if (word.substring(len-1, len+1).equals(" ुओ")) {
        word.delete(len - 1, len + 1);
        return;
      }
      if (word.substring( len- 1, len+1).equals("ये")) {
        word.delete(len-1 , len + 1);
        return;
      }
      if (word.substring(len-1, len+1).equals(" ेन")) {
        word.delete(len - 1, len + 1);
        return;
      }
      if (word.substring(len-1, len+1).equals(" ेण")) {
        word.delete(len - 1, len + 1);
        return;
      }
      if (word.substring( len- 1, len+1).equals(" ीय")) {
        word.delete(len-1 , len + 1);
        return;
      }
      if (word.substring(len-1, len+1).equals("टी")) {
        word.delete(len - 1, len + 1);
        return;
      }
      if (word.substring(len-1, len+1).equals("ार")) {
        word.delete(len - 1, len + 1);
        return;
      }
      if (word.substring(len-1, len+1).equals("ाई")) {
        word.delete(len - 1, len + 1);
        return;
      }
      
    } /* end if len > 3 */
    if (len > 2) {
      if (word.substring(len, len+1).equals(" ा")) {
        word.delete(len , len + 1);
        return;
      }
      if (word.substring(len, len+1).equals(" े")) {
        word.delete(len , len + 1);
        return;
      }
      if (word.substring(len, len+1).equals(" ी")) {
        word.delete(len , len + 1);
        return;
      }
      if (word.substring(len, len+1).equals(" ो")) {
        word.delete(len , len + 1);
        return;
      }
      if (word.substring(len, len+1).equals("ि ")) {
        word.delete(len , len + 1);
        return;
      }
      if (word.substring(len, len+1).equals("अ")) {
        word.delete(len , len + 1);
        return;
      }
      
    } /* end if len > 2 */
    return;
  }
}


