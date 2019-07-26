<%--@elvariable id="courseDatabase" type="java.util.Map<Integer, com.ig.model.Course>"--%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<a href="<c:url value="/login?logout" />">Logout</a>
<h2>Tickets</h2>
<a href="<c:url value="/courses">
            <c:param name="action" value="create" />
        </c:url>">Create Course</a><br /><br />
<c:choose>
    <c:when test="${fn:length(courseDatabase) == 0}">
        <i>There are no course in the system.</i>
    </c:when>
    <c:otherwise>
        <c:forEach items="${courseDatabase}" var="entry">
            Course ${entry.key}: <a href="<c:url value="/courses">
                        <c:param name="action" value="view" />
                        <c:param name="courseId" value="${entry.key}" />
                    </c:url>"><c:out value="${entry.value.name}" /></a>
            (Professor : <c:out value="${entry.value.professorName}" />)<br />
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>