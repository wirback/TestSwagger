package ru.dmitriiladnov.service;

import ru.dmitriiladnov.dao.PetDao;
import ru.dmitriiladnov.dao.PetDaoImpl;
import ru.dmitriiladnov.model.Pet;

public class PetServiceImpl implements PetService {
    private final PetDao petDao = new PetDaoImpl();

    @Override
    public void createPetTable() {
        petDao.createPetTable();
    }

    @Override
    public void dropPetTable() {
        petDao.dropPetTable();
    }

    @Override
    public Long createPet(Pet pet) {
        return petDao.createPet(pet);
    }

    @Override
    public Pet getPetById(Long id) {
        return petDao.getPetById(id);
    }

    @Override
    public void deletePetById(Long id) {
        petDao.deletePetById(id);
    }
}
