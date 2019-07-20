<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>add student</title>
</head>
<body>
<h2>Add student</h2>
<form method="POST" action="courses?action=addStudent">
    Student name:<br/>
    <input type="text" name="studname"><br/><br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>