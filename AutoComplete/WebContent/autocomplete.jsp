
<%@page import = "java.util.*"%>
<%@page import = "java.io.*"%>
<%@page import = "iavanish.ir.*"%>

<%!
	RadixTree radixTree;
%>

<%!

public void jspInit() {

	radixTree = new RadixTree();
	radixTree.initialize();
	
}

%>

<%  	
    		
    String query = request.getParameter("com");
     
    ArrayList <String> suggestions = radixTree.search(query);
    
    if(suggestions != null) {
    
    	Iterator <String> iterator = suggestions.iterator();
    
    	while(iterator.hasNext()) {
    		String temp = iterator.next();
    		out.println(temp);
    	}
    }
    
%>