package com.epam.periodicals.command.admin;

import com.epam.periodicals.command.Command;
import com.epam.periodicals.command.RequestContent;
import com.epam.periodicals.controller.Router;
import com.epam.periodicals.dao.PublicationDao;
import com.epam.periodicals.model.Publication;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChangePublicationCommand implements Command {

    private final PublicationDao publicationDao = PublicationDao.getInstance();
    private Map<String, Object> sessionAttributes = new HashMap<>();

    @Override
    public Router execute(RequestContent requestContent) {
        sessionAttributes = requestContent.getSessionAttributes();
        String id = (String) sessionAttributes.get("id");
        Publication publication = publicationDao.get(Integer.parseInt(id));
        String selectedTopicsStr = requestContent.getRequestParameters().get("topics");
        if (selectedTopicsStr == null || selectedTopicsStr.equals("")) {
            sessionAttributes.put("error", "You need to choose at least one topic for publication");
            return new Router(true,"/periodicals?command=managePublications",sessionAttributes);
        }
        String[] selectedTopics = selectedTopicsStr.split(",");
        boolean sameTopics = publication.getTitleEn().equals(requestContent.getRequestParameters().get("titleEn"))
                || publication.getTitleUa().equals(requestContent.getRequestParameters().get("titleUa"));


        publication.setTitleUa(requestContent.getRequestParameters().get("titleUa"));
        publication.setTitleEn(requestContent.getRequestParameters().get("titleEn"));
        publication.setDescriptionUa(requestContent.getRequestParameters().get("descriptionUa"));
        publication.setDescriptionEn(requestContent.getRequestParameters().get("descriptionEn"));

        try {
            publication.setPrice(Float.parseFloat(requestContent.getRequestParameters().get("price")));
        } catch (Exception e) {
            sessionAttributes.put("error", "Wrong input");
            return new Router(true,"/periodicals?command=managePublications",sessionAttributes);
        }


        String checkBox = requestContent.getRequestParameters().get("addAsNu");

        if (checkBox == null) {
            publicationDao.update(publication);
            publicationDao.deletePublicationsTopics(publication.getId());
        } else if (checkBox.equals("on")) {
            if (sameTopics) {
                sessionAttributes.put("error", "You can`t add new publication with the same title");
                return new Router(true,"/periodicals?command=managePublications",sessionAttributes);
            }

            publicationDao.create(publication);
        }


        for (String topicId: selectedTopics) {
            int topicIdInt = Integer.parseInt(topicId);
            publicationDao.assignTopicToPublication(topicIdInt, publication.getId());
        }

        sessionAttributes.replace("id",0);

        return new Router(true,"/periodicals?command=managePublications",sessionAttributes);

    }
}
