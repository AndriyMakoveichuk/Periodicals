package com.epam.periodicals.command.admin;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.PublicationDao;
import com.epam.periodicals.dao.TopicDao;
import com.epam.periodicals.model.Topic;

import java.util.HashMap;
import java.util.Map;

public class DeleteTopicCommand implements Command {

    private final Map<String, Object> sessionAttributes = new HashMap<>();
    TopicDao topicDao = TopicDao.getInstance();

    @Override
    public Router execute(RequestContent requestContent) {
        int id = Integer.parseInt(requestContent.getRequestParameters().get("id"));
        topicDao.delete(id);
        return new Router(true,"/periodicals?command=manageTopics",sessionAttributes);

    }
}
