package mx.uv.fei.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class SaludarControlador {
    String nombre;
    
    // CORRECCIÓN 1: Declaramos la variable global, pero NO la instanciamos aquí
    // para evitar el error del constructor vacío.
    Saludador saludador;   

    @GetMapping("/saludos")
    public String saludar() {
        return "Hola, mundo! " + (nombre != null ? nombre : "");  
    }

    @GetMapping("/despedidas")
    public String adios() {
        return "Adiós, mundo!";
    }

    @PostMapping("/nombramientos")
    public void nombre() {
        nombre = "Roa";
    }

    // Actualizar el nombre
    @PutMapping("/nombramientos")
    public void met1() {
        nombre = "Alessandro";
    }

    // Eliminar el nombre  
    @DeleteMapping("/nombramientos")
    public void met2() {
        nombre = null;  
    }       

    @GetMapping("/saludos/{nombre}")
    public Saludador met3(@PathVariable String nombre) {
        System.out.println(nombre);
        return new Saludador(nombre);
    }

    @GetMapping("/saludos2/{nombrex}")
    public Saludador met4(@PathVariable("nombrex") String nombre) {
        System.out.println(nombre);
        return new Saludador(nombre);
    }

    @PostMapping("/crearSaludador")
    public String crearSaludador(@RequestBody Saludador objetoSaludador) {
        // Asignamos el objeto recibido de forma segura a nuestra variable global
        this.saludador = objetoSaludador;
        // CORRECCIÓN 2: Accedemos al contenido real usando el método que creaste en la clase Saludador
        return "Saludador creado con éxito: " + objetoSaludador.getContenido();
    }

    @PostMapping("/obtenerSaludador")
    public Saludador obtenerSaludador(@RequestBody String nombre) {
        return new Saludador(nombre);
    }

    
    @GetMapping("/query1")
public String query1(@RequestParam String nombre,
@RequestParam String paterno,
@RequestParam(defaultValue = "") String materno){
System.out.println(nombre);
return nombre + " " + paterno + " " + materno;}
        

    @GetMapping("/query2")
    public String query2(@RequestParam (defaultValue = "default") String nombre) {
        System.out.println(nombre);
        return nombre;
    }

    @RequestMapping("/")
    public String raiz() {
        return """
        <h1>Menú de opciones</h1>
        <ul>
            <li><a href="/saludos">Saludar básico (/saludos)</a></li>
            <li><a href="/despedidas">Despedirse (/despedidas)</a></li>
            <li><a href="/query2">Probar Parámetro Query por defecto (/query2)</a></li>
            <li><a href="/query2?nombre=Alejandro">Probar Parámetro Query con valor (/query2?nombre=Alejandro)</a></li>
            <li><a href="/saludos/Juan">Saludar con PathVariable Juan (/saludos/Juan)</a></li>
            <li><a href="/saludos2/Maria">Saludar con PathVariable Maria (/saludos2/Maria)</a></li>
            <li><a href="/query1?nombre=Juan&paterno=Perez&materno=Gomez">Probar Parámetro Query con varios valores (/query1?nombre=Juan&paterno=Perez&materno=Gomez)</a></li>
            <li><a href="/query2">Probar Parámetro Query con valor (/query2?nombre=Alejandro)</a></li>
        </ul>
        <p><strong>Nota técnica:</strong> Las rutas asociadas a <code>POST</code>, <code>PUT</code> y <code>DELETE</code> (como <em>/nombramientos</em>, <em>/crearSaludador</em> o <em>/obtenerSaludador</em>) requieren de herramientas externas de pruebas API como Postman o cURL para enviar cuerpos JSON de datos estructurados, debido a que los hipervínculos del navegador estándar ejecutan de forma exclusiva peticiones de tipo HTTP GET.</p>
        """;
    }
}
