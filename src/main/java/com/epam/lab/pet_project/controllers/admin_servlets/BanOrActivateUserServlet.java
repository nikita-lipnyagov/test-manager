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

public class BanOrActivateUserServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(BanOrActivateUserServlet.class);

    private final UserService _userService = new UserService();
    private final TestService _testService = new TestService();
    private final QuestionService _questionService = new QuestionService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
        HttpSession session = req.getSession();
        if (((String) session.getAttribute("language")).equals("rus")) {
            req.setAttribute("locale", "ru");
        } else {
            req.setAttribute("locale", "en");
        }

        String userName = req.getParameter("userName");
        String action = req.getParameter("info_field");

        if(userName != null){
            long id = _userService.findUserIdByUserName(userName);

            if (action.equals("ban")) {
                _userService.changeBanStatusByUserId(id, "BANNED");
            } else {
                _userService.changeBanStatusByUserId(id, "ACTIVE");
            }
            req.setAttribute("activeUserNames", _userService.findAllUserNamesWithStatus("ACTIVE"));
            req.setAttribute("bannedUserNames", _userService.findAllUserNamesWithStatus("BANNED"));

            LOG.debug("user status was changed");
        }

        req.setAttribute("tests", _testService.findAllTestNames(((String) session.getAttribute("language")).toUpperCase()));
        req.setAttribute("questions", _questionService.findAllQuestionNames(((String) session.getAttribute("language")).toUpperCase()));
        rd = req.getRequestDispatcher("/WEB-INF/jsp/admin_form.jsp");
        rd.forward(req, resp);
    }
}
