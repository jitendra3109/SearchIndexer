package evaltionir;

/**
 *
 * @author jsroyal
 */
import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class EvaltionIR extends Porter {

    public static ArrayList<String> allFiles = new ArrayList();
    public static ArrayList<File> list = new ArrayList();
    public static Set<String> set = null;
    public static Porter port = new Porter();
    public static Double id = 1.0;   
    public static HashMap<String, Double> query = new HashMap();
    public static HashMap<Object, Double> queryW = new HashMap();
    public static HashMap<Object, Double> norlizeW = new HashMap();
    public static BufferedReader bufferedReader = null;
    public static FileInputStream inputfilename = null;

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        File hashMapFile = new File("MapEnglish");
        boolean bool = false;
        bool = hashMapFile.exists();
        //stop words in array
        String[] stopWords = {"a", "as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "queryWond", "queryWondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};
        set = new HashSet(Arrays.asList(stopWords));
        //path of the all file 
        File folder = new File("/home/jsroyal/Desktop/en.docs.2011/");
        listFilesForFolder(folder);
        int size = list.size(); //count the file size
        
        StringBuffer stringArr[] = new StringBuffer[list.size()];
        //store in 
        for (int k = 0; k < size; k++) {
            stringArr[k] = new StringBuffer(list.get(k).getName());
        }
        //System.out.print(" outside try\n");
        try {

            if (bool == false) { 
                HashMap<String, HashMap<String, Double>> invertIndex = new HashMap();
                int fileId = 0;               
                for (int k = 0; k < size; k++) {
                    System.out.println(fileId+" " + list.get(k).getName());
                    fileId++;
                    invertedindex(list.get(k), invertIndex);
                }

                System.out.println("Time before object : " + (System.currentTimeMillis() - startTime));
                FileOutputStream fos = new FileOutputStream("MapEnglish");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(invertIndex);
                oos.close();
            } else {
                System.out.println("Please Enter Query");
                FileInputStream objHash = new FileInputStream("MapEnglish");
                ObjectInputStream inputFile = new ObjectInputStream(objHash);
                //@SuppressWarnings("unchecked")
                HashMap<String, HashMap<String, Double>> map = (HashMap<String, HashMap<String, Double>>) inputFile.readObject();
                inputFile.close();
                File fileQuery = new File("/home/jsroyal/NetBeansProjects/EvaltionIR/src/evaltionir/qquery.text");
                int query_no = 126; // start query
                FileReader fileReader = new FileReader(fileQuery);
                BufferedReader bufferReader = new BufferedReader(fileReader);
                PrintWriter out = new PrintWriter(new FileWriter("SampleOutput.txt", true), true); 
                String s;
                while ((s = bufferReader.readLine()) != null) {
                    //s = new Scanner(System.in).nextLine();
                    if (s.contains("॥") || s.contains(":") || s.contains("|")
                            || s.contains(",") || s.contains("!") || s.contains("?")) {
                        s = s.replace("॥", " ");
                        s = s.replace(":", " ");
                        s = s.replace("|", " ");
                        s = s.replace(",", " ");
                        s = s.replace("!", " ");
                        s = s.replace("?", " ");
                    }
                    StringTokenizer st = new StringTokenizer(s, " ");
                    while (st.hasMoreTokens()) {
                        String str = (st.nextToken());
                        if (set.contains(str)) {
                        } else {
                            String ss = port.stripAffixes(str);
                            if (!query.containsKey(ss)) {
                                query.put(ss, 1.0);
                            } else {
                                Double r = query.get(ss);
                                r++;
                                query.replace(ss, r);
                            }

                        }
                    }
                    Double weight;

                    for (Map.Entry qe : query.entrySet()) {
                        //System.out.println(" "+qe.getKey() + " : "+ qe.getValue());
                        if (map.containsKey(qe.getKey())) {
                            weight = (Double) qe.getValue() * ((Double) Math.log(size / map.get(qe.getKey()).size()));
                            queryW.put(qe.getKey(), weight);
                        } else {
                            weight = 0.0;                            
                            queryW.put(qe.getKey(), weight);
                        }
                        //System.out.println(" " + qe.getKey() + " : " + qe.getValue());
                    }
                    Double store = 0.0;
                    for (Map.Entry se : queryW.entrySet()) {
                        //System.out.println(" " + se.getKey() + " : " + se.getValue());
                        store += Math.pow((Double) se.getValue(), 2);
                    }
                    for (Map.Entry se : queryW.entrySet()) {                        
                        Double sss = (Double) se.getValue() / Math.sqrt(store);
                        norlizeW.put(se.getKey(), sss);
                    }
                    for (Map.Entry de : norlizeW.entrySet()) {
                        //System.out.println(" " + de.getKey() + " : " + de.getValue());
                    }
                    ArrayList<Double> cosineScore = new ArrayList();
                    for (int i = 0; i < size; i++) {
                        cosineScore.add(0.0);
                    }
                    int i = 0;
                    for (int k = 0; k < size; k++) {
                        for (Map.Entry de : norlizeW.entrySet()) {
                            if (map.containsKey((String) de.getKey())) {
                                if (map.get((String) de.getKey()).containsKey((String) list.get(k).getName())) {
                                    Double ddd = ((Double) map.get(de.getKey()).get((String) list.get(k).getName()) * (Double) Math.log(size / map.get(de.getKey()).size()));
                                    //  System.out.println("Key : "+de.getKey()+" Docs : "+file.getName()+ ": found ");
                                    //  System.out.println(""+ddd);
                                    Double val = cosineScore.get(k);
                                    val += ((Double) de.getValue() * ddd) / size;
                                    cosineScore.add(k, val);
                                    // System.out.println(k + " cosineScore " + cosineScore.get(k));

                                }

                            }
                        }

                        i++;
                    }                    
                    System.out.println("Document " + i);                
                    // doc  and cosineScore                                 
                    for (i = 0; i < list.size() - 1; i++) {
                        for (int j = 0; j < list.size(); j++) {
                            if (cosineScore.get(j) < cosineScore.get(i)) {                            
                                Collections.swap(cosineScore, i, j);//sorting
                                StringBuffer tes = stringArr[j];
                                stringArr[j] = stringArr[i];
                                stringArr[i] = tes;
                            }
                        }
                    }                    
                    for (i = 0; i < 20; i++) {
                        //System.out.println(query_no + " Q0 " + stringArr[i] + " " +(i+1)+" "+ cosineScore.get(i));                                                                     
                        out.write(query_no + " Q0 " + stringArr[i] + " " +(i+1)+" "+ cosineScore.get(i)+"\n");                         
                    }
                    query_no++;
                }
                out.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("Total time : " + totalTime);
        }
    }

    // list of file getting here
    public static void listFilesForFolder(final File folder) {

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                list.add(fileEntry);
            }
        }
    }

    //inverted index code here  
    public static void invertedindex(File file, HashMap<String, HashMap<String, Double>> invertIndex) throws Exception {
        inputfilename = new FileInputStream(file);
        bufferedReader = new BufferedReader(new InputStreamReader(inputfilename, "UTF-8"));
        String s;
        while ((s = bufferedReader.readLine()) != null) {

            s = s.replaceAll("\\<.*?>", " ");
            if (s.contains("॥") || s.contains(":") || s.contains("।")
                    || s.contains(",") || s.contains("!") || s.contains("?")) {
                s = s.replace("॥", " ");
                s = s.replace(":", " ");
                s = s.replace("।", " ");
                s = s.replace(",", " ");
                s = s.replace("!", " ");
                s = s.replace("?", " ");
            }
            StringTokenizer st = new StringTokenizer(s, " ");
            while (st.hasMoreTokens()) {
                String str = (st.nextToken());
                if (set.contains(str)) {
                } else {
                    String ss = port.stripAffixes(str);
                    if (!invertIndex.containsKey(ss)) {
                        HashMap<String, Double> in = new HashMap<String, Double>();
                        in.put(file.getName(), id);
                        invertIndex.put(ss, in);
                    } else if (invertIndex.get(ss).containsKey(file.getName())) {
                        Double h = (Double) invertIndex.get(ss).get(file.getName());
                        h = h + 1;
                        invertIndex.get(ss).replace(file.getName(), h++);
                    } else {
                        HashMap<String, Double> inin = invertIndex.get(ss);
                        inin.put(file.getName(), id);
                    }
                }
            }
        }
    }
}
