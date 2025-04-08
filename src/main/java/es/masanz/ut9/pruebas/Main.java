package es.masanz.ut9.pruebas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.rendering.template.JavalinFreemarker;
import org.jetbrains.annotations.NotNull;

public class Main {

    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinFreemarker());
        }).start(4567);

        app.get("/", Main::saludar);

    }

    private static void saludar(Context ctx) {

        List<Persona> personas = new ArrayList<>();

        HashMap<String, Object> model = new HashMap<String, Object>();
/*
        personas.add(new Persona("Javi", 37, model));
        personas.add(new Persona("Eneko", 18, model));
        personas.add(new Persona("Sara", 18, model));
        personas.add(new Persona("Mamadi", 18, model));
        personas.add(new Persona("Xabi", 18, model));
        personas.add(new Persona("Ruben", 19, model));
*/
        model.put("mensaje", "¡Hola Mundo con FreeMarker!");
        model.put("personas", personas);
        model.put("isAdmin", true);
        if(personas.size() > 5){
            model.put("hacerCaso", false);
        }

        model.put("listadoVacio", new HashMap<String,String>());

        ctx.render("hola.ftl", model);
    }
}