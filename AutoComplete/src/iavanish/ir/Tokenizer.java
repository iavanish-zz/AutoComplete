package iavanish.ir;

import java.io.*;
import java.util.*;

public class Tokenizer {
	
	public Tokenizer() {

	}

	public static void main(String[] args) {

		Tokenizer obj = new Tokenizer();
		obj.tokenize("fileNames.out");

	}

	public void tokenize(String fileNames) {

		try {
			
			File tokensFile = new File("tokensFile.out");
			tokensFile.createNewFile();
			PrintWriter out = new PrintWriter(tokensFile);

			String[] listOfFiles = extractTextFromFile(fileNames).split("\n"); 

			StringBuilder writableText = new StringBuilder();
	          
			HashMap <String, Integer> hashedTokens = new HashMap <String, Integer> ();
			
			int noOfTokens = 0;

			for(int i = 0; i < listOfFiles.length; i++) {

				File temp = new File(listOfFiles[i]);
          
				if (temp.isFile()) {
            
					String text = extractTextFromFile(temp.toString());
					String[] tokens = extractTokensFromText(text);
                
					outer: for(int j = 0; j < tokens.length; j++) {

						if((tokens[j].length() < 1) || (tokens[j].length() > 14)) {
							continue;
						}

						else if(hashedTokens.containsKey(tokens[j])) {
							continue;
						}

						char repeatedChar = '*';
						int repeatedTimes = 0;
						for(int k = 0; k < tokens[j].length(); k++) {
							char c = tokens[j].charAt(k);
							if((c < 97) || (c > 122)) {
								continue outer;
							}
							if(c == repeatedChar) {
								repeatedTimes++;
							}
							else {
								repeatedChar = c;
								repeatedTimes = 1;
							}
							if(repeatedTimes > 2) {
								continue outer;
							}
						}
						
						hashedTokens.put(tokens[j], new Integer(1));
						writableText.append(tokens[j]);
						writableText.append("\n");
						noOfTokens++;
						
						if(noOfTokens > MAX_TOKENS) {
							break;
						}

					}
                
				}
			}       

			out.print(writableText);

			out.flush();
			out.close();

		}
		
		catch(Exception exception) {
			System.out.println("Exception in tokenize");
		}
		
	}

	public String extractTextFromFile(String fileName) {

		try {
			File file = new File(fileName);
			Scanner scan = new Scanner(file);
			scan.useDelimiter("\\Z");
			String text = scan.next();
			scan.close();
			return text;
		}
		catch(Exception exception) {
			System.out.println("Exception in extractTextFromFile");
			return null;
		}

	}

	private static final int MAX_TOKENS = 800000;
	
	public String[] extractTokensFromText(String text) {

		text = text.toLowerCase();
		String[] tokens = text.split("[0123456789`~!@#$%^&*\\(\\)-_=+\\[\\]\\{\\}|\\\\;:'\"\n,\\<\\>.?/\\s]+");
		return tokens;

	}

}