package com.epam.lab.pet_project.controllers;

import com.epam.lab.pet_project.entities.humans.Admin;
import com.epam.lab.pet_project.entities.humans.User;
import com.epam.lab.pet_project.services.AdminService;
import com.epam.lab.pet_project.services.QuestionService;
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

public class LoginServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    private final UserService _userService = new UserService();
    private final AdminService _adminService = new AdminService();
    private final TestService _testService = new TestService();
    private final QuestionService _questionService = new QuestionService();
    private final StringCrypter crypter = new StringCrypter(new byte[]{1,4,5,6,8,9,7,8});

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            HttpSession session = req.getSession();
            session.setAttribute("language", req.getParameter("language"));

            String userName = req.getParameter("userName");
            String password = req.getParameter("password");
            RequestDispatcher rd;

            String userNameResult = _userService.checkUserName(userName);
            String userPasswordResult = _userService.checkUserPassword(password);

            long id = _userService.findIdByUserNameAndPassword(userName, password);

            if (id > 0 && userNameResult.equals("success") && userPasswordResult.equals("success")) {
                if (_userService.findRoleById(id).equals("USER")) {
                    User user = _userService.findById(id);
                    user.setUserName(crypter.encrypt(user.getUserName()));
                    user.setPassword(crypter.encrypt(user.getPassword()));

                    if(user.getStatus().equals("ACTIVE") && _userService.changeLogStatus(user, true)){
                        rd = req.getRequestDispatcher("/WEB-INF/jsp/" + session.getAttribute("language") +
                                "/user_form.jsp?" + session.getId());
                        session.setAttribute("user", user);
                        session.setAttribute("role", "USER");
                        req.setAttribute("tests", _testService.findAll(((String) session.getAttribute("language")).toUpperCase()));
                        req.setAttribute("subjects", _testService.findAllSubjects(((String) session.getAttribute("language")).toUpperCase()));

                        LOG.debug("user logs in");
                    }else{
                        LOG.error("user was banned");
                        rd = req.getRequestDispatcher("/WEB-INF/jsp/" + session.getAttribute("language") +
                                "/ban_error.jsp");
                    }

                } else {
                    rd = req.getRequestDispatcher("/WEB-INF/jsp/admin_form.jsp?" + session.getId());
                    Admin admin = _adminService.findById(id);
                    admin.setUserName(crypter.encrypt(admin.getUserName()));
                    admin.setPassword(crypter.encrypt(admin.getPassword()));
                    session.setAttribute("admin", admin);
                    session.setAttribute("role", "ADMIN");
                    req.setAttribute("activeUserNames", _userService.findAllUserNamesWithStatus("ACTIVE"));
                    req.setAttribute("bannedUserNames", _userService.findAllUserNamesWithStatus("BANNED"));
                    req.setAttribute("tests", _testService.findAllTestNames(((String) session.getAttribute("language")).toUpperCase()));
                    req.setAttribute("questions", _questionService.findAllQuestionNames(((String) session.getAttribute("language")).toUpperCase()));
                    if(((String) session.getAttribute("language")).equals("rus")){
                        req.setAttribute("locale", "ru");
                    }else{
                        req.setAttribute("locale", "en");
                    }
                    LOG.debug("admin logs in");
                }

            } else {
                LOG.error("Error during authorization");
                rd = req.getRequestDispatcher("/WEB-INF/jsp/eng/login_error.jsp");
            }
            rd.forward(req, resp);
    }
}
