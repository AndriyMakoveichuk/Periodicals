package com.epam.periodicals.command.user;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.UserDao;
import com.epam.periodicals.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegistrationCommand implements Command {

    UserDao userDao = UserDao.getInstance();
    Router router;
    Map<String, Object> sessionAttributes = new HashMap<>();

    @Override
    public Router execute(RequestContent requestContent) {
        String login = requestContent.getRequestParameters().get("login");

        if (login==null || login.equals("")) {
            sessionAttributes.put("error", "Login field shouldn't be empty");
            router = new Router(true, "/registration", sessionAttributes);
            return router;
        }
        User user = userDao.get(login);
        if (user == null) {
            user = new User();
            user.setName(requestContent.getRequestParameters().get("name"));
            user.setSurname(requestContent.getRequestParameters().get("surname"));
            user.setLogin(login);
            user.setPassword(requestContent.getRequestParameters().get("pass"));
            user = userDao.create(user);


            sessionAttributes.put("user", user);

            router = new Router(true,"/login" ,sessionAttributes);

        } else {
            sessionAttributes.put("error", "User already exists");
            router = new Router(true, "/registration", sessionAttributes);
        }


        return router;
    }
}
