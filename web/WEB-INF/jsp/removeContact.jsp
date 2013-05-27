<%@ page import="java.util.List" %>
<%@ page import="com.drmeph.entity.Contact" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <title>Welcome to the mini projet Web</title>
    </head>
    <body>
        <form id="deletecontact" method="post" action="deletecontact">
            Select contact to remove :
            <select name="id">
                <%
                    List<Contact> listContact = (List<Contact>) request.getAttribute("listcontact");
                    if (listContact == null) {
                        response.sendRedirect("initListDel");
                    } else {
                        if (listContact != null && listContact.size() > 0) {
                            for ( Contact contact : listContact ) {
                                out.print("<option value=\""+contact.getContactId()+"\">"
                                        +contact.getFirstName()+" "
                                        +contact.getLastName()+"</option>");
                            }
                        }
                    }
                %>
            </select><br/>

            <input type="submit"value="Submit">
            <input type="reset"value="Reset">
        </form>
    </body>
</html>