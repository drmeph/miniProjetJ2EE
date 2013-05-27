<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.drmeph.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <title>Welcome to the mini projet Web</title>
</head>
<body>
<form id="searchcontact" method="post" action="findcontact">
    Search : <br>
    Key : <input type="text" name="name" size="50"><br/>
    <input type="submit"
           value="Submit">
    <input type="reset"
           value="Reset">
</form>
<hr>
<%
    List<Contact> contactList = (List<Contact>) request.getAttribute("listcontact");
    Boolean display = false;

    if (contactList != null && contactList.size() > 0) {
        display = true;
    }
%>
<table class="contactBook" <% if (!display) out.print("hidden=\"true\""); %>>
    <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Email</th>
        <th>Address</th>
        <th>Home Phone</th>
        <th>Work Phone</th>
        <th>Cell Phone</th>
        <th>Groups</th>
    </tr>
    <%

        if (contactList != null && contactList.size() > 0) {

            for ( Contact contact : contactList ) {
                out.print("<tr>");
                out.print("<td>"+contact.getFirstName()+"</td>");
                out.print("<td>"+contact.getLastName()+"</td>");
                out.print("<td>"+contact.getEmail()+"</td>");
                Address adr = contact.getAdd();
                if (adr != null) {
                    out.print("<td>"+adr.getStreet()+" "+adr.getZip()+" "+adr.getCountry()+"</td>");
                } else {
                    out.print("<td></td>");
                }

                Iterator<PhoneNumber> iterator = contact.getPhones().iterator();
                String home = "";
                String work = "";
                String cell = "";

                while (iterator.hasNext()) {
                    PhoneNumber pn = iterator.next();
                    switch (pn.getType()) {
                        case HOME:
                            home = pn.getPhoneNumber();
                            break;
                        case WORK:
                            work = pn.getPhoneNumber();
                            break;
                        case CELL:
                            cell = pn.getPhoneNumber();
                    }

                }
                out.print("<td>"+home+"</td>");
                out.print("<td>"+work+"</td>");
                out.print("<td>"+cell+"</td>");

                StringBuilder groups = new StringBuilder();
                for (ContactGroup cg : contact.getBooks()) {
                    groups.append(cg.getGroupName()+" ");
                }
                out.print(("<td>"+groups+"</td>"));
                out.print("</tr>");
            }
        }
    %>

</table>


</body>
</html>