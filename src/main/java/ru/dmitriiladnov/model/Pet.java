package ru.dmitriiladnov.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
public class Pet {
    @Id
    @Column(name = "pet_id")
    private Long id;
    @Column(name = "pet_name")
    private String name;
    @Column(name = "pet_category")
    private String category;

    public Pet() {
    }

    public Pet(Long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Pet{id: %d, name: %s, category: %s}", id, name, category);
    }
}
