package com.epam.lab.pet_project.controllers.user_servlets;

import com.epam.lab.pet_project.entities.humans.User;
import com.epam.lab.pet_project.services.TestService;
import com.epam.lab.pet_project.services.UserService;
import com.epam.lab.pet_project.utils.StringCrypter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FinishTestServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(FinishTestServlet.class);

    UserService _userService = new UserService();
    private final TestService _testService = new TestService();
    private final StringCrypter crypter = new StringCrypter(new byte[]{1,4,5,6,8,9,7,8});

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd;
        long idTest = Long.parseLong(req.getParameter("testId"));
        Double mark = Double.valueOf(req.getParameter("mark"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        user.setUserName(crypter.decrypt(user.getUserName()));
        user.setPassword(crypter.decrypt(user.getPassword()));

        _userService.writeTestMark(idTest, mark, user);
        req.setAttribute("tests", _testService.findAll(((String) session.getAttribute("language")).toUpperCase()));
        req.setAttribute("subjects", _testService.findAllSubjects(((String) session.getAttribute("language")).toUpperCase()));
        rd = req.getRequestDispatcher("/WEB-INF/jsp/" + session.getAttribute("language") +
                "/user_form.jsp");

        LOG.debug("Test was finished");
        rd.forward(req, resp);
    }
}
