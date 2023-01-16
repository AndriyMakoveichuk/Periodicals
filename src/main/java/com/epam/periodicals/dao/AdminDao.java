package com.epam.periodicals.dao;

import com.epam.periodicals.model.Admin;
import com.epam.periodicals.model.Topic;
import com.epam.periodicals.model.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao implements Dao<Admin>{

    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource;
    private ModelSetter modelSetter;
    private static AdminDao instance;

    private AdminDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private AdminDao() {
        this.dataSource = DatabaseUtil.getDataSource();
    }

    public static AdminDao getInstance() {
        if (instance == null) {
            instance = new AdminDao();
        }
        return instance;
    }

    public static AdminDao getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new AdminDao(dataSource);
        }
        return instance;
    }

    @Override
    public Admin create(Admin admin) {
        String insertAdminQuery = "INSERT INTO admins VALUES(default, ?,? , ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertAdminQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, admin.getLogin());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getName());

            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                admin.setId(generatedKey.getInt(1));
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return admin;
    }

    @Override
    public Admin update(Admin admin) {
        String updateAdminsQuery = "UPDATE admins " +
                "SET login=?, pass=?, name=? " +
                "WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateAdminsQuery)) {

            statement.setString(1, admin.getLogin());
            statement.setString(2, admin.getPassword());
            statement.setString(3, admin.getName());
            statement.setInt(4, admin.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return admin;
    }

    @Override
    public Admin get(int id) {
        String getAdminQuery = "SELECT * FROM admins WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(getAdminQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            ModelSetter modelSetter = new ModelSetter(resultSet);

            if (resultSet.next()) {
                return modelSetter.buildAdmin();
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String deleteAdminQuery = "DELETE FROM admins WHERE id=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteAdminQuery)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }

    }

    @Override
    public List<Admin> listAll() {
        String selectAllAdminsQuery = "SELECT * FROM admins";
        List<Admin> adminsList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllAdminsQuery)) {
            ResultSet resultSet = statement.executeQuery();
            modelSetter = new ModelSetter(resultSet);
            while (resultSet.next()) {
                adminsList.add(modelSetter.buildAdmin());
            }

        }
        catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return adminsList;
    }

    public Admin get(String login) {

        String getUserQuery = "SELECT * FROM admins WHERE login=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(getUserQuery)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            ModelSetter modelSetter = new ModelSetter(resultSet);

            if (resultSet.next()) {
                return modelSetter.buildAdmin();
            }

        }
        catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return null;
    }
}
