package Logica;

import java.util.*;
import java.text.Normalizer;

public class AnalizadorBoW {
    private static final List<String> STOP_WORDS = Arrays.asList(
            "el", "la", "los", "las", "un", "una", "unos", "unas", "y", "o", "pero",
            "si", "no", "en", "con", "por", "para", "de", "del", "al", "a", "ante",
            "bajo", "cabe", "contra", "desde", "entre", "hacia", "hasta", "sobre",
            "tras", "que", "quien", "cuyo", "donde", "cuando", "como", "este", "esta",
            "estos", "estas", "ese", "esa", "esos", "esas", "aquel", "aquella",
            "aquellos", "aquellas", "mi", "tu", "su", "nuestro", "vuestro"
    );

    /**
     * Preprocesa el texto para análisis
     * @param texto Texto a procesar
     * @return Array de palabras procesadas
     */
    public static String[] preprocesarTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return new String[0];
        }

        // Convertir a minúsculas y normalizar
        texto = texto.toLowerCase();
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        // Eliminar caracteres especiales y números
        texto = texto.replaceAll("[^a-z\\s]", " ");

        // Eliminar espacios múltiples
        texto = texto.replaceAll("\\s+", " ").trim();

        // Tokenización y eliminación de stopwords
        return Arrays.stream(texto.split("\\s+"))
                .filter(palabra -> !STOP_WORDS.contains(palabra))
                .filter(palabra -> palabra.length() > 2) // Eliminar palabras muy cortas
                .toArray(String[]::new);
    }

    /**
     * Vectoriza las palabras y cuenta sus frecuencias
     * @param palabras Array de palabras a vectorizar
     * @return Mapa de palabras y sus frecuencias
     */
    public static Map<String, Integer> vectorizar(String[] palabras) {
        if (palabras == null) {
            return new HashMap<>();
        }

        Map<String, Integer> vector = new HashMap<>();
        for (String palabra : palabras) {
            vector.merge(palabra, 1, Integer::sum);
        }
        return vector;
    }

    /**
     * Calcula los puntajes emocionales normalizados
     * @param vector Vector de palabras y frecuencias
     * @param diccionarioEmocional Diccionario de emociones
     * @return Mapa de emociones y sus puntajes normalizados
     */
    public static Map<String, Double> calcularPuntajesEmocionales(
            Map<String, Integer> vector,
            Map<String, String> diccionarioEmocional) {

        Map<String, Double> puntajes = new HashMap<>();
        double totalPalabras = 0;

        // Calcular frecuencias de emociones
        for (Map.Entry<String, Integer> entrada : vector.entrySet()) {
            String palabra = entrada.getKey();
            int frecuencia = entrada.getValue();

            if (diccionarioEmocional.containsKey(palabra)) {
                String emocion = diccionarioEmocional.get(palabra);
                puntajes.merge(emocion, (double)frecuencia, Double::sum);
                totalPalabras += frecuencia;
            }
        }

        // Normalizar puntajes
        if (totalPalabras > 0) {
            double finalTotalPalabras = totalPalabras;
            puntajes.replaceAll((k, v) -> v / finalTotalPalabras);
        }

        return puntajes;
    }

    /**
     * Calcula los puntajes técnicos normalizados
     * @param vector Vector de palabras y frecuencias
     * @param diccionarioTecnico Diccionario técnico
     * @return Mapa de categorías técnicas y sus puntajes normalizados
     */
    public static Map<String, Double> calcularPuntajesTecnicos(
            Map<String, Integer> vector,
            Map<String, String> diccionarioTecnico) {

        Map<String, Double> puntajes = new HashMap<>();
        double totalPalabras = 0;

        // Calcular frecuencias de categorías técnicas
        for (Map.Entry<String, Integer> entrada : vector.entrySet()) {
            String palabra = entrada.getKey();
            int frecuencia = entrada.getValue();

            if (diccionarioTecnico.containsKey(palabra)) {
                String categoria = diccionarioTecnico.get(palabra);
                puntajes.merge(categoria, (double)frecuencia, Double::sum);
                totalPalabras += frecuencia;
            }
        }

        // Normalizar puntajes
        if (totalPalabras > 0) {
            double finalTotalPalabras = totalPalabras;
            puntajes.replaceAll((k, v) -> v / finalTotalPalabras);
        }

        return puntajes;
    }

    /**
     * Obtiene las palabras más frecuentes del texto
     * @param vector Vector de palabras y frecuencias
     * @param limite Número máximo de palabras a retornar
     * @return Lista de palabras más frecuentes con sus frecuencias
     */
    public static List<Map.Entry<String, Integer>> obtenerPalabrasFrecuentes(
            Map<String, Integer> vector,
            int limite) {

        return vector.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(limite)
                .toList();
    }

    /**
     * Calcula la similitud coseno entre dos vectores de palabras
     * @param vector1 Primer vector de palabras
     * @param vector2 Segundo vector de palabras
     * @return Valor de similitud entre 0 y 1
     */
    public static double calcularSimilitudCoseno(
            Map<String, Integer> vector1,
            Map<String, Integer> vector2) {

        double productoEscalar = 0.0;
        double norma1 = 0.0;
        double norma2 = 0.0;

        // Calcular producto escalar y normas
        for (Map.Entry<String, Integer> entrada : vector1.entrySet()) {
            String palabra = entrada.getKey();
            int freq1 = entrada.getValue();
            int freq2 = vector2.getOrDefault(palabra, 0);

            productoEscalar += freq1 * freq2;
            norma1 += freq1 * freq1;
        }

        for (int freq : vector2.values()) {
            norma2 += freq * freq;
        }

        // Calcular similitud coseno
        if (norma1 == 0 || norma2 == 0) {
            return 0.0;
        }

        return productoEscalar / (Math.sqrt(norma1) * Math.sqrt(norma2));
    }
}