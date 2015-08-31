<%@page import="edu.pitt.core.Bird"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
          Bird bird = (Bird)session.getAttribute("bird");
          out.print(bird.getBirdComName());
          
        %>
    </body>
</html>
