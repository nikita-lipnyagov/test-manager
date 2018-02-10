<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Тест</title>
    <link href="<c:url value="/resources/html_css/bootstrap.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/html_css/project_css/test_form.css"/>" rel="stylesheet"/>
</head>
<body>
<div class="container-fluid">
    <div class="test_container">
        <!--  Start header-->
        <div class="row" id="header">
            <div class="col-xs-9">
                <h1 id="header_text">${test.testName}</h1>
            </div>
        </div>
        <!--   End header-->
        <div class="info">
            <div class="col-xs-1">Таймер: </div>
            <div class="col-xs-11" id="timer"></div>
        </div>
        <div>
            <c:forEach items="${test.questions}" var="question">
                <c:set var = "idCounter" scope = "session" value = "0"/>
                <p>${question.question}</p>
                <div class="answers">
                    <c:forEach items="${question.answers}" var="answer">
                        <c:set var = "idCounter" value = "${idCounter + 1}"/>
                        <input type="radio" id="${idCounter}" value="${question.ringhtAnswer}" name ="${question.questionId}"  class="answer"> ${answer.answer}<br>
                    </c:forEach>
                    <c:set var = "idCounter" value = "0"/>
                </div>
            </c:forEach>
        </div>
        <input id="finish_button" type="submit" value="Закончить тест" onclick="calculateMark(${test.numberOfQuestions})">
    </div>
</div>
<!--Javascript-->
<script src="<c:url value="/resources/js/jquery-3.2.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/project_scripts/test_form_script.js"/>"></script>
<script>
    initializeTimer(${test.timeLimit});
</script>
</body>
</html>
