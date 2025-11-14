package Logica;

import java.util.HashMap;
import java.util.Map;

public class DiccionarioEmocional {
    private Map<String, String> palabrasEmociones;

    public DiccionarioEmocional() {
        palabrasEmociones = new HashMap<>();
        // Palabras iniciales de ejemplo
        palabrasEmociones.put("enojado", "Frustracion");
        palabrasEmociones.put("urgente", "Urgencia");
        palabrasEmociones.put("molesto", "Frustracion");
        palabrasEmociones.put("feliz", "Positivo");
    }

    public void agregarPalabra(String palabra, String emocion) {
        palabrasEmociones.put(palabra, emocion);
    }

    public void eliminarPalabra(String palabra) {
        palabrasEmociones.remove(palabra);
    }

    public Map<String, String> analizarEmociones(Map<String, Integer> vectorPalabras) {
        Map<String, String> emocionesEncontradas = new HashMap<>();

        for (Map.Entry<String, Integer> entrada : vectorPalabras.entrySet()) {
            String palabra = entrada.getKey();
            if (palabrasEmociones.containsKey(palabra)) {
                emocionesEncontradas.put(palabra, palabrasEmociones.get(palabra));
            }
        }

        return emocionesEncontradas;
    }
}