package main.tools;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TestFileManager {

    @Test
    public void testGetFile() {
        // Test 1: fichier valide
        String path = "/config.json";
        File file = FileManager.getFile(path);
        assertNotNull(file, "Le fichier ne doit pas être null");
        assertTrue(file.exists(), "Le fichier doit exister");

        // Test 2: chemin invalide
        String invalidPath = "/nonexistent.json";
        Exception exception = assertThrows(NullPointerException.class, () -> FileManager.getFile(invalidPath));
        assertEquals("Le fichier n'existe pas !", exception.getMessage());

        // Test 3: chemin null
        Exception exception2 = assertThrows(NullPointerException.class, () -> FileManager.getFile(null));
        assertEquals("Le chemin ne doit pas être null", exception2.getMessage());
    }

    @Test
    public void testConvertFileToJSON() {
        String path = "/config.json";
        // Test 1: entrée fichier
        File file = FileManager.getFile(path);
        JSONObject json = FileManager.FileToJSON(file);
        assertNotNull(json, "Le JSON ne doit pas être null");

        // Test 2: entrée chemin
        JSONObject json2 = FileManager.FileToJSON(path);
        assertNotNull(json2, "Le JSON ne doit pas être null");
    }

}
