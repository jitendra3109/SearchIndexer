package search_engine;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jsroyal
 */
public class Quary {
    public static void main(String[] args) throws Exception {
        File TfIdf = new File("tfidfHashMap");
        if (!TfIdf.exists()) {
            System.out.println("File not exist");
        } else {
            System.out.println("Enter you Quary ?");
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            ObjectInputStream fileReadTfIdf = new ObjectInputStream(new BufferedInputStream(new FileInputStream(TfIdf)));
            HashMap<String, ArrayList<Nodew>> TF_IDF = (HashMap) fileReadTfIdf.readObject();/*reading object of hash map*/
            fileReadTfIdf.close();
            if (TF_IDF.containsKey(s)) {
//                Iterator<Map.Entry<String, ArrayList<Nodew>>> weight = TF_IDF.entrySet().iterator();
//                while (weight.hasNext()) {
//                    Map.Entry<String, ArrayList<Nodew>> entry = weight.next();                    
                ArrayList<Nodew> Id_idf = TF_IDF.get(s);
                Double max = 0.0;
                int DocId = 0;
                for (int i = 0; i < Id_idf.size(); i++) {
                    if (max < Id_idf.get(i).weightedAverage) {
                        max = Id_idf.get(i).weightedAverage;
                        DocId = Id_idf.get(i).docId;
                    }
                    System.out.println(Id_idf.get(i).docId + ":" + Id_idf.get(i).weightedAverage);
                }
                System.out.println("Maximum Waitage File ID");
                System.out.println(DocId + ":" + max);

            } else {
                System.out.println("Key not exist in File");
            }

        }

    }
}

