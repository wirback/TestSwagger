package ru.dmitriiladnov.service;

import ru.dmitriiladnov.model.Pet;

public interface PetService {
    void createPetTable();
    void dropPetTable();
    Long createPet(Pet pet);
    Pet getPetById(Long id);
    void deletePetById(Long id);
}
