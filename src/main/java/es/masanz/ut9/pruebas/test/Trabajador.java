package es.masanz.ut9.pruebas.test;

public class Trabajador  extends Persona {

    public int edad;

    public Trabajador(int edad) {
        super(edad);
        this.edad = this.edad + 10;
    }
}
