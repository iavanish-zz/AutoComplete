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

			for(int i = 0; i < listOfFiles.length; i++) {

				File temp = new File(listOfFiles[i]);
          
				if (temp.isFile()) {
            
					String text = extractTextFromFile(temp.toString());
					String[] tokens = extractTokensFromText(text);
                
					outer: for(int j = 0; j < tokens.length; j++) {

						for(int k = 0; k < tokens[j].length(); k++) {
							char c = tokens[j].charAt(k);
							if((c < 48) || ((c > 57) && (c < 97)) || (c > 122)) {
								continue outer;
							}
						}
						
						if((tokens[j].length() < 1) || (tokens[j].length() > 15)) {
							continue;
						}

						else if(hashedTokens.containsKey(tokens[j])) {
							continue;
						}

						else {
							hashedTokens.put(tokens[j], new Integer(1));
							writableText.append(tokens[j]);
							writableText.append("\n");
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

	public String[] extractTokensFromText(String text) {

		text = text.toLowerCase();
		String[] tokens = text.split("[`~!@#$%^&*\\(\\)-_=+\\[\\]\\{\\}|\\\\;:'\"\n,\\<\\>.?/\\s]+");
		return tokens;

	}

}