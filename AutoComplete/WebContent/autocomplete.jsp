<%@page import="java.util.*"%>
<%
    // Create ArrayList and add some items
    ArrayList<String> as=new ArrayList<String>();
    as.add("Google");
    as.add("Yahoo");
    as.add("Apple");
    as.add("Microsoft");
    as.add("Linkedin");
    as.add("Facebook");
    as.add("IBM");
    as.add("Oracle");
    as.add("Salesforce");
    as.add("Amazon");
    
        String s=request.getParameter("com");
        
            for(String st:as)
            {
                if(st.toLowerCase().startsWith(s.toLowerCase()))
                {
                    out.println(st);
                }
            }
%>