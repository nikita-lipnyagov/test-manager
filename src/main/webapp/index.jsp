<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login Page</title>
    <link href="<c:url value="resources/html_css/project_css/index.css"/>" rel="stylesheet">
</head>

<body>

<div id="login-box">

    <form name='loginForm' action="/authorization" method='POST'>

        <h1>Sign in</h1>
        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='userName' value=''></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password'/></td>
            </tr>
            <tr>
                <td colspan='2'>
                    <input name="submit" type="submit" value="OK"/>
                </td>
            </tr>
            <tr>
                <td>Select language: </td>
                <td>
                    <select name = "language">
                        <option value="eng">English</option>
                        <option value="rus">Russian</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>

</div>

</body>
</html>

