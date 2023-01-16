package com.epam.periodicals.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private int id;
    private List<Publication> publications;
    private String name;
    private String password;
    private String login;
    private String surname;
    private float account;

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public float getAccount() {
        return account;
    }

    public void setAccount(float account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (publications == null && o == null) {
            return true;
        }
        if (publications == null && o != null) {
            return  false;
        }
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Float.compare(user.account, account) == 0 && publications.equals(user.publications) && name.equals(user.name) && password.equals(user.password) && login.equals(user.login) && surname.equals(user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publications, name, password, login, surname, account);
    }



    public User() {
    }

    public boolean isSubscribed(int publicationId){
        boolean subscribed = false;
        for (Publication p: publications) {
            if (p.getId() == publicationId) {
                subscribed = true;
            }
        }
        return  subscribed;

    }

    public void addPublication (Publication publication) {
        publications.add(publication);
    }
    public void deletePublication (int publicationId) {
        publications.removeIf(p -> p.getId() == publicationId);
    }
}
