package com.purvi.automation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.purvi.automation.model.CredentialData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonReader {
    public <T> List<T> readJsonFile(String filepath, Class<T> recordClass) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File(filepath);

            // Read the JSON file and map it to a List<CredentialData>
            return objectMapper.readValue(jsonFile, objectMapper.getTypeFactory().constructCollectionType(List.class, recordClass));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}