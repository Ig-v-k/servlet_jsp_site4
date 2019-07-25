<%@ page session="false" import="java.util.Map" %>
<%
    @SuppressWarnings("unchecked")
    Map<Integer, Course> courseDatabase = (Map<Integer, Course>)request.getAttribute("courseDatabase");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<h2>Course</h2>
<a href="<c:url value="/courses">
            <c:param name="action" value="create" />
        </c:url>">Create Course</a><br /><br />
<%
    if(courseDatabase.size() == 0)
    {
        %><i>There are no course in the system.</i><%
    }
    else
    {
        for(int id : courseDatabase.keySet())
        {
            String idString = Integer.toString(id);
            Course course = courseDatabase.get(id);
            %>Course #<%= idString %> --> <a href="<c:url value="courses">
                                    <c:param name="action" value="view" />
                                    <c:param name="courseId" value="<%= idString %>" />
                                </c:url>"><%= course.getName() %></a> (professor -->
            <%= course.getProfessorName() %>)<br /><%
        }
    }
%>
</body>
</html>