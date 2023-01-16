package com.epam.periodicals.command.admin;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.TopicDao;
import com.epam.periodicals.model.Publication;
import com.epam.periodicals.model.Topic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageTopicsCommand implements Command {

    private final TopicDao topicDao = TopicDao.getInstance();
    private Map<String, Object> sessionAttributes = new HashMap<>();
    private final Map<String, Object> newRequestAttributes = new HashMap<>();
    private List<Topic> topicList;




    @Override
    public Router execute(RequestContent requestContent) {

        topicList = topicDao.listAll();
        newRequestAttributes.put("topicsList", topicList);
        return new Router(false, "/manage_topics", sessionAttributes, newRequestAttributes);

    }
}
