package com.epam.periodicals.dao;

import com.epam.periodicals.model.Admin;
import com.epam.periodicals.model.Publication;
import com.epam.periodicals.model.Topic;
import com.epam.periodicals.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public record ModelSetter(ResultSet resultSet) {

    User buildUser(boolean needsLists) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setName(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setAccount(resultSet.getFloat(4));
        user.setLogin(resultSet.getString(5));
        user.setSurname(resultSet.getString(6));
        user.setActive(resultSet.getBoolean(7));

        if (needsLists) {
            user.setPublications(PublicationDao.getInstance().getPublicationsOfUser(user.getId()));
        }

        return user;
    }

    Admin buildAdmin() throws SQLException {
        Admin admin = new Admin();

        admin.setId(resultSet.getInt(1));
        admin.setLogin(resultSet.getString(2));
        admin.setPassword(resultSet.getString(3));
        admin.setName(resultSet.getString(4));

        return admin;
    }

    Publication buildPublication(boolean needsLists) throws SQLException {
        Publication publication = new Publication();

        publication.setId(resultSet.getInt(1));
        publication.setTitleUa(resultSet.getString(2));
        publication.setPrice(resultSet.getFloat(3));
        publication.setTitleEn(resultSet.getString(4));
        publication.setDescriptionUa(resultSet.getString(5));
        publication.setDescriptionEn(resultSet.getString(6));

        if (needsLists) {
            publication.setTopics(TopicDao.getInstance().getTopicsOfPublication(publication.getId()));
            publication.setUsers(UserDao.getInstance().getUsersOfPublication(publication.getId()));
        }


        return publication;
    }

    Topic buildTopic(boolean needsLists) throws SQLException {
        Topic topic = new Topic();
        topic.setId(resultSet.getInt(1));
        topic.setTitleUa(resultSet.getString(2));
        topic.setTitleEn(resultSet.getString(3));

        if (needsLists) {
            topic.setPublications(PublicationDao.getInstance().getPublicationsOfTopic(topic.getId()));
        }

        return topic;
    }


}
