package Main;

import java.io.*;
import java.util.*;

/**
 * A Class to return a word to be typed
 * @author Elvan Owen
 */

public class WordsDictionary
{
	private static ArrayList<String> words = new ArrayList<>();
        private static WordsDictionary instance;
        
        public static synchronized WordsDictionary getInstance()
        {
            if (instance == null)
            {
                instance = new WordsDictionary();
                getWordsFromExternalFiles();
            }
            return instance;
        }
        
	private WordsDictionary()
	{
		getWordsFromExternalFiles();
	}
	
	private synchronized static void getWordsFromExternalFiles()
	{
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("src/Main/wordlist.txt"));

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
	}
	
	public static synchronized String getWordsFromDictionary()
	{
		return words.remove(0);
	}
	
	public static void main(String[] args)
	{
		for (int i=0;i<5000;i++)
		{
			System.out.println(WordsDictionary.getInstance().getWordsFromDictionary());
		}
	}
}