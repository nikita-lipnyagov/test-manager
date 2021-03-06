<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib uri="/WEB-INF/tag/message_tag.tld" prefix="custom"%>
<html>
<head>
    <title>Admin form</title>
    <link href="<c:url value="/resources/html_css/bootstrap.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/html_css/project_css/admin_form.css"/>" rel="stylesheet"/>
</head>
<body>

<fmt:setLocale value = "${locale}" scope="session"/>
<%--<fmt:setBundle basename = "i18n.messages" var = "lang"/>--%>

<div class="container-fluid">
    <div class="test_container">
        <!--  Start header-->
        <div class="row" id="header">
            <div class="col-xs-11">
                <h1 id="header_text">
                    <custom:message key="admin_header" language="${locale}"/>
                </h1>
            </div>
            <div class="col-xs-1">
                <a  href="<c:url value="/logOut"/>"><img src="<c:url value="/resources/images/sign-out.png"/>" alt="Log out"/></a>
            </div>
        </div>
        <!--   End header-->
        <div class="row">
            <div id = "ban_activate" class="col-xs-3">
                <h1><custom:message key="ban_or_activate" language="${locale}"/></h1>
                <div>
                    <form action="/admin/banOrActivateUser" method="post">
                        <select name = "userName">
                            <option value=" "> </option>
                            <c:forEach items="${activeUserNames}" var="userName">
                                <option value= "${userName}">${userName}</option>
                            </c:forEach>
                        </select>
                        <input type="text" name="info_field" value="ban" class="info_field">
                        <input id="banButton" type="submit" value="Ban">
                    </form>
                </div>
                <div>
                    <form action="/admin/banOrActivateUser" method="post">
                        <select name = "userName">
                            <option value=" "> </option>
                            <c:choose>
                                <c:when test="${bannedUserNames!=null}">
                                    <c:forEach items="${bannedUserNames}" var="userName">
                                        <option value= "${userName}">${userName}</option>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <option value=" "><custom:message key="all_active" language="${locale}"/></option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                        <input type="text"  name="info_field" value="activate" class="info_field">
                        <input id="activateButton" type="submit" value="Activate">
                    </form>
                </div>
            </div>

            <div class="col-xs-9 empty"></div>
        </div>

        <div class="row">
            <div id = "create_test_div" class="col-xs-3">
                <h1><custom:message key="create_test" language="${locale}"/></h1>
                    <c:choose>
                        <c:when test="${error!=null}">
                            <h4>${error}</h4>
                        </c:when>
                    </c:choose>

                    <form action="/admin/createTest" method="post">
                        <table>
                        <tr>
                            <td><custom:message key="name" language="${locale}"/>:</td>
                            <td><input type='text' name='testName'/></td>
                        </tr>

                        <tr>
                            <td><custom:message key="russianTestName" language="${locale}"/>:</td>
                            <td><input type='text' name='russianTestName'/></td>
                        </tr>

                        <tr>
                            <td><custom:message key="subject" language="${locale}"/>:</td>
                            <td><input type='text' name='subject'/></td>
                        </tr>

                        <tr>
                            <td><custom:message key="level" language="${locale}"/>:</td>
                            <td>
                                <select name = "level">
                                    <option value="EASY">EASY</option>
                                    <option value="MEDIUM">MEDIUM</option>
                                    <option value="HARD">HARD</option>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td><custom:message key="language" language="${locale}"/>:</td>
                            <td>
                                <select name = "language">
                                    <option value="ENG">English</option>
                                    <option value="RUS">Russian</option>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td><custom:message key="questionNumber" language="${locale}"/>:</td>
                            <td><input type='text' name='questionNumber'/></td>
                        </tr>

                        <tr>
                            <td><custom:message key="timeLimit" language="${locale}"/>:</td>
                            <td><input type='text' name='timeLimit'/></td>
                        </tr>
                        </table>

                        <input id="createButton" type="submit" value="Create">
                    </form>
            </div>

            <div id = "create_question_div" class="col-xs-3">

                <h1><custom:message key="createQuestion" language="${locale}"/></h1>
                <form action="/admin/createQuestion" method="post">
                    <table>
                        <tr>
                            <td><custom:message key="question" language="${locale}"/>:</td>
                            <td><input type='text' name='question'/></td>
                        </tr>

                        <tr>
                            <td><custom:message key="russianQuestion" language="${locale}"/>:</td>
                            <td><input type='text' name='russianQuestion'/></td>
                        </tr>

                        <tr>
                            <td><custom:message key="rightAnswer" language="${locale}"/>:</td>
                            <td><input type='text' name='rightAnswer'/></td>
                        </tr>

                        <tr>
                            <td><custom:message key="language" language="${locale}"/>:</td>
                            <td>
                                <select name = "language">
                                    <option value="ENG">English</option>
                                    <option value="RUS">Russian</option>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td><custom:message key="test" language="${locale}"/>:</td>
                            <td>
                                <select id = "testName"  name = "test">
                                    <c:forEach items="${tests}" var="test">
                                        <option value= "${test}">${test}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <input id="createQuestion" type="submit" value="Create">
                </form>
            </div>
            <div id = "create_answer_div" class="col-xs-5">
                <h1><custom:message key="createAnswer" language="${locale}"/></h1>
                <form action="/admin/createAnswer" method="post">
                    <table>
                        <tr>
                            <td><custom:message key="answerEng" language="${locale}"/>:</td>
                            <td><input type='text' name='answerEng'/></td>
                        </tr>
                        <tr>
                            <td><custom:message key="answerRus" language="${locale}"/>:</td>
                            <td><input type='text' name='answerRus'/></td>
                        </tr>
                        <tr>
                            <td><custom:message key="question" language="${locale}"/>:</td>
                            <td>
                                <select id = "questionName"  name = "questionName">
                                    <c:forEach items="${questions}" var="question">
                                        <option value= "${question}">${question}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <input id="createAnswer" type="submit" value="Create">
                </form>
            </div>
        </div>
    </div>
</div>
<!--Javascript-->
<script src="<c:url value="/resources/js/jquery-3.2.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</body>
</html>
