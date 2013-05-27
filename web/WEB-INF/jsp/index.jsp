<%--
  Created by IntelliJ IDEA.
  User: Dmitry.Avdeev
  Date: 10/2/12
  Time: 5:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <title>Welcome to the mini projet Web</title>
  </head>
  <body>
      <form class="cform" id="login" method="post" action="checklogin">
          <%
              String login = "Name:";
              String pass = "Password:";
              String message = (String) request.getAttribute("message");

              if (message != null) {
                  login ="<span style=\"color:red\">*</span>"+login;
                  pass ="<span style=\"color:red\">*</span>"+pass;
                  out.print(message);
              }
          %>
      <table>
          <tr>
              <td>
                <%
                    out.print(login);
                %>
              </td>
              <td><input type="text" name="username" size="25"></td>
          </tr>
          <tr>
              <td>
                  <%
                    out.print(pass);
                  %>
              </td>
          <td><input type="password" name="password" size="25"></td>
      </tr>
      </table>
          <input type="submit"
                 value="Submit">
          <input type="reset"
                 value="Reset">
      </form>
  </body>
</html>