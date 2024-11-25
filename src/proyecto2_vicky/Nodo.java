/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_vicky;

/**
 *
 * @author vicky
 */
public class Nodo {

    private String nombre;
    private String numeral;
    private Nodo padre;
    private String madre;
    private String mote;
    private String titulo;
    private String esposa;
    private String ojos;
    private String cabello;
    private Nodo[] hijos;
    private String notas;
    private String destino;

    private Nodo pNext;

    public Nodo(String nombre, String ofHisName, Nodo padre, String ofEyes, String ofHair) {
        this.nombre = nombre;
        this.numeral = ofHisName;
        this.padre = padre;
        this.ojos = ofEyes;
        this.cabello = ofHair;
        this.pNext = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(getNombre() != null ? getNombre() : "N/A").append("\n");
        sb.append("Numeral: ").append(getNumeral() != null ? getNumeral() : "N/A").append("\n");
        sb.append("Mote: ").append(getMote() != null ? getMote() : "N/A").append("\n");
        sb.append("Título: ").append(getTitulo() != null ? getTitulo() : "N/A").append("\n");
        sb.append("Esposa: ").append(getEsposa() != null ? getEsposa() : "N/A").append("\n");
        sb.append("Ojos: ").append(getOjos() != null ? getOjos() : "N/A").append("\n");
        sb.append("Cabello: ").append(getCabello() != null ? getCabello() : "N/A").append("\n");
        sb.append("Notas: ").append(getNotas() != null ? getNotas() : "N/A").append("\n");
        sb.append("Destino: ").append(getDestino() != null ? getDestino() : "N/A").append("\n");

        if (getPadre() != null) {
            sb.append("Padre: ").append(getPadre().getNombre() != null ? getPadre().getNombre() : "N/A").append("\n");
        } else {
            sb.append("Padre: N/A\n");
        }
        
        if (getMadre() != null) {
            sb.append("Madre: ").append(getMadre()).append("\n");
        } else {
            sb.append("Madre: N/A\n");
        }

        if (getHijos() != null && getHijos().length > 0) {
            sb.append("Hijos: ");
            for (Nodo hijo : getHijos()) {
                sb.append(hijo.getNombre() != null ? hijo.getNombre() : "N/A").append(", ");
            }
            sb.setLength(sb.length() - 2); // Eliminar la última coma y espacio
            sb.append("\n");
        } else {
            sb.append("Hijos: N/A\n");
        }

        return sb.toString();
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the numeral
     */
    public String getNumeral() {
        return numeral;
    }

    /**
     * @param numeral the numeral to set
     */
    public void setNumeral(String numeral) {
        this.numeral = numeral;
    }

    /**
     * @return the padre
     */
    public Nodo getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    /**
     * @return the madre
     */
    public String getMadre() {
        return madre;
    }

    /**
     * @param madre the madre to set
     */
    public void setMadre(String madre) {
        this.madre = madre;
    }

    /**
     * @return the mote
     */
    public String getMote() {
        return mote;
    }

    /**
     * @param mote the mote to set
     */
    public void setMote(String mote) {
        this.mote = mote;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the esposa
     */
    public String getEsposa() {
        return esposa;
    }

    /**
     * @param esposa the esposa to set
     */
    public void setEsposa(String esposa) {
        this.esposa = esposa;
    }

    /**
     * @return the ojos
     */
    public String getOjos() {
        return ojos;
    }

    /**
     * @param ojos the ojos to set
     */
    public void setOjos(String ojos) {
        this.ojos = ojos;
    }

    /**
     * @return the cabello
     */
    public String getCabello() {
        return cabello;
    }

    /**
     * @param cabello the cabello to set
     */
    public void setCabello(String cabello) {
        this.cabello = cabello;
    }

    /**
     * @return the hijos
     */
    public Nodo[] getHijos() {
        return hijos;
    }

    /**
     * @param hijos the hijos to set
     */
    public void setHijos(Nodo[] hijos) {
        this.hijos = hijos;
    }

    /**
     * @return the notas
     */
    public String getNotas() {
        return notas;
    }

    /**
     * @param notas the notas to set
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the pNext
     */
    public Nodo getpNext() {
        return pNext;
    }

    /**
     * @param pNext the pNext to set
     */
    public void setpNext(Nodo pNext) {
        this.pNext = pNext;
    }
}
