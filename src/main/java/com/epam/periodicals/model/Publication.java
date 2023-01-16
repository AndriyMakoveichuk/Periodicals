package com.epam.periodicals.model;

import java.util.List;
import java.util.Objects;

public class Publication {

    private int id;
    private String titleUa;
    private String titleEn;
    private String descriptionEn;
    private String descriptionUa;
    private float price;
    private List<User> users;
    private List<Topic> topics;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleUa() {
        return titleUa;
    }

    public void setTitleUa(String titleUa) {
        this.titleUa = titleUa;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionUa() {
        return descriptionUa;
    }

    public void setDescriptionUa(String descriptionUa) {
        this.descriptionUa = descriptionUa;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return id == that.id && Float.compare(that.price, price) == 0 && titleUa.equals(that.titleUa) && titleEn.equals(that.titleEn) && descriptionEn.equals(that.descriptionEn) && descriptionUa.equals(that.descriptionUa) && users.equals(that.users) && topics.equals(that.topics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titleUa, titleEn, descriptionEn, descriptionUa, price, users, topics);
    }

}
