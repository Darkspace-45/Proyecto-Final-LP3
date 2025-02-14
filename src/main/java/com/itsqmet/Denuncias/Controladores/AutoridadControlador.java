package com.itsqmet.Denuncias.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutoridadControlador {

    @GetMapping("/")
    public String Inicio() {

        return "index";
    }

}
