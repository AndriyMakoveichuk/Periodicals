package com.epam.periodicals.dao;

import com.epam.periodicals.model.Publication;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationDao implements Dao<Publication> {

    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource;
    private ModelSetter modelSetter;
    private static PublicationDao instance;

    private PublicationDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private PublicationDao() {
        this.dataSource = DatabaseUtil.getDataSource();
    }

    public static PublicationDao getInstance() {
        if (instance == null) {
            instance = new PublicationDao();
        }
        return instance;
    }

    public static PublicationDao getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new PublicationDao(dataSource);
        }
        return instance;
    }


    @Override
    public Publication create(Publication publication) {
        String insertPublicationQuery = "INSERT INTO publications VALUES(default, ?,?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertPublicationQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, publication.getTitleUa());
            statement.setFloat(2, publication.getPrice());
            statement.setString(3, publication.getTitleEn());
            statement.setString(4, publication.getDescriptionUa());
            statement.setString(5, publication.getDescriptionEn());

            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                publication.setId(generatedKey.getInt(1));
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return publication;
    }

    public void assignTopicToPublication(int topicId, int publicationId) {
        String assignTopicToPublicationQuery = " INSERT INTO topics_publications " +
                "VALUES(?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(assignTopicToPublicationQuery)) {

            statement.setInt(1, topicId);
            statement.setInt(2, publicationId);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
    }


    public void assignUserToPublication(int userId, int publicationId) {
        String assignUserToPublicationQuery = " INSERT INTO users_publications " +
                "VALUES(?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(assignUserToPublicationQuery)) {

            statement.setInt(1, userId);
            statement.setInt(2, publicationId);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
    }

    public void deletePublicationsTopics(int publicationId) {
        String deletePublicationsTopicsQuery = "DELETE FROM topics_publications " +
                "WHERE publication_id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(deletePublicationsTopicsQuery)) {

            statement.setInt(1, publicationId);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
    }

    public void deleteUserPublication(int userId, int publicationId) {
        String assignUserToPublicationQuery = " DELETE FROM users_publications WHERE user_id=? AND publication_id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(assignUserToPublicationQuery)) {

            statement.setInt(1, userId);
            statement.setInt(2, publicationId);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
    }


    @Override
    public Publication update(Publication publication) {
        String updatePublicationQuery = "UPDATE publications " +
                "SET title_ua=?, price=?, title_en=?, description_ua=?, description_en=? " +
                "WHERE id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(updatePublicationQuery)) {

            statement.setString(1, publication.getTitleUa());
            statement.setFloat(2, publication.getPrice());
            statement.setString(3, publication.getTitleEn());
            statement.setString(4, publication.getDescriptionUa());
            statement.setString(5, publication.getDescriptionEn());
            statement.setInt(6, publication.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return publication;
    }


    @Override
    public Publication get(int id) {
        String getPublicationQuery = "SELECT * FROM publications WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(getPublicationQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            ModelSetter modelSetter = new ModelSetter(resultSet);

            if (resultSet.next()) {
                return modelSetter.buildPublication(true);
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return null;
    }

    @Override
    public void delete(int id) {

        deletePublicationsTopics(id);
        String deletePublicationUsersQuery = "DELETE FROM users_publications WHERE publication_id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(deletePublicationUsersQuery)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }

        String deletePublicationQuery = "DELETE FROM publications WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(deletePublicationQuery)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }

    }

    @Override
    public List<Publication> listAll() {
        String selectAllPublicationsQuery = "SELECT * FROM publications";
        List<Publication> publicationList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllPublicationsQuery)) {
            ResultSet resultSet = statement.executeQuery();
            modelSetter = new ModelSetter(resultSet);
            while (resultSet.next()) {
                publicationList.add(modelSetter.buildPublication(true));
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return publicationList;
    }


    public List<Publication> getPublicationsOfUser(int id) {
        String selectPublicationsQuery = "SELECT id, title_ua, price, title_en, description_ua, description_en " +
                "FROM users_publications " +
                "INNER JOIN publications ON users_publications.publication_id = publications.id " +
                "WHERE user_id = ?";
        List<Publication> publicationsList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectPublicationsQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            modelSetter = new ModelSetter(resultSet);
            while (resultSet.next()) {
                publicationsList.add(modelSetter.buildPublication(false));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return publicationsList;
    }


    public List<Publication> getPublicationsOfTopic(int id) {
        String selectPublicationsQuery = "SELECT id, title_ua, price, title_en, description_ua, description_en " +
                "FROM topics_publications " +
                "INNER JOIN publications ON topics_publications.publication_id = publications.id " +
                "WHERE topic_id = ?";

        List<Publication> publicationsList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectPublicationsQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            modelSetter = new ModelSetter(resultSet);
            while (resultSet.next()) {
                publicationsList.add(modelSetter.buildPublication(false));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return publicationsList;
    }

    public List<Publication> getPublicationsByCustomQuery(String query) {
        List<Publication> publicationsList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();

             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            modelSetter = new ModelSetter(resultSet);
            while (resultSet.next()) {
                publicationsList.add(modelSetter.buildPublication(false));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return publicationsList;
    }
}
