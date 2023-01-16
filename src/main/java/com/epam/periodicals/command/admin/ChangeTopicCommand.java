package com.epam.periodicals.command.admin;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.PublicationDao;
import com.epam.periodicals.dao.TopicDao;
import com.epam.periodicals.model.Publication;
import com.epam.periodicals.model.Topic;

import java.util.HashMap;
import java.util.Map;

public class ChangeTopicCommand implements Command {

    private final TopicDao topicDao = TopicDao.getInstance();
    private Map<String, Object> sessionAttributes = new HashMap<>();

    @Override
    public Router execute(RequestContent requestContent) {

        sessionAttributes = requestContent.getSessionAttributes();

        String id = (String) sessionAttributes.get("id");
        Topic topic = topicDao.get(Integer.parseInt(id));
        boolean sameTopics = topic.getTitleEn().equals(requestContent.getRequestParameters().get("titleEn"))
                || topic.getTitleUa().equals(requestContent.getRequestParameters().get("titleUa"));
        topic.setTitleUa(requestContent.getRequestParameters().get("titleUa"));
        topic.setTitleEn(requestContent.getRequestParameters().get("titleEn"));
        String checkBox = requestContent.getRequestParameters().get("addAsNu");

        if (checkBox == null) {
            topicDao.update(topic);
        } else if (checkBox.equals("on")) {
            if (sameTopics) {
                sessionAttributes.put("error", "You can`t add new topic with the same title");
            }
            topicDao.create(topic);
        }

        sessionAttributes.replace("id",0);
        return new Router(true,"/periodicals?command=manageTopics",sessionAttributes);

    }
}
