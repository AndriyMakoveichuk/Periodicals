package com.epam.periodicals.command.common;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.AdminDao;
import com.epam.periodicals.dao.UserDao;
import com.epam.periodicals.model.Admin;
import com.epam.periodicals.model.User;

import java.util.HashMap;
import java.util.Map;

public class LoginCommand implements Command {

    private final UserDao userDao = UserDao.getInstance();
    private final AdminDao adminDao = AdminDao.getInstance();
    private final Map<String, Object> sessionAttributes = new HashMap<>();

    @Override
    public Router execute(RequestContent requestContent) {
        String login = requestContent.getRequestParameters().get("login");
        String password = requestContent.getRequestParameters().get("password");

        User user = userDao.get(login);
        Admin admin = adminDao.get(login);

        Router router;
        if (user == null && admin == null) {
            sessionAttributes.put("error", "User does not exist");
            sessionAttributes.remove("user");
            router = new Router(true,"/login", sessionAttributes );
            return router;
        }
        if (user != null) {
            if (!user.getPassword().equals(password)) {
                sessionAttributes.put("error", "Incorrect password");
                sessionAttributes.remove("user");
                router = new Router(true,"/login", sessionAttributes );
                return router;
            }
            if (!user.isActive()) {
                sessionAttributes.put("error", "Account is blocked");
                sessionAttributes.remove("user");
                router = new Router(true,"/login", sessionAttributes );
                return router;
            }
            sessionAttributes.put("user", user);
            router = new Router(true,"/home" ,sessionAttributes);
        } else  {
            if (!admin.getPassword().equals(password)) {
                sessionAttributes.put("error", "Incorrect password");
                sessionAttributes.remove("user");
                router = new Router(true,"/login", sessionAttributes );
                return router;
            }
            sessionAttributes.put("admin", admin);
            sessionAttributes.remove("user");
            router = new Router(true,"/admin-home" , sessionAttributes);
        }
        return router;
    }


}
