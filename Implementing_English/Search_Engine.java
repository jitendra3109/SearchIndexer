package search_engine;

import java.io.*;
import java.util.*;

/**
 *
 * @author jsroyal
 */
public class Search_Engine {
    public static void main(String[] args) throws Exception {
        File TfIdf = new File("tfidfHashMap");
        if (!TfIdf.exists()) {
            File Objectbinaryfile = new File("InvertedIndexHashMap");
            int filesize = 0;
            if (!Objectbinaryfile.exists()) {
                HashMap<String, ArrayList<Node>> invert_index = new HashMap<>();
                File folder = new File("/home/jsroyal/Desktop/Corpus/hindi/");
                File[] ListOfFiles = folder.listFiles();
                int documentid = 1;
                for (File file : ListOfFiles) {
                    //System.out.println(file);
                    filesize++;
                    Inverted_index(file, documentid, invert_index);
                    /*sending file and Id of document,hashmap*/
                    System.out.println(documentid);/*track the document Passing*/
                    documentid++;
                }
                FileOutputStream hashmap = new FileOutputStream("InvertedIndexHashMap");
                ObjectOutputStream obj = new ObjectOutputStream(hashmap);
                obj.writeObject(invert_index);/*writting object of hash map */
                obj.close();
            } else {
                /*For posting list*/
                FileInputStream hashmap = new FileInputStream("InvertedIndexHashMap");
                ObjectInputStream obj = new ObjectInputStream(hashmap);
                HashMap<String, ArrayList<Node>> invert_index = (HashMap) obj.readObject();
                obj.close();
//                System.out.println("Inverted Index ");
//                System.out.println("Key    :    frequency ");
//                Iterator<Map.Entry<String, ArrayList<Node>>> TF = invert_index.entrySet().iterator();
//                while (TF.hasNext()) {
//                    Map.Entry<String, ArrayList<Node>> entry = TF.next();
//                    System.out.print(entry.getKey() + " : { ");
//                    ArrayList<Node> Id_frequency = entry.getValue();
//                    for (int i = 0; i < Id_frequency.size(); i++) {
//                        System.out.print(Id_frequency.get(i).docId + ":" + Id_frequency.get(i).termFrequncy);
//                        if ((i + 1) < Id_frequency.size()) {
//                            System.out.print(" , ");
//                        }
//                    }
//                    System.out.print(" }\n");
//
//                }

                HashMap<String, ArrayList<Nodew>> weighted_average = new HashMap<>();
                Iterator<Map.Entry<String, ArrayList<Node>>> TFIDF = invert_index.entrySet().iterator();
                while (TFIDF.hasNext()) {
                    Map.Entry<String, ArrayList<Node>> entry = TFIDF.next();
                    ArrayList<Node> Id_frequency = entry.getValue();
                    ArrayList<Nodew> Id_idf = new ArrayList();
                    for (int i = 0; i < Id_frequency.size(); i++) {
                        Id_idf.add(new Nodew(Id_frequency.get(i).docId, Id_frequency.get(i).termFrequncy * Math.log(filesize / Id_frequency.size())));
                    }
                    weighted_average.put(entry.getKey(), Id_idf);
                }

                FileOutputStream tfidf = new FileOutputStream("tfidfHashMap");
                ObjectOutputStream tf_idf = new ObjectOutputStream(tfidf);
                tf_idf.writeObject(weighted_average);/*writting object of hash map */
                tf_idf.close();
            }

        } else {

            ObjectInputStream fileReadTfIdf = new ObjectInputStream(new BufferedInputStream(new FileInputStream(TfIdf)));
            HashMap<String, ArrayList<Nodew>> TF_IDF = (HashMap) fileReadTfIdf.readObject();/*reading object of hash map*/
            fileReadTfIdf.close();
            HashMap<String, ArrayList<Nodew>> NormalizationMap = new HashMap<>();
            System.out.println("  TF IDF ");
            System.out.println("Key    : Document id :    WTF");
            Iterator<Map.Entry<String, ArrayList<Nodew>>> weight = TF_IDF.entrySet().iterator();
//            while (weight.hasNext()) {
//                Map.Entry<String, ArrayList<Nodew>> entry = weight.next();
//                ArrayList<Nodew> Id_idf = entry.getValue();
//                ArrayList<Nodew> arraylistNormalization = new ArrayList();
//
//                Double totalwaigth = 0.0;
//                for (int i = 0; i < Id_idf.size(); i++) {
//                    Double w = Id_idf.get(i).weightedAverage;
//                    totalwaigth = totalwaigth + w;
//                }
//                double totalw = Math.sqrt(totalwaigth);
//
//                for (int i = 0; i < Id_idf.size(); i++) {
//                    //System.out.print(Id_idf.get(i).docId + ":" + String.format("%.3f", Id_idf.get(i).weightedAverage));
//                    Double w = Id_idf.get(i).weightedAverage;
//                    arraylistNormalization.add(new Nodew(Id_idf.get(i).docId, w / totalw));
//                }
//                NormalizationMap.put(entry.getKey(), arraylistNormalization);
//
//            }
//            FileOutputStream tfidf = new FileOutputStream("NormalizationObjectHashMap");
//            ObjectOutputStream tf_idf = new ObjectOutputStream(tfidf);
//            tf_idf.writeObject(NormalizationMap);/*writting object of hash map */
//            tf_idf.close();

//            Iterator<Map.Entry<String, ArrayList<Nodew>>> weight = TF_IDF.entrySet().iterator();
            while (weight.hasNext()) {
                Map.Entry<String, ArrayList<Nodew>> entry = weight.next();
                System.out.print(entry.getKey() + " : { ");
                ArrayList<Nodew> Id_idf = entry.getValue();
                for (int i = 0; i < Id_idf.size(); i++) {
                    System.out.print(Id_idf.get(i).docId + ":" + String.format("%.3f", Id_idf.get(i).weightedAverage));
                    if ((i + 1) < Id_idf.size()) {
                        System.out.print(" , ");
                    }
                }
                System.out.print(" }\n");
            }
        }

//        Writer outputtxt = new BufferedWriter(new OutputStreamWriter(
//              new FileOutputStream("file.txt"), "utf-8")); 
//  
//        ObjectInputStream fileReadTfIdf = new ObjectInputStream(new BufferedInputStream(new FileInputStream(NormalizationObjectFile)));
//        HashMap<String, ArrayList<Nodew>> NormalizationMap = (HashMap) fileReadTfIdf.readObject();/*reading object of hash map*/
//        fileReadTfIdf.close();
//        System.out.println(" Normalization  ");
//        System.out.println("Key    : Document id :    Normalization Waigth ");
//        Iterator<Map.Entry<String, ArrayList<Nodew>>> iterator = NormalizationMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, ArrayList<Nodew>> entry = iterator.next();
//            System.out.print(entry.getKey() + " : { ");
//            ArrayList<Nodew> arraylistnormalzation = entry.getValue();
//            for (int i = 0; i < arraylistnormalzation.size(); i++) {
//                System.out.print(arraylistnormalzation.get(i).docId + ":" + String.format("%.3f", arraylistnormalzation.get(i).weightedAverage));
//                outputtxt.write(arraylistnormalzation.get(i).docId + ":" + String.format("%.3f", arraylistnormalzation.get(i).weightedAverage));
//                
//                if ((i + 1) < arraylistnormalzation.size()) {
//                        System.out.print(" , ");
//                   }
//            }
//            System.out.print(" }\n");
//
//        }
    }

    static void Inverted_index(File file, int documentid, HashMap<String, ArrayList<Node>> invert_index) throws Exception {
        /*object of stop words class*/
        StopWords stopWords = new StopWords();
        /*object of Stemming class*/        
        Stemmer stem = new Stemmer();
        BufferedReader bufferedReader = null;
        FileInputStream inputfilename = null;
        inputfilename = new FileInputStream(file);
        /*taking file input */
        bufferedReader = new BufferedReader(new InputStreamReader(inputfilename, "UTF-8"));
        String s;

        while ((s = bufferedReader.readLine()) != null) {
            s = s.replaceAll("\\<.*?>", " ");/*removing tag */
            if (s.contains("॥") || s.contains(":") || s.contains("।") || s.contains(",") || s.contains("!") || s.contains("?") || s.contains(".")) {
                s = s.replace("॥", " ");
                s = s.replace(":", " ");
                s = s.replace("।", " ");
                s = s.replace(",", " ");
                s = s.replace("!", " ");
                s = s.replace("?", " ");
                s = s.replace("?", " ");
                s = s.replace(".", " ");
            }
            /*removing punction marks*/
            StringTokenizer st = new StringTokenizer(s, " ");/*String tokenzer*/


            while (st.hasMoreTokens()) {
                String str = st.nextToken();
                if (!stopWords.stopWordsSetEnglish.contains(str)) {/*Remove stop words*/

                    String word = stem.stripAffixes(str);/*stemming the words*/
                     /*after the stemming*/
                    ArrayList<Node> arrayList = invert_index.get(word);
                    if (!invert_index.containsKey(word)) {
                        arrayList = new ArrayList<>();
                        /*putting key and hash set value*/
                        arrayList.add(new Node(documentid, 1));
                        invert_index.put(word, arrayList);

                    } else {
                        int flag = 0;

                        for (int i = 0; i < invert_index.get(word).size(); i++) {
                            if (invert_index.get(word).get(i).docId == documentid) {
                                invert_index.get(word).get(i).termFrequncy = invert_index.get(word).get(i).termFrequncy + 1;
                                flag = 1;
                            }
                        }

                        if (flag == 0) {
                            invert_index.get(word).add(new Node(documentid, 1));
                        }

                    }

                }
            }
        }
    }

}
//inverted index

class Node implements Serializable {

    int docId;
    int termFrequncy;

    Node(int id, int frequncy) {
        this.docId = id;
        this.termFrequncy = frequncy;

    }
}
//tf idf

class Nodew implements Serializable {

    int docId;
    double weightedAverage;

    Nodew(int id, double weightedAverage) {
        this.docId = id;
        this.weightedAverage = weightedAverage;
    }
}
