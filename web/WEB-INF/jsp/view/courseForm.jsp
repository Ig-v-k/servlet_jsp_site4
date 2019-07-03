<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<h2>Create a Course</h2>
<form method="POST" action="courses?action=create">
    Course name:<br/>
    <input type="text" name="courseName"><br/><br/>
    Professor name<br/>
    <input type="text" name="professor"><br/><br/>
    Student name:<br/>
    <input type="text" name="student"><br/><br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>