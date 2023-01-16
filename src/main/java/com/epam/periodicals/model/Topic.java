package com.epam.periodicals.model;

import java.util.List;
import java.util.Objects;

public class Topic {

    private int id;
    private String titleUa;
    private String titleEn;
    private List<Publication> publications;

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

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id == topic.id && titleUa.equals(topic.titleUa) && titleEn.equals(topic.titleEn) && publications.equals(topic.publications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titleUa, titleEn, publications);
    }
}
