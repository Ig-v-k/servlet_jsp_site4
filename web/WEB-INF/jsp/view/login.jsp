<%--@elvariable id="loginFailed" type="java.lang.Boolean"--%>
<%--@elvariable id="uidloginFailed" type="java.lang.Boolean"--%>
<%--@elvariable id="db" type="java.util.Map<String, com.ig.model.UserAccount>"--%>

<template:loggedOut htmlTitle="Log In" bodyTitle="Log In">
    You must log in to access the customer support site.<br /><br />
    <c:if test="${loginFailed}">
        <div style="background:transparent url(/resourse/images/info_icon.jpg) no-repeat scroll 14px 13px;
                    padding:2px 0 0 27px;
                    color:#CC0000;
                    font-size:14px;
                    font-weight:bold;
                    padding-bottom:7px;
                    border:1px solid #D3D5C3;
                    border-radius:5px 5px 5px 5px;
                    margin-bottom:5px;
                    padding:14px 20px 6px 48px;
                    min-height:30px;
                    max-width:600px;">
            <b>The username and password you entered are not correct. Please try
            again.</b><br/><br/>
        </div>
    </c:if>
    <form method="POST" action="<c:url value="/login" />">
        <fieldset>
            <legend>Log in service</legend>
            Username<br/>
            <input type="text" name="username"/><br/><br/>
            Password<br/>
            <input type="password" name="password"/><br/><br/>
            UID<br/>
            <input type="text" name="uid"/><br/><br/>
            <c:if test="${uidloginFailed}">
                <div style="background:transparent url(/resourse/images/info_icon.jpg) no-repeat scroll 14px 13px;
                    padding:2px 0 0 27px;
                    color:#CC0000;
                    font-size:14px;
                    font-weight:bold;
                    padding-bottom:7px;
                    border:1px solid #D3D5C3;
                    border-radius:5px 5px 5px 5px;
                    margin-bottom:5px;
                    padding:14px 20px 6px 48px;
                    min-height:30px;
                    max-width:600px;">
                    <b>The username : uid : entered are not correct. Please try again.</b><br/><br/>
                </div>
            </c:if>
            <input type="submit" value="Log In"/>
            <p style="color:blue;">Login with:</p>

            <c:forEach items="${db}" var="i">
                Login: ${i.key} => Password: ${i.value.password} => UID: ${i.value.uid}<br/>
            </c:forEach>
        </fieldset>
    </form>
<script>
    function random_bg_color() {
        var x = Math.floor(Math.random() * 256);
        var y = Math.floor(Math.random() * 256);
        var z = Math.floor(Math.random() * 256);
        var bgColor = "rgb(" + x + "," + y + "," + z + ")";
        console.log(bgColor);

        document.body.style.background = bgColor;
    }
</script>
</template:loggedOut>
