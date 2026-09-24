package mx.uv.fei.demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
public class SaludarControlador {
    String nombre;
    @GetMapping("/getSaludo")
    public String saludar() {
        return "Hola, mundo!"+nombre;  }

    @GetMapping("/getDespedida")
    public String adios() {
        return "Adiós, mundo!";
    }

    @PostMapping("/postNombre")
    public void nombre() {
        nombre = "Roa";
    }
}