package com.epam.periodicals.dao;

import com.epam.periodicals.model.Publication;
import com.epam.periodicals.model.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {

    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource;
    private ModelSetter modelSetter;
    private static UserDao instance;

    private UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private UserDao() {
        this.dataSource = DatabaseUtil.getDataSource();
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public static UserDao getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new UserDao(dataSource);
        }
        return instance;
    }



    @Override
    public User create(User user) {
        String insertUserQuery = "INSERT INTO users VALUES(default, ?,?,?,?,?,default)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setFloat(3, user.getAccount());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getSurname());

            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                user.setId(generatedKey.getInt(1));
                user.setActive(generatedKey.getBoolean(7));
            }

        }
        catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return user;
    }

    @Override
    public User update(User user) {

        String updateUserQuery = "UPDATE users " +
                "SET name=?, password=?, account=?, login=?, surname=?, active=? " +
                "WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateUserQuery)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setFloat(3, user.getAccount());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getSurname());
            statement.setBoolean(6, user.isActive());
            statement.setInt(7, user.getId());
            statement.executeUpdate();

        }
        catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return user;
    }

    @Override
    public User get(int id) {

        String getUserQuery = "SELECT * FROM users WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(getUserQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            ModelSetter modelSetter = new ModelSetter(resultSet);

            if (resultSet.next()) {
                return modelSetter.buildUser(true);
            }

        }
        catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return null;
    }


    public User get(String login) {

        String getUserQuery = "SELECT * FROM users WHERE login=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(getUserQuery)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            ModelSetter modelSetter = new ModelSetter(resultSet);

            if (resultSet.next()) {
                return modelSetter.buildUser(true);
            }

        }
        catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String deleteUserPublicationsQuery = "DELETE FROM users_publications WHERE user_id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteUserPublicationsQuery)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        }
        catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }

        String deleteUserQuery = "DELETE FROM users WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteUserQuery)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        }
        catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
    }

    @Override
    public List<User> listAll() {
        String selectAllUsersQuery = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllUsersQuery)) {
            ResultSet resultSet = statement.executeQuery();
            modelSetter = new ModelSetter(resultSet);
            while (resultSet.next()) {
                userList.add(modelSetter.buildUser(true));
            }

        }
        catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return userList;
    }



    List<User> getUsersOfPublication(int id) {
        String selectUsersQuery = "SELECT id, name, password, account, login, surname, active " +
                "FROM users_publications " +
                "INNER JOIN users ON users_publications.user_id = users.id " +
                "WHERE publication_id = ?";
        List<User> usersList= new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectUsersQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            modelSetter= new ModelSetter(resultSet);
            while (resultSet.next()) {
                usersList.add(modelSetter.buildUser(false));
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return usersList;
    }

}
