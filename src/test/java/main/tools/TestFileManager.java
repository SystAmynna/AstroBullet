package main.tools;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TestFileManager {

    @Test
    void testGetFile() {
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
    void testLoadJson() {
        // Test 1: fichier valide
        String path = "/config.json";
        Map<String, Object> map = FileManager.loadJson(path);
        assertNotNull(map, "La map ne doit pas être null");

        // Test 2: fichier invalide
        String invalidPath = "/nonexistent.json";
        Exception exception = assertThrows(NullPointerException.class, () -> FileManager.loadJson(invalidPath));
        assertEquals("Le fichier n'existe pas !", exception.getMessage());
    }

    @Test
    void testMapToFile() {
        // Test 1: fichier valide
        String path = "/config.json";
        Map<String, Object> map = FileManager.loadJson(path);
        File file = FileManager.mapToFile(map, "test12345.json");
        assertNotNull(file, "Le fichier ne doit pas être null");
        assertTrue(file.exists(), "Le fichier doit exister");
        // Suppression du fichier
        if (!file.delete()) {
            fail("Le fichier n'a pas pu être supprimé");
        }
    }

}
