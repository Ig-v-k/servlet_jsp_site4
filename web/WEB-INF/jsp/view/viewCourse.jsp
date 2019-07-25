<%@ page session="false" %>
<%
    String courseId = (String)request.getAttribute("courseId");
    Course course = (Course)request.getAttribute("course");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<h2>Course #<%= courseId %>: <%= course.getName() %></h2>
<i>Professor Name - <%= course.getProfessorName() %></i><br /><br />
<%= course.getName() %><br /><br />
<%
    if(course.getNumberOfStudent() > 0)
    {
        %>Students: <%
            int i = 0;
            for(Student a : course.getStudent())
            {
                if(i++ > 0)
                    out.print(", ");
                %><%--<a href="<c:url value="/courses">
                                        <c:param name="action" value="download" />
                                        <c:param name="courseId" value="<%= courseId %>" />
                                        <c:param name="student" value="<%= a.getName() %>" />
                                    </c:url>">--%><%= a.getName() %>
                <%
            }
        %><br /><br /><%
    }
%>
<a href="<c:url value="courses"/>">Return to list course</a><br>
<a href="<c:url value="courses">
    <c:param name="action" value="addStudent"/>
    <c:param name="courseId" value="<%= courseId %>"/>
</c:url>">Add student</a>
</body>
</html>