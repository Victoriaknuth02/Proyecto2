/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_vicky;

/**
 *
 * @author Vicky
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * La clase {@code CargaDeJSON} se encarga de cargar un árbol genealógico
 * desde un archivo JSON y de insertar los datos en una estructura de árbol
 * genealógico y una tabla hash.
 */
public class CargaDeJSON {

    private ArbolGenealogico arbolGenealogico;
    private TablaHash tablaHash;

    /**
     * Crea una instancia de {@code CargaDeJSON} inicializando un nuevo
     * árbol genealógico y una tabla hash.
     */
    public CargaDeJSON() {
        arbolGenealogico = new ArbolGenealogico();
        tablaHash = new TablaHash(100); // Tamaño de la tabla hash
    }

    /**
     * Lee un archivo JSON desde la ruta especificada y procesa su contenido.
     *
     * @param ruta la ruta del archivo JSON a leer.
     */
    public void leerArchivo(String ruta) {
        String jsonContent = readJsonFile(ruta);
        if (jsonContent != null) {
            JsonElement jsonElement = JsonParser.parseString(jsonContent);
            procesarJson(jsonElement);
        }
    }

    /**
     * Lee el contenido de un archivo JSON y lo devuelve como una cadena.
     *
     * @param filePath la ruta del archivo JSON.
     * @return el contenido del archivo JSON como cadena, o {@code null} si hubo un error.
     */
    public static String readJsonFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            return jsonContent.toString();
        } catch (IOException e) {
            System.out.println("Error leyendo archivo JSON: " + e.getMessage());
            return null;
        }
    }

    /**
     * Procesa un elemento JSON y lo inserta en el árbol genealógico y la tabla hash.
     *
     * @param jsonElement el elemento JSON a procesar.
     */
    public void procesarJson(JsonElement jsonElement) {
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            for (String casa : jsonObject.keySet()) {
                JsonArray personajes = jsonObject.getAsJsonArray(casa);
                for (JsonElement personajeElement : personajes) {
                    JsonObject personaje = personajeElement.getAsJsonObject();

                    // Asumiendo que el nombre completo es la clave del objeto personaje
                    String nombreCompleto = personaje.keySet().iterator().next();

                    // Obtener el array de detalles del personaje
                    JsonArray detallesArray = personaje.getAsJsonArray(nombreCompleto);

                    // Crear un JsonObject para almacenar los detalles
                    JsonObject detalles = new JsonObject();

                    // Iterar sobre el array de detalles para llenar el JsonObject
                    for (JsonElement detalleElement : detallesArray) {
                        JsonObject detalle = detalleElement.getAsJsonObject();
                        for (String key : detalle.keySet()) {
                            detalles.add(key, detalle.get(key));
                        }
                    }
                    
                    String padre = "Desconocido";
                    String madre = "Desconocido";
                    for (JsonElement elementoDetalle : detallesArray) {
                        JsonObject detalleJson = elementoDetalle.getAsJsonObject();
                        for (String clave : detalleJson.keySet()) {
                            // Solo almacenar el primer valor de "Born to"
                            if (clave.equals("Born to") && padre.equals("Desconocido")) {
                                padre = detalleJson.get(clave).getAsString();
                            } else if (clave.equals("Born to") && madre.equals("Desconocido")) {
                                madre = detalleJson.get(clave).getAsString();
                            }
                            detalles.add(clave, detalleJson.get(clave));
                        }
                    }

                    // Atributos obligatorios
                    String numeral = "Desconocido";
                    String colorOjos = "Desconocido";
                    String colorCabello = "Desconocido";

                    // Validaciones para atributos obligatorios
                    if (detalles.has("Of his name")) {
                        JsonElement numeralElement = detalles.get("Of his name");
                        if (numeralElement.isJsonArray()) {
                            JsonArray arrayNumeral = numeralElement.getAsJsonArray();
                            if (arrayNumeral.size() > 0) {
                                numeral = arrayNumeral.get(0).getAsString();
                            }
                        } else if (numeralElement.isJsonPrimitive()) {
                            numeral = numeralElement.getAsString();
                        }
                    }

                    if (detalles.has("Of eyes")) {
                        JsonElement colorOjosElement = detalles.get("Of eyes");
                        if (colorOjosElement.isJsonArray()) {
                            JsonArray arrayColorOjos = colorOjosElement.getAsJsonArray();
                            if (arrayColorOjos.size() > 0) {
                                colorOjos = arrayColorOjos.get(0).getAsString();
                            }
                        } else if (colorOjosElement.isJsonPrimitive()) {
                            colorOjos = colorOjosElement.getAsString();
                        }
                    }

                    if (detalles.has("Of hair")) {
                        JsonElement colorCabelloElement = detalles.get("Of hair");
                        if (colorCabelloElement.isJsonArray()) {
                            JsonArray arrayColorCabello = colorCabelloElement.getAsJsonArray();
                            if (arrayColorCabello.size() > 0) {
                                colorCabello = arrayColorCabello.get(0).getAsString();
                            }
                        } else if (colorCabelloElement.isJsonPrimitive()) {
                            colorCabello = colorCabelloElement.getAsString();
                        }
                    }

                    // Atributos opcionales con validación
                    String mote = "Desconocido";
                    String titulo = "Desconocido";
                    String esposa = "Desconocido";
                    String notas = "Desconocido";
                    String destino = "Desconocido";

                    // Validación para "Known throughout as"
                    if (detalles.has("Known throughout as")) {
                        JsonElement moteElement = detalles.get("Known throughout as");
                        if (moteElement.isJsonArray()) {
                            JsonArray arrayMote = moteElement.getAsJsonArray();
                            if (arrayMote.size() > 0) {
                                mote = arrayMote.get(0).getAsString();
                            }
                        } else if (moteElement.isJsonPrimitive()) {
                            mote = moteElement.getAsString();
                        }
                    }

                    // Validación para "Held title"
                    if (detalles.has("Held title")) {
                        JsonElement tituloElement = detalles.get("Held title");
                        if (tituloElement.isJsonArray()) {
                            JsonArray arrayTitulo = tituloElement.getAsJsonArray();
                            if (arrayTitulo.size() > 0) {
                                titulo = arrayTitulo.get(0).getAsString();
                            }
                        } else if (tituloElement.isJsonPrimitive()) {
                            titulo = tituloElement.getAsString();
                        }
                    }

                    // Validación para "Wed to"
                    if (detalles.has("Wed to")) {
                        JsonElement esposaElement = detalles.get("Wed to");
                        if (esposaElement.isJsonArray()) {
                            JsonArray arrayEsposa = esposaElement.getAsJsonArray();
                            if (arrayEsposa.size() > 0) {
                                esposa = arrayEsposa.get(0).getAsString();
                            }
                        } else if (esposaElement.isJsonPrimitive()) {
                            esposa = esposaElement.getAsString();
                        }
                    }

                    // Validación para "Notes"
                    if (detalles.has("Notes")) {
                        JsonElement notasElement = detalles.get("Notes");
                        if (notasElement.isJsonArray()) {
                            JsonArray arrayNotas = notasElement.getAsJsonArray();
                            if (arrayNotas.size() > 0) {
                                notas = arrayNotas.get(0).getAsString();
                            }
                        } else if (notasElement.isJsonPrimitive()) {
                            notas = notasElement.getAsString();
                        }
                    }

                    // Validación para "Fate"
                    if (detalles.has("Fate")) {
                        JsonElement destinoElement = detalles.get("Fate");
                        if (destinoElement.isJsonArray()) {
                            JsonArray arrayDestino = destinoElement.getAsJsonArray();
                            if (arrayDestino.size() > 0) {
                                destino = arrayDestino.get(0).getAsString();
                            }
                        } else if (destinoElement.isJsonPrimitive()) {
                            destino = destinoElement.getAsString();
                        }
                    }

                    // Inserción en estructuras de datos
                    Nodo insertado = getArbolGenealogico().insertar(nombreCompleto, numeral, colorOjos, colorCabello, titulo, mote, esposa, notas, destino, padre, madre);
                    if (insertado.getMote() != null) {
                        getTablaHash().insertar(insertado, 2);
                    }
                }
            }
        } else {
            System.out.println("El contenido no es un objeto JSON válido.");
        }
    }

    /**
     * @return the arbol Genealogico
     */
    public ArbolGenealogico getArbolGenealogico() {
        return arbolGenealogico;
    }

    /**
     * @param arbolGenealogico the arbolGenealogico to set
     */
    public void setArbolGenealogico(ArbolGenealogico arbolGenealogico) {
        this.arbolGenealogico = arbolGenealogico;
    }

    /**
     * @return the tablaHash
     */
    public TablaHash getTablaHash() {
        return tablaHash;
    }

    /**
     * @param tablaHash the tablaHash to set
     */
    public void setTablaHash(TablaHash tablaHash) {
        this.tablaHash = tablaHash;
    }
}