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

 
public class invd { 

         Map<String, List<Tuple>> index = new HashMap<String, List<Tuple>>(); 
        List<String> files = new ArrayList<String>();
 
	public void indexFile(File file) throws IOException {	
 
		int pos = 0;
		BufferedReader reader = new BufferedReader(new FileReader(file));
        for (String line = reader.readLine(); line != null; line = reader.eadLine()) {
			for (String _word : line.split("\\W+")) {
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
		System.out.println("indexed " + file.getPath() + " " + pos + " words");
	}
}
	

 
 