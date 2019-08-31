<%--@elvariable id="username" type="java.lang.String"--%>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="htmlTitle" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ attribute name="bodyTitle" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ attribute name="extraHeadContent" fragment="true" required="false" %>
<%@ attribute name="extraNavigationContent" fragment="true" required="false" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>

<template:main htmlTitle="${htmlTitle}" bodyTitle="${bodyTitle}">
    <jsp:attribute name="headContent">
        <jsp:invoke fragment="extraHeadContent" />
    </jsp:attribute>

    <jsp:attribute name="navigationContent">
        <a href="<c:url value="/courses/${loginedUser.userName}" />">List Course</a><br />
        <a href="<c:url value="/courses/${loginedUser.userName}">
                <c:param name="action" value="create" />
                </c:url>">Create a Course</a><br />
        <a href="<c:url value="/sessions" />">List Sessions</a><br />
        <a href="<c:url value="/login?logout" />">Log Out</a><br />
        <jsp:invoke fragment="extraNavigationContent" />
    </jsp:attribute>

    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</template:main>