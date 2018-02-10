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

public class CreateQuestionServlet extends HttpServlet{
    private static final Logger LOG = LoggerFactory.getLogger(CreateQuestionServlet.class);

    private final UserService _userService = new UserService();
    private final QuestionService _questionService = new QuestionService();
    private final TestService _testService = new TestService();

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

        String question = decodeGetParameter(req.getParameter("question"));
        String russianQuestion = decodeGetParameter(req.getParameter("russianQuestion"));
        String language = req.getParameter("language");
        int rightAnswer = 0, testId = -1;
        try{
            rightAnswer = Integer.parseInt(req.getParameter("rightAnswer"));
            testId = (int) _testService.findIdByTestName(req.getParameter("test"));

            LOG.debug("calculate rightAnswer and testId");
        }catch (Exception e){
            LOG.error("Not valid parameters");
            req.setAttribute("error", "Not valid parameters ...");
        }

        if (rightAnswer != 0 && testId != -1) {
            long numberOfQuestion = _questionService.doesQuestionAlreadyExist(russianQuestion);
            if (numberOfQuestion < 0) {
                numberOfQuestion = _questionService.createNewQuestion(question, russianQuestion, language, rightAnswer, testId);
            } else if(!_questionService.willItQuestionLanguageCopy(russianQuestion, language)){
                _questionService.createQuestionTranslation(numberOfQuestion, question, russianQuestion, language, rightAnswer, testId);
            }

            LOG.debug("new question was created");
        } else {
            LOG.error("Not valid parameters");
            req.setAttribute("error", "Not valid parametrs ...");
        }
        req.setAttribute("activeUserNames", _userService.findAllUserNamesWithStatus("ACTIVE"));
        req.setAttribute("bannedUserNames", _userService.findAllUserNamesWithStatus("BANNED"));
        req.setAttribute("tests", _testService.findAllTestNames(((String) session.getAttribute("language")).toUpperCase()));
        req.setAttribute("questions", _questionService.findAllQuestionNames(((String) session.getAttribute("language")).toUpperCase()));
        rd = req.getRequestDispatcher("/WEB-INF/jsp/admin_form.jsp");
        rd.forward(req, resp);
    }
}
