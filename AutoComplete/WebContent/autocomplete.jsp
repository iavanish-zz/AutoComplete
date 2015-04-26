<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%
    
	String[] tokens;

   	File file;
   	Scanner scan;
   	String text = "";
    
    try {
  		file = new File("D:/Codes/EclipseWorkspace/AutoComplete/AutoComplete/tokensFile.out");
    	System.out.println("File Created");
		scan = new Scanner(file);
		scan.useDelimiter("\\Z");
		text = scan.next();
		scan.close();
    }
    catch(FileNotFoundException exception) {
    	System.out.println("Exception in JSP");
    }
    
	tokens = text.split("\n");
    		
    String query = request.getParameter("com");
        
    for(int i = 0; i < tokens.length; i++) {
        if(tokens[i].startsWith(query.toLowerCase())) {
            out.println(tokens[i]);
        }
    }
    
%>