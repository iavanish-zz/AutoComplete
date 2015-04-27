package iavanish.ir;

import java.io.*;
import java.util.*;


public class RadixTree {
	
	public RadixTreeNode root;
	
	public RadixTree() {
		
		root = this.initializeNewNode(root, "");
		
	}
	
	public void initialize() {
		
		String[] tokens = getTokens();
		int noOfTokens = tokens.length;
		
		for(int i = 0; i < noOfTokens; i++) {
			
			RadixTreeNode temp = this.root;
			
			Stack <RadixTreeNode> stack = new Stack <RadixTreeNode> ();
			
			for(int j = 0; j < tokens[i].length(); j++) {
				
				char currentCharacter = tokens[i].charAt(j);
				int currentIndex = charToInt(currentCharacter);
				
				if(temp.children[currentIndex] == null) {
					temp.children[currentIndex] = initializeNewNode(temp.children[currentIndex], tokens[i].substring(0,j+1));
				}
				
				temp = temp.children[currentIndex];
				
				stack.push(temp);
				
			}
			
			while(!stack.empty()) {
				
				temp = stack.pop();
				if(temp != null && temp != this.root) {
					temp.words.add(tokens[i]);
				}
				
			}
			
		}
		
		System.out.println("Radix Tree Created");
		
	}

	public ArrayList <String> search(String w) {
		
		RadixTreeNode temp = this.root;
		
		for(int i = 0; i < w.length(); i++) {
			if(temp == null) {
				break;
			}
			temp = temp.children[charToInt(w.charAt(i))];
		}
		
		if(temp == null) {
			return null;
		}
		else {
			return temp.words;
		}
		
	}
	
	private RadixTreeNode initializeNewNode(RadixTreeNode newNode, String data) {
		
		newNode = new RadixTreeNode();
		newNode.data = data;
		newNode.words = new ArrayList <String> ();
		newNode.children = new RadixTreeNode[36];
		
		for(int i = 0; i < 36; i++) {
			newNode.children[i] = null;
		}

		return newNode;
		
	}
	
	private int charToInt(char c) {
		
		int cToI = (int)c;
		cToI -= 48;
		if(cToI > 9) {
			cToI -= 39;
		}
		return cToI;
		
	}
	
	/*
	private char intToChar(int c) {
		
		c += 48;
		if(c > 57) {
			c += 39;
		}
		return (char)c;
		
	}
	*/
	
	private String[] getTokens() {
		
		File file;
	   	Scanner scan;
	   	String text = "";
	   	String[] tokens;
	    
	    try {
	  		file = new File("D:/Codes/EclipseWorkspace/AutoComplete/AutoComplete/tokensFile.out");
	    	System.out.println("File Created");
			scan = new Scanner(file);
			scan.useDelimiter("\\Z");
			text = scan.next();
			scan.close();
	    }
	    catch(Exception exception) {
	    	System.out.println("Exception in JSP");
	    }
	    
		tokens = text.split("\n");
		return tokens;
		
	}
	
}