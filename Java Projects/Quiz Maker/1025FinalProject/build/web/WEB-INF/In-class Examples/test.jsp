<%@page import="edu.pitt.core.Bird"%>
<!--
    HTML Comment
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test JSP Page</title>
    </head>
    <body>
        <%
          Bird bird = new Bird(2);
          session.setAttribute("bird", bird);
          out.print(bird.getBirdComName());
          
        %>
        <br/><br/>
        <a href="test1.jsp">Redirect me to test1.jsp</a>
    </body>
</html>
