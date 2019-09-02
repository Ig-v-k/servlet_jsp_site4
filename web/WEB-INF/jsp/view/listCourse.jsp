<%--@elvariable id="courseDatabase" type="java.util.Map<Integer, com.ig.model.Course>"--%>
<%--@elvariable id="username" type="java.lang.String"--%>

<template:basic htmlTitle="Courses" bodyTitle="Courses">
    <c:choose>
        <c:when test="${fn:length(courseDatabase) == 0}">
            <i>There are no course in the system.</i>
        </c:when>
        <c:otherwise>
            <c:forEach items="${courseDatabase}" var="entry">
                Course ${entry.key}: <a href="<c:url value="/course/${loginedUser.userName}">
                                                <c:param name="action" value="view" />
                                                <c:param name="courseId" value="${entry.key}" />
                                              </c:url>"><c:out value="${ig:abbreviateString(entry.value.nameCourse, 60)}" /></a><br/>
                <c:out value="${entry.value.userName}" /> - created course
                <ig:formatDate value="${entry.value.dateFormat}" type="both" timeStyle="short" dateStyle="medium" /><br/>
                <a href="<c:url value="/course/${loginedUser.userName}">
                            <c:param name="action" value="deleteCourse"/>
                            <c:param name="courseId" value="${entry.key}"/>
                         </c:url>">Remove</a><br/><br/><br/>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</template:basic>