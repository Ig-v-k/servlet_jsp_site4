<%--@elvariable id="courseId" type="java.lang.String"--%>
<%--@elvariable id="course" type="com.ig.model.Course"--%>
<%--@elvariable id="a" type="empty"--%>

<%--<%
    String courseId = (String)request.getAttribute("courseId");
    Course course = (Course)request.getAttribute("course");
%>--%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<h3>${course.userName}</h3>
<a href="<c:url value="/login?logout" />">Logout</a>
<h2>Course #${courseId}: <c:out value="${course.name}" /></h2>
<i>Professor Name - <c:out value="${course.professorName}" /></i><br /><br />
<c:out value="${course.name}" /><br /><br />
Students:
<c:if test="${course.numberOfStudent > 0}">
    <c:forEach items="${course.student}" var="students"
               varStatus="status">

        <c:if test="${course.student.size() == 0}">
            <i style="color: darkred"><c:out value="${a}"/></i>
        </c:if>

        <c:out value="${students.name}"/>
        <c:out value="a">, </c:out>

<%--        <c:if test="${!status.first}">, </c:if>
        <a href="<c:url value="/courses">
                    <c:param name="action" value="download" />
                    <c:param name="..." value="${...}" />
                    <c:param name="..." value="${...}" />
                </c:url>"><c:out value="${...}" /></a>--%>
    </c:forEach><br /><br />
</c:if>
<br/><a href="<c:url value="/courses" />">Return to list courses</a><br/>
<a href="<c:url value="/courses">
            <c:param name="action" value="addStudent" />
            <c:param name="courseId" value="${courseId}" />
        </c:url>">Add student</a>
</body>
</html>