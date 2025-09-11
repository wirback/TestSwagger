package ru.dmitriiladnov.dao;

import ru.dmitriiladnov.model.Pet;
import ru.dmitriiladnov.util.ConnectionUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;

public class PetDaoImpl implements PetDao {
    private static final Connection connection = ConnectionUtil.getConnection();

    public PetDaoImpl() {

    }

    @Override
    public void createPetTable() {
        try (PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(
                Files.readString(Path.of("src/main/resources/create_pet_table.sql"))
        )) {
            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropPetTable() {
        try (PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(
                "DROP TABLE IF EXISTS pet_table"
        )) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long createPet(Pet pet) {
        try (PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(
                String.format("INSERT INTO pet_table(pet_name, pet_category) VALUES('%s', '%s')",
                pet.getName(), pet.getCategory()),
                Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                return generatedKey.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Pet getPetById(Long id) {
        try (PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(
                String.format("SELECT pet_id, pet_name, pet_category FROM pet_table WHERE pet_id = %s", id));
             ResultSet result = statement.executeQuery()
        ) {
            Pet pet = new Pet();
            while (result.next()) {
                pet.setId(result.getLong("pet_id"));
                pet.setName(result.getString("pet_name"));
                pet.setCategory(result.getString("pet_category"));
            }
            return pet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePetById(Long id) {
        try (PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(
                String.format("DELETE FROM pet_table WHERE pet_id = %s ", id))
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
