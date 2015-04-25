import java.io.*;
import java.util.*;

/**
 * A Class to return a word to be typed
 * @author Elvan Owen
 */

public class WordsDictionary
{
	private ArrayList<String> words = new ArrayList<>();
        private static WordsDictionary instance;
    
        public static WordsDictionary getInstance()
        {
            if (instance == null)
                instance = new WordsDictionary();
            return instance;
        }
        
	private WordsDictionary()
	{
		getWordsFromExternalFiles();
	}
	
	private void getWordsFromExternalFiles()
	{
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("wordlist.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				words.add(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		Collections.shuffle(words);
		Collections.sort(words,new Comparator<String>() {
            public int compare(String s1, String s2) {
				return s1.length() - s2.length();
            }
        });
	}
	
	public synchronized String getWordsFromDictionary()
	{
		return words.remove(0);
	}
	
	public static void main(String[] args)
	{
		WordsDictionary wd = new WordsDictionary();
		for (int i=0;i<5000;i++)
		{
			System.out.println(wd.getWordsFromDictionary());
		}
	}
}