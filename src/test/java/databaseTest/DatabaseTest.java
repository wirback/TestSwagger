package databaseTest;

import org.junit.jupiter.api.*;
import ru.dmitriiladnov.model.Pet;
import ru.dmitriiladnov.service.PetService;
import ru.dmitriiladnov.service.PetServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Тестирование базы данных")
final class DatabaseTest {
    private static final PetService service = new PetServiceImpl();
    private static Pet petExpected;
    private static Long generatedId;


    @BeforeAll
    static void prepare() {
        service.createPetTable();
    }

    @Test
    @Order(1)
    @DisplayName("Запись данных в БД")
    void writingDataToDatabaseTest() {
        petExpected = new Pet(0L, "Kitty-Kat", "cats");

        assertDoesNotThrow(() -> {
            generatedId = service.createPet(petExpected);
        });
    }

    @Test
    @Order(2)
    @DisplayName("Чтение данных из БД")
    void readingDataFromDatabase() {
        Pet petActual = service.getPetById(generatedId);

        assertEquals(petExpected.getName(), petActual.getName());
        assertEquals(petExpected.getCategory(), petActual.getCategory());
    }

    @Test
    @Order(3)
    @DisplayName("Удаление данных из БД")
    void deletingDataFromDatabase() {
        service.deletePetById(generatedId);
        assertNull(service.getPetById(generatedId).getId());
    }

    @AfterAll
    static void ending() {
        service.dropPetTable();
    }
}
