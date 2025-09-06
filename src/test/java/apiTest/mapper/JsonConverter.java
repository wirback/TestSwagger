package apiTest.mapper;

import apiTest.model.Entity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class JsonConverter {

    public static <T extends Entity> T jsonToObject(File data, Class<T> obj) {
        try {
            return new ObjectMapper().readValue(data, obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Entity> T jsonToObject(String data, Class<T> obj) {
        try {
            return new ObjectMapper().readValue(data, obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Entity> String objectToJson(Class<T> obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
