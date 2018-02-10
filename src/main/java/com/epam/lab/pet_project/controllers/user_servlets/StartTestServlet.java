package com.epam.lab.pet_project.controllers.user_servlets;

import com.epam.lab.pet_project.services.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StartTestServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(StartTestServlet.class);

    private final TestService _testService = new TestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        long id = Long.parseLong(req.getParameter("testId"));
        req.setAttribute("test", _testService.findById(id, ((String) session.getAttribute("language")).toUpperCase()));
        RequestDispatcher rd;
        rd = req.getRequestDispatcher("/WEB-INF/jsp/" + session.getAttribute("language") +
                "/test_form.jsp?" + session.getId());

        LOG.debug("test starts");

        rd.forward(req, resp);
    }
}
