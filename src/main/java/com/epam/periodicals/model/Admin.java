package com.epam.periodicals.model;

import java.util.Objects;

public class Admin {
    private int id;
    private String name;
    private String password;
    private String login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return id == admin.id && name.equals(admin.name) && password.equals(admin.password) && login.equals(admin.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, login);
    }
}
