package com.itsqmet.Denuncias.Controladores;

import com.itsqmet.Denuncias.Servicios.AutoridadServicio;
import com.itsqmet.Denuncias.Entidades.Autoridad;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Controller
public class AutoridadControlador {

    @Autowired
    private AutoridadServicio autoridadServicio;

    // Método para mostrar todas las Autoridades
    @GetMapping("/autoridades")
    public String listarAutoridades(@RequestParam(name = "buscarAutoridad", required = false, defaultValue = "") String buscarAutoridades, Model model) {
        List<Autoridad> autoridades = autoridadServicio.mostrarAutoridades();
        model.addAttribute("buscarAutoridad", buscarAutoridades);
        model.addAttribute("autoridades", autoridades);
        return "Vistas/Autoridades";
    }

    // Método para mostrar una Autoridad específica por ID
    @GetMapping("/autoridad/{id}")
    public String mostrarAutoridad(@PathVariable String id, Model model) {
        Optional<Autoridad> autoridad = autoridadServicio.buscarAutoridadPorID(id);
        if (autoridad.isPresent()) {
            model.addAttribute("autoridad", autoridad.get());
            return "Vistas/Autoridades"; // Asegúrate de que este nombre coincida con el archivo HTML
        } else {
            // Manejar el caso en que la autoridad no se encuentra
            return "redirect:/autoridades"; // O una página de error
        }
    }

    // Método para mostrar el formulario de registro de una Autoridad
    @GetMapping("/formAutoridad")
    public String formularioAutoridad(Model model) {
        model.addAttribute("autoridad", new Autoridad());
        return "Autoridad/formularioAutoridad";
    }

    // Método para registrar una nueva Autoridad
    @PostMapping("/registrarAutoridad")
    public String enviarAutoridad(Autoridad autoridad) {
        autoridadServicio.guardarAutoridad(autoridad);
        return "redirect:/autoridades";
    }

    @GetMapping("/eliminarAutoridad/{id}")
    public String eliminarAutoridad(@PathVariable String id){
        autoridadServicio.eliminarAutoridad(id);
        return "redirect:/autoridades";
    }

}
