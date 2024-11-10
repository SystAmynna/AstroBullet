package main.tools;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
    public FileManager() {
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
            throw new RuntimeException("Erreur lors de la lecture du fichier", e);
        }
    }

    /**
     * Permet de convertir un fichier en JSON
     * @param file Fichier à convertir
     * @return Fichier converti en JSON
     */
    public static JSONObject FileToJSON(File file) {
        return new JSONObject(file);
    }

    /**
     * Permet de convertir un fichier en JSON
     * @param path Chemin du fichier à convertir
     * @return Fichier converti en JSON
     */
    public static JSONObject FileToJSON(String path) {
        return new JSONObject(getFile(path));
    }

    // TODO : Sauvegarder le fichier JSON
    // TODO : Se renseigner sur un moyen de sauvegarder des fichier au sein de l'archive JAR



}
