package es.masanz.ut9.pruebas.concesionario;

public class Coche {
    private String imagen;
    private String precio;
    private String modelo;
    private String color;

    public Coche(String imagen, String precio, String modelo, String color) {
        this.imagen = imagen;
        this.precio = precio;
        this.modelo = modelo;
        this.color = color;
    }

    public String getImagen() { return imagen; }
    public String getPrecio() { return precio; }
    public String getModelo() { return modelo; }
    public String getColor() { return color; }
}
