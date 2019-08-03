<%--@elvariable id="courseId" type="java.lang.String"--%>
<%--@elvariable id="course" type="com.ig.model.Course"--%>
<%--@elvariable id="nameFailed" type="java.lang.Boolean"--%>

<template:basic htmlTitle="${course.nameCourse}" bodyTitle="Course #${courseId}: ${course.nameCourse}">
    <h2>Add student</h2>
    <form method="POST" action="<c:url value="courses">
                                    <c:param name="action" value="addStudent"/>
                                </c:url>">
        Student name:<br/>
        <input type="text" name="studname">
        <c:if test="${nameFailed}">
            <b>A student with that name already exists.</b><br/><br/>
        </c:if>
        <input type="submit" value="Submit"/><br/>
    </form>
</template:basic>