package com.epam.periodicals.dao;

import com.epam.periodicals.model.Publication;
import com.epam.periodicals.model.User;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/periodicalsdb");
        dataSource.setUsername("root");
        dataSource.setPassword("7EbWy^hA%EDFvX@P");


        PublicationDao publicationDao = PublicationDao.getInstance(dataSource);
        UserDao userDao = UserDao.getInstance(dataSource);
        TopicDao topicDao = TopicDao.getInstance(dataSource);

        List<Publication> publicationList = publicationDao.getPublicationsOfTopic(2);
        for (Publication u: publicationList) {
            System.out.println(u.getTitleEn());
            System.out.println("lol");

        }





    }
}
