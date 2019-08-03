<%--@elvariable id="courseId" type="java.lang.String"--%>
<%--@elvariable id="course" type="com.ig.model.Course"--%>
<template:basic htmlTitle="${course.nameCourse}" bodyTitle="Course #${courseId}: ${course.nameCourse}">
    <i>User - <c:out value="${course.userName}" /><br />
        Created <ig:formatDate value="${course.dateFormat}" type="both" timeStyle="long" dateStyle="full" /></i><br /><br />
    Professor: <i><c:out value="${course.professorName}"/></i><br /><br />
    <c:if test="${course.numberOfStudent > 0}">
        Students:
        <c:forEach items="${course.student}" var="students"
                   varStatus="status">
            <i><c:out value="${students.name}"/>,</i>

            <%--<a href="<c:url value="/course">
                    <c:param name="action" value="download" />
                    <c:param name="courseId" value="${courseId}" />
                    <c:param name="courseStudentName" value="${course.studentName}" />
                </c:url>"><c:out value="${course.studentName}" /></a>--%>
        </c:forEach><br /><br />
    </c:if>
    <a href="<c:url value="/courses">
                <c:param name="action" value="addStudent"/>
                <c:param name="courseId" value="${courseId}"/>
                </c:url>">Add student</a>
</template:basic>