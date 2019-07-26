<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<a href="<c:url value="/login?logout" />">Logout</a>
<h2>Create a Ticket</h2>
<form method="POST" action="courses">
    <input type="hidden" name="action" value="create"/>
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