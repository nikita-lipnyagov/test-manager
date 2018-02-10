package com.epam.lab.pet_project.controllers.user_servlets;

import com.epam.lab.pet_project.dao.impl.jdbc.DBSchema;
import com.epam.lab.pet_project.services.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class TestFilterServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(TestFilterServlet.class);

    private final TestService _testService = new TestService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        synchronized (this) {
            HttpSession session = req.getSession();
            String subject = null;
            String orderedColumns = "";

            if (req.getParameter("checkBoxSortByName") != null) {
                orderedColumns = DBSchema.TESTS.TEST_NAME + ", ";
            }
            if (req.getParameter("checkBoxSortByLevel") != null) {
                orderedColumns += DBSchema.TESTS.LEVEL + ", ";
            }
            if (req.getParameter("checkBoxSortByNumberOfQuestions") != null) {
                orderedColumns += DBSchema.TESTS.NUMBER_OD_QUESTIONS + ", ";
            }
            if (!orderedColumns.equals("")) {
                int index = orderedColumns.lastIndexOf(',');
                if (index > 0) {
                    orderedColumns = orderedColumns.substring(0, index);
                }
            }
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("subject")) {
                    subject = cookie.getValue();
                }
            }
            req.setAttribute("tests", _testService.findFilteredTests(subject, orderedColumns, ((String) session.getAttribute("language")).toUpperCase()));
            req.setAttribute("subjects", _testService.findAllSubjects(((String) session.getAttribute("language")).toUpperCase()));
            RequestDispatcher rd;
            rd = req.getRequestDispatcher("/WEB-INF/jsp/" + session.getAttribute("language") +
                    "/user_form.jsp?" + session.getId());

            LOG.debug("filter tests");

            rd.forward(req, resp);
        }
    }
}
