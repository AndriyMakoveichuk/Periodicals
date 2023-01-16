package com.epam.periodicals.command.admin;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.UserDao;
import com.epam.periodicals.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockUsersCommand implements Command {

    private final UserDao userDao = UserDao.getInstance();
    private Map<String, Object> sessionAttributes = new HashMap<>();





    @Override
    public Router execute(RequestContent requestContent) {
        int id = Integer.parseInt(requestContent.getRequestParameters().get("id"));
        User user = userDao.get(id);
        user.setActive(!user.isActive());
        userDao.update(user);

        return new Router(true, "/periodicals?command=manageUsers", sessionAttributes);

    }
}
