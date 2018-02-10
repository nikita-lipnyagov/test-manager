package com.epam.lab.pet_project.controllers.admin_servlets;

import com.epam.lab.pet_project.services.AnswerService;
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

public class CreateAnswerServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CreateAnswerServlet.class);

    private final UserService _userService = new UserService();
    private final QuestionService _questionService = new QuestionService();
    private final AnswerService _answerService = new AnswerService();
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

        String questionEnglishVersion = req.getParameter("answerEng");
        String questionRussianVersion = decodeGetParameter(req.getParameter("answerRus"));
        String questionName = req.getParameter("questionName");
        long questionId = _questionService.findIdByQuestionName(decodeGetParameter(questionName));

        if(questionEnglishVersion != null){
            _answerService.createNewAnswer(questionEnglishVersion, questionId, "ENG");
            _answerService.createNewAnswer(questionRussianVersion, questionId, "RUS");

            LOG.debug("create new Answer ");
        }else {
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
