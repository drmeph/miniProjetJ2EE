<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <title>Welcome to the mini projet Web</title>
    </head>
    <body>
        <form class="cform" id="addcontact" method="post" action="newcontact">
            <table>
                <tr><td>First name:</td><td><input type="text" name="firstname" size="50"></td></tr>
                <tr><td>Last name:</td><td><input type="text" name="lastname" size="50"></td></tr>
                <tr><td>Email:</td><td><input type="text" name="email" size="50"></td></tr>
                <tr><th>Address</th><td></td></tr>
                <tr><td><span class="tab">Street:</span></td><td><input type="text" name="street" size="50"></td></tr>
                <tr><td><span class="tab">ZIP:</span></td><td><input type="text" name="zip" size="50"></td></tr>
                <tr><td><span class="tab">Country Code:</span></td><td><input type="text" name="cc" size="50"></td></tr>
                <tr><th>Phones</th></tr>
                <tr><td><span class="tab">Home</span></td><td> <input type="text" name="homephone" size="50"></td></tr>
                <tr><td><span class="tab">Work</span></td><td><input type="text" name="workphone" size="50"></td></tr>
                <tr><td><span class="tab">Cell</span></td><td><input type="text" name="cellphone" size="50"></td></tr>
                <tr><th>Groups</th></tr>
                <tr><td><span class="tab"><input type="checkbox" name="groups" value="Friend">Friend</span></td></tr>
                <tr><td><span class="tab"><input type="checkbox" name="groups" value="Family">Family</span></td></tr>
                <tr><td><span class="tab"><input type="checkbox" name="groups" value="Colleague">Colleague</span></td></tr>
            </table>
            <br>
            <input type="submit" value="Submit">
            <input type="reset" value="Reset">
        </form>

    </body>
</html>