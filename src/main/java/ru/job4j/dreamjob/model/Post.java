package ru.job4j.dreamjob.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Модель данных, описывает вакансию.
 */
public class Post implements Serializable {
    private int id;
    private String name;
    private String description;
    private String created;
    private boolean visible;
    private City city;

    public Post() {
    }

    public Post(int id, String name, String description, String created, boolean visible, City city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.visible = visible;
        this.city = city;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return getId() == post.getId() && isVisible() == post.isVisible()
                && Objects.equals(getName(), post.getName())
                && Objects.equals(getDescription(), post.getDescription())
                && Objects.equals(getCreated(), post.getCreated())
                && Objects.equals(getCity(), post.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getCreated(), isVisible(), getCity());
    }
}
