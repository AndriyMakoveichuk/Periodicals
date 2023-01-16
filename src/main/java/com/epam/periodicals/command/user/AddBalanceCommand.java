package com.epam.periodicals.command.user;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.UserDao;
import com.epam.periodicals.model.User;

import java.util.HashMap;
import java.util.Map;

public class AddBalanceCommand implements Command {

    private final UserDao userDao = UserDao.getInstance();
    private Map<String, Object> sessionAttributes = new HashMap<>();

    @Override
    public Router execute(RequestContent requestContent) {
        User user = (User) requestContent.getSessionAttributes().get("user");
        float sum = Float.parseFloat(requestContent.getRequestParameters().get("sum"));
        user.setAccount(user.getAccount() + sum);
        userDao.update(user);
        sessionAttributes = requestContent.getSessionAttributes();
        sessionAttributes.replace("user", user);
        Router router = new Router(true,"/balance", sessionAttributes );

        return router;
    }
}
