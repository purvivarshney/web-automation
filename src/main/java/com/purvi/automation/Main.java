package com.purvi.automation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.purvi.automation.model.CredentialData;
import com.purvi.automation.model.LocatorData;
import com.purvi.automation.model.WebsiteData;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        JsonReader reader = new JsonReader();

        String credentialsPath = getResourceFilePath("credentials.json");
        String locatorsPath = getResourceFilePath("locators.json");
        String websitePath = getResourceFilePath("website.json");
        List<CredentialData> credentials = reader.readJsonFile(credentialsPath, CredentialData.class);
        List<LocatorData> locators = reader.readJsonFile(locatorsPath, LocatorData.class);
        List<WebsiteData> websites = reader.readJsonFile(websitePath, WebsiteData.class);
        Map<String, CredentialData> credentialsMap = new HashMap<>();
        for (CredentialData jsonNode : credentials) {
            String id = jsonNode.id();
            credentialsMap.put(id, jsonNode);
        }

        Map<String, LocatorData> locatorsMap = new HashMap<>();
        for (LocatorData jsonNode : locators) {
            String id = jsonNode.id();
            locatorsMap.put(id, jsonNode);
        }

        Map<String, WebsiteData> websitesMap = new HashMap<>();
        for (WebsiteData jsonNode : websites) {
            String id = jsonNode.id();
            websitesMap.put(id, jsonNode);
        }
        WebsiteOpener websiteOpener = new WebsiteOpener();
        websiteOpener.open(credentialsMap, locatorsMap, websitesMap);
    }

    public static String getResourceFilePath(String resourceName) {
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resourceUrl = classLoader.getResource(resourceName);

        if (resourceUrl != null) {
            return resourceUrl.getPath();
        } else {
            throw new RuntimeException("Resource file not found: " + resourceName);
        }
    }
}
