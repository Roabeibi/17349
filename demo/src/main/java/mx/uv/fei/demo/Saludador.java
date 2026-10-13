package mx.uv.fei.demo;

public class Saludador {
    private String contenido;

    // Se corrigió "PUBLIC" a "public" en minúsculas
    public Saludador(String contenido) {
        this.contenido = contenido;
    }

    // Se cambió "class" por el tipo de retorno "String"
    public String getContenido() {
        return contenido;
    }
}
