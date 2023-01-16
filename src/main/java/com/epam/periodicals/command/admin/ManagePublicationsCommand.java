package com.epam.periodicals.command.admin;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.PublicationDao;
import com.epam.periodicals.dao.TopicDao;
import com.epam.periodicals.model.Publication;
import com.epam.periodicals.model.Topic;
import com.epam.periodicals.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagePublicationsCommand implements Command {

    private final PublicationDao publicationDao = PublicationDao.getInstance();
    private final TopicDao topicDao = TopicDao.getInstance();
    private Map<String, Object> sessionAttributes = new HashMap<>();
    private final Map<String, Object> newRequestAttributes = new HashMap<>();
    private List<Publication> publicationsList;
    private List<Topic> topicList;




    @Override
    public Router execute(RequestContent requestContent) {
        sessionAttributes = requestContent.getSessionAttributes();
        publicationsList = publicationDao.listAll();
        topicList = topicDao.listAll();
        newRequestAttributes.put("publicationsList", publicationsList);
        newRequestAttributes.put("topicsList", topicList);
        return new Router(false, "/manage_publications", sessionAttributes, newRequestAttributes);
    }
}
