package main.tools;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Classe de gestion des fichiers
 * @version 1.0
 * @author SystAmynna
 */
public class FileManager {

    /**
     * Constructeur de la classe
     * NE DOIT PAS ETRE UTILISE
     */
    private FileManager() {
        throw new IllegalStateException("FileManager ne doit pas être instancié !");
    }

    /**
     * Permet de récupérer un fichier
     * @param path Chemin du fichier
     * @return Fichier
     */
    public static File getFile(String path) {
        // Vérification de la validité du chemin
        Objects.requireNonNull(path, "Le chemin ne doit pas être null");
        // Récupération du fichier
        InputStream inputStream = FileManager.class.getResourceAsStream(path);
        // Vérification de l'existence du fichier
        if (inputStream == null) throw new NullPointerException("Le fichier n'existe pas !");
        // Création du fichier temporaire
        try {
            // Copie du fichier dans le fichier temporaire
            File tempFile = File.createTempFile("temp", null);
            // Suppression du fichier temporaire à la fin du programme
            tempFile.deleteOnExit();
            // Copie du fichier
            Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            // Retour du fichier
            return tempFile;
        } catch (IOException e) {
            // Erreur lors de la lecture du fichier
            throw new IllegalArgumentException("Erreur lors de la lecture du fichier", e);
        }
    }

    /**
     * Permet de convertir un fichier en JSON
     * @param file Fichier à convertir
     * @return une hashmap contenant les données du fichier
     */
    public static Map<String, Object> loadJson(File file) {
        JSONObject jsonObject = new JSONObject(file);
        Map<String, Object> map = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            map.put(key, jsonObject.get(key));
        }
        return map;
    }

    /**
     * Permet de convertir un fichier en JSON
     * @param path Chemin du fichier à convertir
     * @return Fichier converti en JSON
     */
    public static Map<String, Object> loadJson(String path) {
        return loadJson(getFile(path));
    }

    /**
     * Permet de convertir un JSON en fichier. Enregistre le fichier au chemin spécifié, et écrase le fichier s'il existe déjà.
     *
     * @param jsonObject JSON à convertir en fichier
     * @param path Chemin du fichier
     * @return Fichier créé
     */
    public static File jsonToFile(JSONObject jsonObject, String path) {
        // Création du fichier
        File file = new File(path);
        try {
            // Ecriture du fichier avec le contenu JSON
            Files.write(file.toPath(), jsonObject.toString(4).getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException("Erreur lors de l'écriture du fichier", e);
        }
        // Retour du fichier
        return file;
    }

    /**
     * Permet de convertir une map en fichier. Enregistre le fichier au chemin spécifié, et écrase le fichier s'il existe déjà.
     * @param map Map à convertir en fichier
     * @param path Chemin du fichier
     * @return Fichier créé
     */
    public static File mapToFile(Map<String, Object> map, String path) {
        return jsonToFile(new JSONObject(map), path);
    }

}
