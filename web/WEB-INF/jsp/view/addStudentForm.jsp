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
    <label>
        <input type="text" name="studname">
    </label><br/><br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>