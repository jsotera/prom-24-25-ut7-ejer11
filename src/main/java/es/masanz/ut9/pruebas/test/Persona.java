package es.masanz.ut9.pruebas.test;

public class Persona implements Cloneable {

    public int edad;
    public Salario salario;

    public Persona(int edad) {
        this.edad = edad;
        this.salario = new Salario(edad);
    }

    @Override
    protected Persona clone() {
        try {
            return (Persona) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
