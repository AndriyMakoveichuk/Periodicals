package com.epam.periodicals.command.admin;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.TopicDao;
import com.epam.periodicals.dao.UserDao;
import com.epam.periodicals.model.Topic;
import com.epam.periodicals.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageUsersCommand implements Command {

    private final UserDao userDao = UserDao.getInstance();
    private Map<String, Object> sessionAttributes = new HashMap<>();
    private final Map<String, Object> newRequestAttributes = new HashMap<>();
    private List<User> usersList;




    @Override
    public Router execute(RequestContent requestContent) {

        usersList = userDao.listAll();
        newRequestAttributes.put("usersList", usersList);
        return new Router(false, "/manage_users", sessionAttributes, newRequestAttributes);

    }
}
