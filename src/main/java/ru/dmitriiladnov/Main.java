package ru.dmitriiladnov;

import ru.dmitriiladnov.service.PetService;
import ru.dmitriiladnov.service.PetServiceImpl;

public class Main {
    public static void main(String[] args) {
        PetService service = new PetServiceImpl();

        service.createPetTable();

        service.dropPetTable();
    }
}