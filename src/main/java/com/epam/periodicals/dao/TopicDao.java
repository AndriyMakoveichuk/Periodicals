package com.epam.periodicals.dao;

import com.epam.periodicals.model.Publication;
import com.epam.periodicals.model.Topic;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicDao implements Dao<Topic> {


    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource;
    private ModelSetter modelSetter;
    private static TopicDao instance;

    private TopicDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private TopicDao() {
        this.dataSource = DatabaseUtil.getDataSource();
    }

    public static TopicDao getInstance() {
        if (instance == null) {
            instance = new TopicDao();
        }
        return instance;
    }

    public static TopicDao getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new TopicDao(dataSource);
        }
        return instance;
    }

    @Override
    public Topic create(Topic topic) {
        String insertTopicQuery = "INSERT INTO topics VALUES(default, ?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertTopicQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, topic.getTitleUa());
            statement.setString(2, topic.getTitleEn());

            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                topic.setId(generatedKey.getInt(1));
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return topic;
    }

    @Override
    public Topic update(Topic topic) {
        String updateTopicsQuery = "UPDATE topics " +
                "SET title_ua=?, title_en=? " +
                "WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateTopicsQuery)) {

            statement.setString(1, topic.getTitleUa());
            statement.setString(2, topic.getTitleEn());
            statement.setInt(3, topic.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return topic;
    }

    @Override
    public Topic get(int id) {
        String getTopicQuery = "SELECT * FROM topics WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(getTopicQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            ModelSetter modelSetter = new ModelSetter(resultSet);

            if (resultSet.next()) {
                return modelSetter.buildTopic(true);
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String deleteFromMtoMQuery = "DELETE FROM topics_publications WHERE topic_id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteFromMtoMQuery)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }

        String deleteTopicQuery = "DELETE FROM topics WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteTopicQuery)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }



    }

    @Override
    public List<Topic> listAll() {
        String selectAllTopicsQuery = "SELECT * FROM topics";
        List<Topic> topicsList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllTopicsQuery)) {
            ResultSet resultSet = statement.executeQuery();
            modelSetter = new ModelSetter(resultSet);
            while (resultSet.next()) {
                topicsList.add(modelSetter.buildTopic(true));
            }

        }
        catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return topicsList;
    }


    List<Topic> getTopicsOfPublication(int id) {
        String selectTopicsQuery = "SELECT id, title_ua, title_en " +
                "FROM topics_publications " +
                "INNER JOIN topics ON topics_publications.topic_id = topics.id " +
                "WHERE publication_id = ?";
        List<Topic> topicsList= new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectTopicsQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            modelSetter= new ModelSetter(resultSet);
            while (resultSet.next()) {
                topicsList.add(modelSetter.buildTopic(false));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return topicsList;
    }


}
