<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="htmlTitle" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ attribute name="bodyTitle" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ attribute name="headContent" fragment="true" required="false" %>
<%@ attribute name="navigationContent" fragment="true" required="true" %>
<%@ include file="/WEB-INF/jsp/base.jspf"%>

<!DOCTYPE html>
<html>
<head>
    <title>Customer Support :: <c:out value="${fn:trim(htmlTitle)}" /></title>
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<c:url value="/resourse/stylesheet/main.css"/>" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.0.0/moment.min.js"></script>
    <script type="text/javascript" lang="javascript">
        var postInvisibleForm = function(url, fields) {
            var form = $('<form id="mapForm" method="post"></form>')
                .attr({ action: url, style: 'display: none;' });
            for(var key in fields) {
                if(fields.hasOwnProperty(key))
                    form.append($('<input type="hidden">').attr({
                        name: key, value: fields[key]
                    }));
            }
            $('body').append(form);
            form.submit();
        };
        var newChat = function() {
            postInvisibleForm('<c:url value="/chat" />', { action: 'new' });
        };
    </script>
    <jsp:invoke fragment="headContent" />
</head>
<body onload="myFunction()" style="margin:auto; padding-top: 70px;">
<div id="loader"></div>
<div style="display:none;" id="myDiv" class="animate-bottom">
    <table border="0" id="bodyTable">
        <h1>Multinational Widget Corporation</h1><span style="color:red">[ ${loginedUser.userName} ]</span><br/><br/>
        <tbody>
        <tr>
            <td class="sidebarCell">
                <jsp:invoke fragment="navigationContent" />
            </td>
            <td class="contentCell">
                <h2><c:out value="${fn:trim(bodyTitle)}" /></h2>
                <jsp:doBody />
            </td>
        </tr>
        </tbody>
    </table>
</div>
<script>
    var myVar;
    function myFunction() {
        myVar = setTimeout(showPage, 500);
    }
    function showPage() {
        document.getElementById("loader").style.display = "none";
        document.getElementById("myDiv").style.display = "block";
    }
</script>
</body>
</html>