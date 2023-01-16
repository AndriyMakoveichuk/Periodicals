package com.epam.periodicals.command.user;

import com.epam.periodicals.Paginator;
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
import java.util.Objects;

public class AllPublicationsCommand implements Command {

    private final PublicationDao publicationDao = PublicationDao.getInstance();
    private final TopicDao topicDao = TopicDao.getInstance();
    private Map<String, Object> sessionAttributes = new HashMap<>();
    private final Map<String, Object> newRequestAttributes = new HashMap<>();
    private List<Publication> publicationsList;
    private List<Topic> topicList;

    @Override
    public Router execute(RequestContent requestContent) {

        sessionAttributes = requestContent.getSessionAttributes();

        if (requestContent.getRequestParameters().get("filter") == null &&
                requestContent.getRequestParameters().get("search") == null
                && requestContent.getRequestParameters().get("sorting") == null) {
            publicationsList = publicationDao.listAll();


        } else {

            int sortingType = Integer.parseInt(requestContent.getRequestParameters().get("sorting"));
            String searchString = requestContent.getRequestParameters().get("search");
            int topicIdFilter = Integer.parseInt(requestContent.getRequestParameters().get("filter"));

            StringBuilder queryBuilder = new StringBuilder("SELECT id, " +
                    "title_ua, price, title_en, description_ua, description_en ");
            String filterQuery;
            String searchQueryStart;

            if (topicIdFilter == 0) {
                filterQuery = "FROM publications ";
                searchQueryStart = "WHERE ";

            } else {
                filterQuery = "FROM topics_publications " +
                        "INNER JOIN publications ON topics_publications.publication_id = publications.id " +
                        "WHERE topic_id = " + topicIdFilter + " ";
                searchQueryStart = "AND ";
            }
            queryBuilder.append(filterQuery);

            if (!Objects.equals(searchString, "")) {
                queryBuilder.append(searchQueryStart).append("title_en LIKE \"%").append(searchString).append("%\" ");
            }
            if (sortingType == 1) {
                queryBuilder.append("ORDER BY price");
            }
            if (sortingType == 2) {
                queryBuilder.append("ORDER BY title_en");
            }

            String query = queryBuilder.toString();
            publicationsList = publicationDao.getPublicationsByCustomQuery(query);
        }

        topicList = topicDao.listAll();

        int totalPages = publicationsList.size()/5 + 1;
        int pageNum = 1;
        try {
            int nuPageNum = Integer.parseInt( requestContent.getRequestParameters().get("page"));
            if (nuPageNum > totalPages) {
                pageNum = totalPages;
            } else if (nuPageNum != 0) {
                pageNum = nuPageNum;
            }
        } catch (Exception e) {

        }
        Paginator<Publication> paginator = new Paginator<>();

        newRequestAttributes.put("publicationsList", paginator.listDevider(pageNum, publicationsList));
        newRequestAttributes.put("totalPages", totalPages);
        newRequestAttributes.put("topicList", topicList);
        newRequestAttributes.put("pageNum", pageNum);

        User user = (User) sessionAttributes.get("user");


        return new Router(false, "/publications",sessionAttributes, newRequestAttributes);
    }
}
