<%@ page import="java.util.List" %>
<%@ page import="com.drmeph.entity.Contact" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.drmeph.entity.PhoneNumber" %>
<%@ page import="com.drmeph.entity.Address" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="com.drmeph.entity.ContactGroup" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <title>Welcome to the mini projet Web</title>
    </head>
    <body>
        <form id="loadcontact" method="post" action="loadcontact">
            Select contact to update :
            <select name="id">
                <%
                    List<Contact> listContact = (List<Contact>) request.getAttribute("listcontact");
                    Contact contact2 = (Contact) request.getAttribute("contacttoupdate");
                    Boolean display = false;

                    if (contact2 != null) {
                        display = true;
                    }

                    if (listContact == null && !display) {
                        response.sendRedirect("initListUp");
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

        <hr>
        <form id="updatecontact" method="post" action="updatecontact" <% if (!display) out.print("hidden=\"true\""); %>>
            <%
                if (display) {
                    out.print("<input type=\"hidden\" name=\"id\" value=\""+contact2.getContactId()+"\">");
                }


            %>
            <table class="contactDisplay" >

                <%
                    if (display) {

                        out.print("<tr><td>First name:</td><td><input type=\"text\" name=\"firstname\" size=\"50\" value=\""+contact2.getFirstName()+"\"></td></tr>");
                        out.print("<tr><td>Last name:</td><td><input type=\"text\" name=\"lastname\" size=\"50\" value=\""+contact2.getLastName()+"\"></td></tr>");
                        out.print("<tr><td>Email:</td><td><input type=\"text\" name=\"email\" size=\"50\" value=\""+contact2.getEmail()+"\"></td></tr>");
                        out.print("<tr><th>Address</th><td></td></tr>");

                        Address adr = contact2.getAdd();
                        String add = "";
                        String zip = "";
                        String cc = "";

                        if (adr != null) {
                            add = (adr.getStreet() == null) ? "" : adr.getStreet();
                            zip = (adr.getZip() == null) ? "" : adr.getZip();
                            cc = (adr.getCountry() == null) ? "" : adr.getCountry();
                        }

                        out.print("<tr><td><span class=\"tab\">Street:</span></td><td><input type=\"text\" name=\"street\" size=\"50\" value=\""+add+"\"></td></tr>");
                        out.print("<tr><td><span class=\"tab\">ZIP:</span></td><td><input type=\"text\" name=\"zip\" size=\"50\" value=\""+zip+"\"></td></tr>");
                        out.print("<tr><td><span class=\"tab\">Country Code:</span></td><td><input type=\"text\" name=\"cc\" size=\"50\" value=\""+cc+"\"></td></tr>");
                        out.print("<tr><th>Phones</th></tr>");

                        Iterator<PhoneNumber> iterator = contact2.getPhones().iterator();
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

                        out.print("<tr><td><span class=\"tab\">Home</span></td><td> <input type=\"text\" name=\"homephone\" size=\"50\" value=\""+home+"\"></td></tr>");
                        out.print("<tr><td><span class=\"tab\">Work</span></td><td><input type=\"text\" name=\"workphone\" size=\"50\" value=\""+work+"\"></td></tr>");
                        out.print("<tr><td><span class=\"tab\">Cell</span></td><td><input type=\"text\" name=\"cellphone\" size=\"50\" value=\""+cell+"\"></td></tr>");
                        out.print("<tr><th>Groups</th></tr>");

                        HashSet<String> groups = new HashSet<String>();
                        Iterator<ContactGroup> iterator1 = contact2.getBooks().iterator();
                        while (iterator1.hasNext()) {
                            ContactGroup cg = iterator1.next();
                            groups.add(cg.getGroupName());
                        }

                        out.print("<tr><td><span class=\"tab\"><input type=\"checkbox\" name=\"groups\" value=\"Friend\"");

                        if (groups.contains("Friend")) out.print("checked");
                        out.print(">Friend</span></td></tr>");
                        out.print("<tr><td><span class=\"tab\"><input type=\"checkbox\" name=\"groups\" value=\"Family\"");

                        if (groups.contains("Family")) out.print("checked");
                        out.print(">Family</span></td></tr>");
                        out.print("<tr><td><span class=\"tab\"><input type=\"checkbox\" name=\"groups\" value=\"Colleague\"");

                        if (groups.contains("Colleague")) out.print("checked");
                        out.print(">Colleague</span></td></tr>");

                    }
                %>
            </table>
            <input type="submit"value="Submit">
        </form>
    </body>
</html>