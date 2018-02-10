<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tag/user_cabinet_tag.tld" prefix="custom"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Кабинет</title>
    <link href="<c:url value="/resources/html_css/bootstrap.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/html_css/project_css/user_cabinet.css"/>" rel="stylesheet"/>
</head>
<body>
<div class="container-fluid">
    <!--  Start header-->
    <div class="row" id="header">
        <div class="col-xs-11">
            <h1 id="header_text">Кабинет пользователя</h1>
        </div>
        <div class="col-xs-1">
            <a  href="<c:url value="/logOut"/>"><img src="<c:url value="/resources/images/sign-out.png"/>" alt="Log out"/></a>
        </div>
    </div>
    <!--   End header-->

    <!--    Start main blocks-->
    <div class="row">
        <div class="col-xs-12">
            <table class="table table-responsive table-hover">
                <thead>
                    <tr>
                        <th>Имя пользователя</th>
                        <th>Статус</th>
                        <c:set var = "counter" value = "0"/>
                        <c:forEach items="${user.marks}" var="mark">
                            <c:set var = "counter" value = "${counter + 1}"/>
                            <th>Тест ${counter}</th>
                        </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${user.userName}</td>
                        <td>${user.status}</td>
                        <c:forEach items="${user.marks}" var="mark">
                                <td><custom:testMark mark="${mark}"/></td>
                        </c:forEach>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <!--    End main blocks-->
</div>





<!--Javascript-->
<script src="<c:url value="/resources/js/jquery-3.2.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>
