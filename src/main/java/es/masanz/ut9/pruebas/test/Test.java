package es.masanz.ut9.pruebas.test;

public class Test {

    public static void main(String[] args) {

        /*
        Programador p = new Programador(10);
        System.out.println(p.edad);
        */

        Persona p1 = new Persona(10);
        Persona p2 = p1.clone();
        p1.salario.sueldo = 10;
        System.out.println(p1.salario.sueldo+p2.salario.sueldo);


    }

}

