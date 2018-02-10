<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tests</title>
    <link href="<c:url value="/resources/html_css/bootstrap.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/html_css/project_css/user_form.css"/>" rel="stylesheet"/>
</head>

<body>

<div class="container-fluid">
    <!--  Start header-->
    <div class="row" id="header">
        <div class="col-xs-11">
            <h1 id="header_text">Тестовый менеджер</h1>
        </div>

        <div class="col-xs-1">
            <a  href="<c:url value="/user/showProfile"/>"><img src="<c:url value="/resources/images/avatar.png"/>" alt="User cabinet"/></a>
        </div>

    </div>
    <!--   End header-->

    <!--    Start main blocks-->
    <div class="row">
        <div class="col-xs-12">
            <table class="table table-responsive table-hover">
                <thead>
                <tr>
                    <th>Тест</th>
                    <th>Предмет</th>
                    <th>Уровень</th>
                    <th>Вопросов</th>
                    <th>Лимит времени (сек.)</th>
                    <th>
                        Фильтр:
                        <select id = "subjectName"  name = "subjectName">
                                <option value=" "> </option>
                            <c:forEach items="${subjects}" var="subject">
                                <option value= "${subject}">${subject}</option>
                            </c:forEach>
                        </select>
                    </th>
                    <form action="/user/showTests/filter" method="post">
                        <th>
                            <input name="checkBoxSortByName" type="checkbox">Имя
                        </th>
                        <th>
                            <input name="checkBoxSortByLevel" type="checkbox">Уровень
                        </th>
                        <th>
                            <input name="checkBoxSortByNumberOfQuestions" type="checkbox">Количество вопросов
                        </th>
                        <th><input id="submitButton" type="submit" value="Фильтровать"></th>
                    </form>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${tests}" var="test">
                    <tr>
                        <td>${test.testName}</td>
                        <td>${test.subject}</td>
                        <td>${test.level}</td>
                        <td>${test.numberOfQuestions}</td>
                        <td>${test.timeLimit}</td>
                        <td><a  href="<c:url value="/user/showTests/test?testId=${test.testId}"/>"><img src="<c:url value="/resources/images/play.png"/>" alt="Start test" title="Start test"></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <!--    End main blocks-->
</div>
<!--Javascript-->
<script src="<c:url value="/resources/js/jquery-3.2.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript">
    //Adding filter
    document.cookie = 'subject=;path=/user/showTests/filter;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    $('#submitButton').on('click', function() {
        var subject = $('#subjectName').val();
        if(subject !== " ") {
            document.cookie = 'subject=' + subject + '; path=/user/showTests/filter';
        }
    });
</script>
</body>
</html>
