package Logica;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class AnalizadorBoW {
    private static final List<String> STOP_WORDS = Arrays.asList("el", "la", "los", "las", "un", "una", "unos", "unas", "y", "o", "pero", "si", "no", "en", "con", "por", "para");

    public static String[] preprocesarTexto(String texto) {
        // Normalización
        texto = texto.toLowerCase()
                .replaceAll("[áàäâã]", "a")
                .replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i")
                .replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u")
                .replaceAll("[^a-z0-9 ]", "");

        // Tokenización y eliminación de stopwords
        return Arrays.stream(texto.split("\\s+"))
                .filter(palabra -> !STOP_WORDS.contains(palabra))
                .toArray(String[]::new);
    }

    public static Map<String, Integer> vectorizar(String[] palabras) {
        Map<String, Integer> vector = new HashMap<>();
        for (String palabra : palabras) {
            vector.put(palabra, vector.getOrDefault(palabra, 0) + 1);
        }
        return vector;
    }
}