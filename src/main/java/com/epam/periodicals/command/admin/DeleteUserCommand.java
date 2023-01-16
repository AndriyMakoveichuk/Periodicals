package com.epam.periodicals.command.admin;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.TopicDao;
import com.epam.periodicals.dao.UserDao;

import java.util.HashMap;
import java.util.Map;

public class DeleteUserCommand implements Command {
    private final Map<String, Object> sessionAttributes = new HashMap<>();
    UserDao userDao = UserDao.getInstance();

    @Override
    public Router execute(RequestContent requestContent) {
        int id = Integer.parseInt(requestContent.getRequestParameters().get("id"));
        userDao.delete(id);
        return new Router(true,"/periodicals?command=manageUsers",sessionAttributes);

    }
}
