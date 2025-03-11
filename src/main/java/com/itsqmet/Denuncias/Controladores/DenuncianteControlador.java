package com.itsqmet.Denuncias.Controladores;

import com.itsqmet.Denuncias.Entidades.Denunciante;
import com.itsqmet.Denuncias.Servicios.DenuncianteServicio;
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
public class DenuncianteControlador {

    @Autowired
    private DenuncianteServicio denuncianteServicio;

    //Método para mostrar todas los Denunciantes
    @GetMapping("/denunciantes")
    public String listarDenunciantes(@RequestParam(name = "buscarDenunciante", required = false, defaultValue = "") String buscarDenunciantes, Model model){
        List<Denunciante> denunciantes = denuncianteServicio.mostrarDenunciantes();
        model.addAttribute("buscarDenunciante", buscarDenunciantes);
        model.addAttribute("denunciantes", denunciantes);
        return "Vistas/Denunciantes";
    }

    @GetMapping("/denunciante/{id}")
    public String mostrarDenunciante(@PathVariable String id, Model model) {
        Optional<Denunciante> denunciante = denuncianteServicio.buscarDenunciantePorID(id);
        if (denunciante.isPresent()) {
            model.addAttribute("denunciante", denunciante.get());
            return "Vistas/Denunciantes"; // Asegúrate de que este nombre coincida con el archivo HTML
        } else {
            // Manejar el caso en que la denuncia no se encuentra
            return "redirect:/denunciantes"; // O una página de error
        }
    }

    @GetMapping("/formDenunciante")
    public String formularioDenunciante(Model model){
        model.addAttribute("denunciante", new Denunciante());
        return "Denunciante/formularioDenunciante";
    }

    @PostMapping("/registrarDenunciante")
    public String enviarDenunciante(Denunciante denunciante){
        denuncianteServicio.guardarDenunciante(denunciante);
        return "redirect:/denunciantes";
    }

    @GetMapping("/eliminarDenunciante/{id}")
    public String eliminarDenunciante(@PathVariable String id){
        denuncianteServicio.eliminarDenunciante(id);
        return "redirect:/denunciantes";
    }

    @GetMapping("/actualizarDenunciante/{id}")
    public String actualizarDenunciante(@PathVariable String id, Model model) {
        Optional<Denunciante> denunciante = denuncianteServicio.buscarDenunciantePorID(id);
        if (denunciante.isPresent()) {
            model.addAttribute("denunciante", denunciante.get());
            return "Denunciante/formularioDenunciante";
        } else {
            return "redirect:/denunciantes";
        }
    }
}
