<%--@elvariable id="courseId" type="java.lang.String"--%>
<%--@elvariable id="course" type="com.ig.model.Course"--%>
<template:basic htmlTitle="${course.name}"
                bodyTitle="Course #${courseId}: ${course.name}">
    <i>User - <c:out value="${course.userName}" /><br />
        Created <ig:formatDate value="${course.dateFormat}" type="both" timeStyle="long" dateStyle="full" /></i><br /><br />
    <c:out value="${course.professorName}" /><br /><br />
    <c:if test="${course.numberOfStudent > 0}">
        Students:
        <c:forEach items="${course.student}" var="students"
                   varStatus="status">
            <c:if test="${!status.first}">, </c:if>
            <c:out value="${students.name}"/>,
            <%--<a href="<c:url value="/tickets">
                    <c:param name="action" value="download" />
                    <c:param name="courseId" value="${courseId}" />
                    <c:param name="courseStudentName" value="${course.studentName}" />
                </c:url>"><c:out value="${course.studentName}" /></a>--%>
        </c:forEach><br /><br />
    </c:if>
</template:basic>