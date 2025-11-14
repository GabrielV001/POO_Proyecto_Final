package Logica;

import java.util.HashMap;
import java.util.Map;

public class DiccionarioTecnico {
    private Map<String, String> palabrasCategorias;

    public DiccionarioTecnico() {
        palabrasCategorias = new HashMap<>();
        // Palabras iniciales de ejemplo
        palabrasCategorias.put("imprimir", "Impresoras");
        palabrasCategorias.put("papel", "Impresoras");
        palabrasCategorias.put("wifi", "Redes");
        palabrasCategorias.put("internet", "Redes");
    }

    public void agregarPalabra(String palabra, String categoria) {
        palabrasCategorias.put(palabra, categoria);
    }

    public void eliminarPalabra(String palabra) {
        palabrasCategorias.remove(palabra);
    }

    public Map<String, String> analizarCategoriaTecnica(Map<String, Integer> vectorPalabras) {
        Map<String, String> categoriasEncontradas = new HashMap<>();

        for (Map.Entry<String, Integer> entrada : vectorPalabras.entrySet()) {
            String palabra = entrada.getKey();
            if (palabrasCategorias.containsKey(palabra)) {
                categoriasEncontradas.put(palabra, palabrasCategorias.get(palabra));
            }
        }

        return categoriasEncontradas;
    }
}