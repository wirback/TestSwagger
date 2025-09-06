package apiTest;

import apiTest.handlers.FailedTestHandler;
import apiTest.mapper.JsonConverter;
import ru.dmitriiladnov.model.Pet;
import apiTest.specification.Specification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("pet")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(FailedTestHandler.class)
@DisplayName("Тестирование сущности Pet")
final class PetTest {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PET_URL = "/pet";
    private static final String PET_ID_URL = "/pet/{id}";
    private static Pet petExpected;
    private Pet petActual;
    private static final String EXPECTED_NAME = "Kit-Kat";

    @BeforeAll
    static void prepare() {
        Specification.initSpec(Specification.requestSpec(BASE_URL));
        petExpected = JsonConverter.jsonToObject(
                new File("src/test/resources/pet_body_create.json"), Pet.class);
    }

    @Test
    @Order(1)
    @Tag("added")
    @DisplayName("Создать нового питомца")
    void addedPetTest() {
        given()
                .body(petExpected)
                .when()
                .post(PET_URL)
                .then().log().status().and().log().body();
    }

    @Test
    @Order(2)
    @Tag("added")
    @DisplayName("Проверить, что питомец успешно создан")
    void checkSuccessfulCreationPetTest() {
        petActual = given()
                .when()
                .get(PET_ID_URL, petExpected.getId())
                .then().log().status().and().log().body()
                .extract().as(Pet.class);

        assertEquals(petExpected.getId(), petActual.getId());
    }

    @Test
    @Order(3)
    @Tag("update")
    @DisplayName("Изменить данные питомца")
    void updateDataPetTest() {
        petExpected.setName(EXPECTED_NAME);
        given()
                .body(petExpected)
                .when()
                .put(PET_URL)
                .then().log().status().and().log().body();
    }

//    @RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
    @Test
    @Order(4)
    @Tag("update")
    @DisplayName("Проверить, что изменения успешно применены")
    void checkSuccessfulUpdateDataPetTest() {
        petActual = given()
                .when()
                .get(PET_ID_URL, petExpected.getId())
                .then().log().status().and().log().body()
                .extract().as(Pet.class);

        assertEquals(EXPECTED_NAME, petActual.getName());
    }

    @Test
    @Order(5)
    @Tag("delete")
    @DisplayName("Удалить питомца")
    void deletePetTest() {
        given()
                .when()
                .delete(PET_ID_URL, petExpected.getId())
                .then().log().status();
    }

//    @RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
    @Test
    @Order(6)
    @Tag("delete")
    @DisplayName("Проверить, что питомец удален")
    void checkSuccessfulDeletePetTest() {
        given()
                .expect().statusCode(HttpStatus.SC_NOT_FOUND)
                .when()
                .get(PET_ID_URL, petExpected.getId())
                .then().log().status().and().log().body();
    }
}
