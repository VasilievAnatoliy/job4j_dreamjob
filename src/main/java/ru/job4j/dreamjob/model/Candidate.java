package ru.job4j.dreamjob.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Модель данных, описывает кандидата.
 */
public class Candidate {
    private int id;
    private String name;
    private String description;
    private byte[] photo;
    private String created;
    private boolean visible;
    private City city;

    public Candidate() {
    }

    public Candidate(int id, String name, String description, byte[] photo, City city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.created = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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
        Candidate candidate = (Candidate) o;
        return getId() == candidate.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
