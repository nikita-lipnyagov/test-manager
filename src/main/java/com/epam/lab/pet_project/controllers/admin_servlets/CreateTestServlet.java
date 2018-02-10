package com.epam.lab.pet_project.controllers.admin_servlets;

import com.epam.lab.pet_project.services.QuestionService;
import com.epam.lab.pet_project.services.TestService;
import com.epam.lab.pet_project.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CreateTestServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CreateTestServlet.class);

    private final UserService _userService = new UserService();
    private final TestService _testService = new TestService();
    private final QuestionService _questionService = new QuestionService();

    private static String decodeGetParameter(String parameter) throws UnsupportedEncodingException {
        return new String(parameter.getBytes("ISO-8859-1"), "UTF8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
        HttpSession session = req.getSession();
        if (((String) session.getAttribute("language")).equals("rus")) {
            req.setAttribute("locale", "ru");
        } else {
            req.setAttribute("locale", "en");
        }
        String testName = decodeGetParameter(req.getParameter("testName"));
        String russianTestName = decodeGetParameter(req.getParameter("russianTestName"));
        String subject = decodeGetParameter(req.getParameter("subject"));
        String level = req.getParameter("level");
        String language = req.getParameter("language");
        int questions = 0, timeLimit = 0;
        try{
            questions = Integer.parseInt(req.getParameter("questionNumber"));
            timeLimit = Integer.parseInt(req.getParameter("timeLimit"));

            LOG.debug("parseInt number of questions and timeLimit");
        }catch (Exception e){
            LOG.error("Not valid parameters");
            req.setAttribute("error", "Not valid parametrs ...");
        }

        if (testName != null && subject != null && questions != 0 && timeLimit != 0) {
            long numberOfTest = _testService.doesTestAlreadyExist(russianTestName);
            if (numberOfTest < 0) {
                numberOfTest = _testService.createNewTest(testName, russianTestName, subject, level, language, questions, timeLimit);
                _userService.addTestColumn(numberOfTest);
            } else if(!_testService.willItTestLanguageCopy(russianTestName, language)){
                _testService.createTestTranslation(numberOfTest, testName, russianTestName, subject, level, language, questions, timeLimit);
            }

            LOG.debug("new test was created");
        } else {
            LOG.error("Not valid parameters");
            req.setAttribute("error", "Not valid parameters ...");
        }
        req.setAttribute("activeUserNames", _userService.findAllUserNamesWithStatus("ACTIVE"));
        req.setAttribute("bannedUserNames", _userService.findAllUserNamesWithStatus("BANNED"));
        req.setAttribute("tests", _testService.findAllTestNames(((String) session.getAttribute("language")).toUpperCase()));
        req.setAttribute("questions", _questionService.findAllQuestionNames(((String) session.getAttribute("language")).toUpperCase()));
        rd = req.getRequestDispatcher("/WEB-INF/jsp/admin_form.jsp");
        rd.forward(req, resp);
    }
}
