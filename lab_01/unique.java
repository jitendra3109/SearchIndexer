import java.util.*;
import java.io.*;
public class unique
{
   public static void main(String[] args) throws Exception
   {
      Scanner fileScanner = new Scanner(new File("one.txt"));
       fileScanner.useDelimiter("[^A-Za-z0-9]");
       ArrayList<String> words = new ArrayList<String>();
      while (fileScanner.hasNext())
    {
        String nextWord = fileScanner.next();
        if (!words.contains(nextWord))
        {
            words.add(nextWord);
        }
    }
      Collections.sort(words);
      System.out.println("There are " +  words.size()+" unique word(s)");
      System.out.println("These words are:");
      for (Iterator<String> it = words.iterator(); it.hasNext();) 
      { String f = it.next();
        System.out.println(f);
      }
      fileScanner.close();
   }
}