package com.epam.lab.pet_project.controllers;

import com.epam.lab.pet_project.entities.humans.User;
import com.epam.lab.pet_project.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(LogOutServlet.class);

    private UserService _userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        if(role.equals("USER")){
            User user = (User) session.getAttribute("user");
            _userService.changeLogStatus(user, false);
            LOG.debug("user was logged out");
        }else{
            LOG.debug("admin was logged out");
        }
        session.invalidate();
        RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");

        rd.forward(req, resp);
    }
}
