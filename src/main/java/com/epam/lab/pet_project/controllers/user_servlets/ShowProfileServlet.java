package com.epam.lab.pet_project.controllers.user_servlets;

import com.epam.lab.pet_project.entities.humans.User;
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

public class ShowProfileServlet extends HttpServlet{
    private static final Logger LOG = LoggerFactory.getLogger(ShowProfileServlet.class);

    private final UserService _userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        long userId = ((User)session.getAttribute("user")).getUserId();
        User user = _userService.findById(userId);
        req.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/" + session.getAttribute("language") +
                "/user_cabinet.jsp");

        LOG.debug("show user profile");
        rd.forward(req, resp);
    }
}
