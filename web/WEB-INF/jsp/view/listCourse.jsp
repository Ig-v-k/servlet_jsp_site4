<%--@elvariable id="courseDatabase" type="java.util.Map<Integer, com.ig.model.Course>"--%>
<template:basic htmlTitle="Courses" bodyTitle="Courses">
    <c:choose>
        <c:when test="${fn:length(courseDatabase) == 0}">
            <i>There are no course in the system.</i>
        </c:when>
        <c:otherwise>
            <c:forEach items="${courseDatabase}" var="entry">
                Course ${entry.key}: <a href="<c:url value="courses">
                    <c:param name="action" value="view" />
                    <c:param name="courseId" value="${entry.key}" />
                </c:url>">
                <c:out value="${ig:abbreviateString(entry.value.professorName, 60)}" />
            </a><br />
                <c:out value="${entry}" /> created ticket
                <ig:formatDate value="${entry.value.dateFormat}" type="both"
                                 timeStyle="short" dateStyle="medium" /><br />
                <br />
            </c:forEach>
        </c:otherwise>
    </c:choose>
</template:basic>